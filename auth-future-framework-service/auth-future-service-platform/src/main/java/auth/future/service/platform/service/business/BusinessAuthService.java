package auth.future.service.platform.service.business;

import auth.future.api.platform.model.auth.LoginRequestVo;
import auth.future.api.message.SmsMessageServiceApi;
import auth.future.api.message.utils.SmsResultUtils;
import auth.future.api.platform.model.role.RoleBindTypeEnum;
import auth.future.component.common.commonenum.IdentityEnum;
import auth.future.component.common.model.auth.UserOrgVo;
import auth.future.component.common.context.CurrentContext;
import auth.future.component.common.exception.LoginException;
import auth.future.component.common.model.auth.constant.SecurityConstants;
import auth.future.component.redis.RedisUtil;
import auth.future.service.platform.authhandler.LogOutHandler;
import auth.future.service.platform.beanconversion.UserMapperCvs;
import auth.future.service.platform.entity.Organization;
import auth.future.service.platform.entity.RoleBind;
import auth.future.service.platform.entity.User;
import auth.future.service.platform.entity.UserOrg;
import auth.future.service.platform.event.event.LogOutEvent;
import auth.future.service.platform.event.event.LoginEvent;
import auth.future.service.platform.authhandler.handlerchain.LoginHandlerChain;
import auth.future.service.platform.authhandler.handlerchain.LoginHandlerChainManager;
import auth.future.service.platform.service.OrganizationService;
import auth.future.service.platform.service.RoleBindService;
import auth.future.service.platform.service.UserOrgService;
import auth.future.service.platform.service.UserService;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import auth.future.api.platform.AuthServiceApi;
import auth.future.component.authentication.AuthenticationProperties;
import auth.future.component.authentication.jwt.JwtUtil;
import auth.future.component.common.model.auth.*;
import auth.future.component.common.model.auth.constant.AuthRedisKeys;
import auth.future.component.common.exception.AuthException;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author hzy
 * @since 2023-08-15
 **/
@Service
public class BusinessAuthService implements AuthServiceApi {
    @Resource
    private AuthenticationProperties authenticationProperties;

    @Resource
    private UserService userService;

    @Resource
    private UserOrgService userOrgService;

    @Resource
    private OrganizationService organizationService;

    @Resource
    private RoleBindService roleBindService;


    /**
     * 短信服务
     */
    @Resource
    private SmsMessageServiceApi smsMessageServiceApi;

    /**
     * 异步事件发布
     */
    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * 登录认证器
     */
    @Resource
    private LoginHandlerChainManager loginFilterChainManager;

    @Resource
    private BusinessUserOrgService businessUserOrgService;

    /**
     *  1. 验证客户端合法性
     *  2. 验证用户状态，用户密码，用户权限
     *  3. 保存授权码，设置有效期(通过配置得到，默认1分钟)
     *  4. 返回授权码
     * @param loginRequestVo 用户登录信息
     * @return 授权码信息
     */
    public LoginResult login(HttpServletRequest httpServletRequest,LoginRequestVo loginRequestVo) {
        UserVo userVo = new UserVo();
        LoginUser loginUser = new LoginUser();
        try {
            userVo  = this.getUserVo(loginRequestVo.getAccount(),loginRequestVo.getRelevancyOrgId());
            loginRequestVo.setUserVo(userVo);
            LoginHandlerChain loginFilterChain = loginFilterChainManager.getInstance();
            // 登录验证
            loginFilterChain.doHandler(loginRequestVo,loginUser);
            // 设置用户角色
            this.setRoleList(userVo.getId(),userVo.getRelevancyOrgId(),loginUser);
            // 设置用户的多组织信息
            List<UserOrgVo> userOrgVos = businessUserOrgService.queryUserOrgList(userVo.getId());
            loginUser.setUserOrgVoList(userOrgVos);
            // 补充一些登录用户信息
            this.supplementLoginUser(loginUser);
            // 构建 保存token信息
            TokenInfo tokenInfo = this.constructAndSaveTokenInfo(loginUser);
            applicationEventPublisher.publishEvent(new LoginEvent(loginRequestVo,userVo,true,loginUser.getLoginId()));
            return tokenInfo.getTokenVo(loginUser);
        }catch (Exception e){
            this.supplementLoginUser(loginUser);
            applicationEventPublisher.publishEvent(new LoginEvent(e, loginRequestVo,userVo,false,loginUser.getLoginId()));
            throw e;
        }
    }

    @Override
    public LoginResult switchOrg(String orgId) {
        UserOrg userDefaultOrg = userOrgService.getUserDefaultOrg(CurrentContext.getUserId());
        // 获取当前tokenInfo
        String accessTokenKey = AuthRedisKeys.AUTH_TOKEN + CurrentContext.getToken();
        TokenInfo tokenInfo = RedisUtil.get(accessTokenKey);
        if (tokenInfo==null){
            throw new AuthException("用户登录失效，请重新登录后在进行切换！");
        }
        LoginUser loginUser = JwtUtil.extractAllClaimObject(tokenInfo.getJwtToken(), LoginUser.class);
        Organization organization = organizationService.getOrganization(orgId);
        // 更新组织信息
        this.setOrgInfo(loginUser,organization);
        // 更新角色信息
        this.setRoleList(loginUser.getUserId(),loginUser.getOrgId(),loginUser);
        // 当用户切换的组织不是归属组织时，该用户的身份只能是普通用户,用户的权限菜单也需要通过角色管理进行授权。
        if (!userDefaultOrg.getOrgId().equals(organization.getId())){
            loginUser.setIdentity(IdentityEnum.DEFAULT_USER.getIdentity());
        }
        // 重新构建和保存token信息
        TokenInfo newTokenInfo = constructAndSaveTokenInfo(loginUser);
        // 清除上一个组织的登录信息
        LogOutHandler.cleanUserLoginInfo();
        return newTokenInfo.getTokenVo(loginUser);
    }


    private void setOrgInfo(LoginUser loginUser,Organization organization){
        loginUser.setOrgId(organization.getId());
        loginUser.setOrgType(organization.getType());
        loginUser.setOrgPath(organization.getPath());
        loginUser.setOrgName(organization.getName());
        loginUser.setOrgParentId(organization.getParentId());
    }

    private void supplementLoginUser(LoginUser loginUser){
        // 获取登录IP
        loginUser.setLoginId(String.valueOf(IdWorker.getId(loginUser)));
        loginUser.setIpAddr(CurrentContext.get(SecurityConstants.LOGIN_IP).toString());
        LocalDateTime now = LocalDateTime.now();
        CurrentContext.set(SecurityConstants.LOGIN_TIME,now);
        loginUser.setLoginTime(now);
    }

    /**
     * 根据用户token获取用户信息
     * 用户信息包括：
     * 1. 该用户所属应用信息
     * 2. 该用户所属组织信息
     * 3. 该用户基础信息
     * 4. 该用户角色信息
     * @param request 请求体，用于从请求头中获取token，如果请求头中没有，则从参数中获取
     * @param token token，可以直接当做参数传递
     * @return 用户信息
     */
    @Override
    public LoginUser getUserInfo(HttpServletRequest request,String token) {
        if (StrUtil.isBlank(token)){
            token = CurrentContext.getToken();
        }
        String loginUserKey = AuthRedisKeys.AUTH_LOGIN_INFO + token;
        if(RedisUtil.hasKey(loginUserKey)){
            return RedisUtil.get(loginUserKey);
        }else{
            throw new AuthException("获取用户信息失败，请重新登录！");
        }
    }

    /**
     *获取用户所属角色
     * @param userId 用户ID
     * @param orgId 当前用户的组织ID
     * @param loginUser 角色集合
     */
    private void setRoleList(String userId,String orgId,LoginUser loginUser){
        // 获取角色信息
        List<RoleBind> roleBindList = roleBindService.queryRoleListByBindsAndApp(Arrays.asList(userId, orgId),loginUser.getAppId());
        // 添加用户多组织角色筛选
        List<RoleBind> list = roleBindList.stream().filter(o -> RoleBindTypeEnum.ORG.getType().equals(o.getBindType())
                || (RoleBindTypeEnum.USER.getType().equals(o.getBindType()) && orgId.equals(o.getOrgId()))).toList();
        Set<String> roleIds = list.stream().map(RoleBind::getRoleId).collect(Collectors.toSet());
        loginUser.setRoleIdList(new ArrayList<>(roleIds));

    }



    private UserVo getUserVo(String account,String relevancyOrgId) throws LoginException{
        User userInfo = userService.getUserByAccount(account);
        if (userInfo==null) {
            throw new LoginException("该账号不存在！");
        }
        // 如果指定了组织登录，则使用指定的，如果没有指定组织，则 使用归属组织
        if (StrUtil.isBlank(relevancyOrgId)){
            UserOrg userDefaultOrg = userOrgService.getUserDefaultOrg(userInfo.getId());
            relevancyOrgId = userDefaultOrg.getOrgId();
        }
        UserVo userVo = UserMapperCvs.INSTANCE.DbToVo(userInfo);
        userVo.setRelevancyOrgId(relevancyOrgId);
        return userVo;
    }


    /**
     * 根据授权模式和JWTToken生成tokenInfo
     */
    private TokenInfo generateTokenInfo( String jwtToken,Integer loginType){
        String accessToken = IdUtil.simpleUUID().toUpperCase();
        String refreshToken = IdUtil.simpleUUID().toUpperCase();
        return new TokenInfo(accessToken,refreshToken,jwtToken,"", loginType,authenticationProperties.getTokenExpiredTime());
    }

    /**
     * 将token信息保存至Redis中
     * @param loginUser 登录用户信息
     */
    private TokenInfo constructAndSaveTokenInfo(LoginUser loginUser){
        // 构建 token信息
        String jwtToken = JwtUtil.generateToken(JSONObject.toJSONString(loginUser));
        TokenInfo tokenInfo = generateTokenInfo(jwtToken, loginUser.getLoginType());
        String accessTokenKey = AuthRedisKeys.AUTH_TOKEN + tokenInfo.getAccess_token();
        String refreshTokenKey = AuthRedisKeys.AUTH_REFRESH_TOKEN + tokenInfo.getRefresh_token();
        RedisUtil.set(accessTokenKey,tokenInfo,authenticationProperties.getTokenExpiredTime(),TimeUnit.SECONDS);
        RedisUtil.set(refreshTokenKey,tokenInfo,authenticationProperties.getRefreshExpiredTime(),TimeUnit.SECONDS);
        return tokenInfo;
    }

    /**
     * 退出登录
     * @param request 请求对象
     * @param token token
     */
    @Override
    public void logout(HttpServletRequest request,String token) {
        LoginUser loginUser = CurrentContext.getLoginUser();
        try {
            LogOutHandler.cleanUserLoginInfo();
            applicationEventPublisher.publishEvent(new LogOutEvent(loginUser,true));
        }catch (Exception e){
            applicationEventPublisher.publishEvent(new LogOutEvent(e,loginUser,false));
        }
    }

    @Override
    public String sendSmsCode(String phone) {
        // 生成6位验证码
        String code = RandomUtil.randomNumbers(6);
        String result = smsMessageServiceApi.sendSmsMessage(phone, code);
        boolean verifyResult = SmsResultUtils.verifyResult(result);
        if (!verifyResult) {
            throw new AuthException("短信验证码发送失败，请重试！");
        }
        RedisUtil.set(AuthRedisKeys.AUTH_SMS_CODE+phone,code,authenticationProperties.getSmsCodeTime(),TimeUnit.SECONDS);
        return authenticationProperties.isShowSmsCode()? code : "";
    }

    /**
     * 刷新用户token
     * 重新生成access_token
     * 自动续期
     * @param authorizeRequest 刷新token
     * @return token信息
     */
    @Override
    public TokenVo refreshToken(AuthorizeRequest authorizeRequest) {
        return null;
    }

}
