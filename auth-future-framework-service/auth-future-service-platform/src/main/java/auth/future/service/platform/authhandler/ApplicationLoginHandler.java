package auth.future.service.platform.authhandler;

import auth.future.api.platform.model.auth.LoginRequestVo;
import auth.future.api.platform.model.applicationinfo.ApplicationStatusEnum;
import auth.future.component.common.exception.AuthException;
import auth.future.component.common.model.auth.LoginUser;
import auth.future.component.common.utils.SpringUtil;
import auth.future.service.platform.entity.ApplicationInfo;
import auth.future.service.platform.authhandler.handlerchain.LoginHandlerChain;
import auth.future.service.platform.service.ApplicationInfoService;
import cn.hutool.core.util.StrUtil;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 客户端验证处理类
 * @author hzy
 * @since 2023-11-12
 **/
public class ApplicationLoginHandler implements LoginHandler {

    /**
     * 验证客户端的合法性
     * 1. 客户端状态是否正常
     * 2. 客户端是否过期
     * 3. 客户端秘钥是否正确
     * 4. 验证客户端何时能请的权限是否合法
     */
    @Override
    public void doHandler(LoginRequestVo loginRequestVo, LoginHandlerChain loginFilterChain, LoginUser loginUser) throws AuthException {
        String appKey = loginRequestVo.getApp_key();
        if (StrUtil.isBlank(appKey)){
            throw new AuthException("认证失败，客户端不能为空！");
        }
        ApplicationInfoService applicationInfoService = SpringUtil.getBean(ApplicationInfoService.class);
        ApplicationInfo applicationInfo = applicationInfoService.getApplicationInfoByKey(appKey);
        if (applicationInfo==null){
            throw new AuthException(String.format("认证失败，应用不存在！[%s]",appKey));
        }

        Integer status = applicationInfo.getAppStatus();
        if (Objects.equals(ApplicationStatusEnum.DISABLED.getStatus(), status)){
            throw new AuthException(String.format("认证失败，应用已被禁用！[%s]",appKey));
        }

        LocalDateTime endTime = applicationInfo.getEndTime();
        if (endTime.isBefore(LocalDateTime.now())){
            throw new AuthException(String.format("认证失败，应用已过期！[%s]",appKey));
        }

        List<String> scopeList = loginRequestVo.getScopeList();
        if (scopeList!=null && !scopeList.isEmpty()){
            this.checkAppScopes(applicationInfo,scopeList);
        }
        loginUser.setAppKey(applicationInfo.getAppKey());
        loginUser.setAppName(applicationInfo.getAppName());
        loginUser.setAppId(applicationInfo.getId());
        loginFilterChain.doHandler(loginRequestVo,loginUser);
    }

    private void  checkAppScopes(ApplicationInfo applicationInfo,List<String> scopeList){
        String scopes = applicationInfo.getScopes();
        List<String> localScopeList;
        if (StrUtil.isBlank(scopes)){
            localScopeList = new ArrayList<>();
        }else {
            localScopeList = Arrays.asList(scopes.split(","));
        }
        boolean result = new HashSet<>(localScopeList).containsAll(scopeList);
        if (!result){
            throw new AuthException("认证失败，申请的权限超出范围!");
        }
    }
}
