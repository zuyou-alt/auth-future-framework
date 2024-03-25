package auth.future.service.platform.service.business;

import auth.future.api.log.common.LogHelper;
import auth.future.api.log.common.constant.BizType;
import auth.future.api.platform.model.UpdateIdentity;
import auth.future.api.platform.model.UpdatePwdVo;
import auth.future.api.platform.model.userOrg.UserOrgTypeEnum;
import auth.future.component.common.commonenum.IdentityEnum;
import auth.future.component.common.context.CurrentContext;
import auth.future.component.common.exception.AuthException;
import auth.future.component.common.exception.CustomException;
import auth.future.component.common.model.PageResult;
import auth.future.component.common.model.auth.UserOrgVo;
import auth.future.component.common.utils.ChineseUtil;
import auth.future.component.common.utils.PhoneNumberUtil;
import auth.future.component.session.LogContext;
import auth.future.service.platform.authhandler.LogOutHandler;
import auth.future.service.platform.baseenum.BindTypeEnum;
import auth.future.service.platform.entity.Organization;
import auth.future.service.platform.entity.RoleBind;
import auth.future.service.platform.entity.UserOrg;
import auth.future.service.platform.service.*;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import auth.future.api.platform.UserServiceApi;
import auth.future.api.platform.model.UserRoleVo;
import auth.future.component.common.encryption.EncryptionPolicy;
import auth.future.component.common.model.auth.UserVo;
import auth.future.component.common.commonenum.UserStatusEnum;
import auth.future.service.platform.beanconversion.UserMapperCvs;
import auth.future.service.platform.config.SystemUserProperties;
import auth.future.service.platform.entity.User;
import auth.future.api.platform.model.request.RequestUserPage;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author hzy
 * @since 2023-08-10
 **/
@Service
public class BusinessUserService implements UserServiceApi {

    @Resource
    private UserService userService;

    @Resource
    private SystemUserProperties systemUserProperties;

    @Resource
    private EncryptionPolicy encryptionPolicy;

    @Resource
    private RoleBindService roleBindService;

    @Resource
    private UserOrgService userOrgService;

    @Resource
    private OrganizationService organizationService;

    @Resource
    private BusinessDictService businessDictService;

    public static final String USER_IDENTITY_DICT = "user_identity";

    @Transactional
    @Override
    public String saveUser(UserVo userVo) {
        User user = UserMapperCvs.INSTANCE.VoToDb(userVo);
        // 检查用户数据
        this.checkUser(user);
        // 设置用默认状态
        this.setUserDefaultStatus(user);
        // 设置用户审核相关
        this.setUserAudit(user);
        // 新增时设置用户默认密码
        this.setUserDefaultPwd(user);
        String userId = userService.saveUser(user);
        // 保存用户组织关系对象
        this.saveUserOrg(userVo);
        LogHelper.recordOperatorLog("保存/修改用户",()-> "保存用户", BizType.UPDATE,user.getId(), CurrentContext.getUserId());
        return userId;
    }


    @Override
    public String saveUserBaseInfo(UserVo userVo) {
        User user = UserMapperCvs.INSTANCE.VoToDb(userVo);
        userService.updateById(user);
        return user.getId();
    }

    /**
     * 获取组织用户关系对象
     * @param user 用户信息
     */
    @Transactional
    protected void saveUserOrg(UserVo user){
        this.setUserDefaultOrg(user);
        UserOrg userOrg = new UserOrg();
        userOrg.setOrgId(user.getRelevancyOrgId());
        userOrg.setUserId(user.getId());
        userOrg.setType(UserOrgTypeEnum.DEFAULT.getType());
        userOrgService.removeRelation(user.getId(),user.getRelevancyOrgId());
        userOrgService.save(userOrg);
    }

    /**
     * 检查用户数据完整性
     * @param user 用户信息
     */
    private void checkUser(User user){
        Assert.isTrue(StrUtil.isNotBlank(user.getAccount()),"用户账号不能为空！");
        Assert.isTrue(StrUtil.isNotBlank(user.getPhone()) || StrUtil.isNotBlank(user.getEmail()),"用户电话或邮箱不能为空！");
        Assert.isTrue(StrUtil.isNotBlank(user.getGender()),"用户性别不能为空！");
        Assert.isTrue(StrUtil.isNotBlank(user.getName()) || StrUtil.isNotBlank(user.getNickName()),"用户姓名或昵称不能为空！");
        Assert.isTrue(user.getSourceTo()!=null,"用户来源不能为空！");
    }

    /**
     * 设置用户默认状态
     */
    private void setUserDefaultStatus(User user){
        user.setStatus(user.getStatus()==null ? UserStatusEnum.DEFAULT.getStatus() : user.getStatus());
        if (StrUtil.isBlank(user.getIdentity())){
            user.setIdentity(IdentityEnum.DEFAULT_USER.getIdentity());
        }
    }

    /**
     * 设置用户默认组织
     * @param userVo 用户信息
     */
    private void setUserDefaultOrg(UserVo userVo){
        if (StrUtil.isBlank(userVo.getRelevancyOrgId())){
            userVo.setRelevancyOrgId(systemUserProperties.getRegisterOrgId());
        }
    }

    /**
     * 设置用户审核状态
     * @param user 用户信息
     */
    private void setUserAudit(User user){
        if (user.getAuditStatus()!=null) return;
        // 传递了审核参数，并且开启了用户审核，才生效
        user.setAuditStatus(systemUserProperties.getAuditEnable()? UserStatusEnum.AUDIT_NOT.getStatus() : UserStatusEnum.AUDIT_DEFAULT.getStatus());
    }

    /**
     * 设置用户默认密码
     * @param user 用户
     */
    private void  setUserDefaultPwd(User user){
        if (CharSequenceUtil.isBlank(user.getId())){
            String password = user.getPassword();
            if (CharSequenceUtil.isBlank(password)){
                password = systemUserProperties.getDefaultPwd();
            }
            String encrypt = encryptionPolicy.encrypt(password);
            String salt = RandomUtil.randomStringUpper(6);
            password  = encrypt+ salt;
            user.setSalt(salt);
            user.setPassword(encryptionPolicy.encrypt(password));
        }
    }

    @Transactional
    @Override
    public boolean removeUser(String userId) {
        // 1. 删除用户与组织的关系
        userOrgService.removeRelation(userId);
        // 2. 删除用户与资源的绑定
        roleBindService.removeBindByBind(Collections.singletonList(userId));
        return userService.removeById(userId);
    }

    @Transactional
    @Override
    public boolean removeUsers(List<String> userIds) {
        if (userIds!=null && !userIds.isEmpty()){
            for (String userId : userIds) {
                removeUser(userId);
            }
            return true;
        }
        return false;
    }

    @Override
    public UserVo getUser(String id) {
        User user = userService.getUser(id);
        UserOrg userDefaultOrg = userOrgService.getUserDefaultOrg(user.getId());
        String orgId = userDefaultOrg.getOrgId();
        Organization organization = organizationService.getOrganization(orgId);
        UserVo userVo = UserMapperCvs.INSTANCE.DbToVo(user);
        userVo.setDefaultOrgId(organization.getId());
        userVo.setDefaultOrgName(organization.getName());
        userVo.setPassword(null);
        userVo.setSalt(null);
        return userVo;
    }

    @Override
    public List<UserVo> getUserListAllByOrgId(String orgId) {
        return userService.getUserListAllByOrgId(orgId);
    }

    @Override
    public List<UserVo> getUserListByOrgId(String orgId) {
        return userService.getUserListByOrgId(orgId);
    }


    @Override
    public PageResult<UserVo> pageUserList(RequestUserPage requestUserPage) {
        IPage<UserVo> userIPage = userService.pageUserList(requestUserPage);
        List<UserVo> records = userIPage.getRecords();
        if (records.isEmpty()) return new PageResult<>(userIPage,records);
        Set<String> orgIds = records.stream().map(UserVo::getRelevancyOrgId).collect(Collectors.toSet());
        if (orgIds.isEmpty()) return new PageResult<>(userIPage,records);
        List<Organization> organizations = organizationService.listByIds(orgIds);
        Map<String, Organization> orgMap = organizations.stream().collect(Collectors.toMap(Organization::getId, o ->o));
        Map<String, String> userIdentityMap = businessDictService.getValueMap(USER_IDENTITY_DICT);
        records.forEach(o-> {
            o.setRelevancyOrgName(orgMap.get(o.getRelevancyOrgId()) ==null ? "": orgMap.get(o.getRelevancyOrgId()).getName());
            o.setIdentityStr(getUserIdentityStr(o.getIdentityList(),userIdentityMap));
        });
        return new PageResult<>(userIPage,records);
    }

    private String getUserIdentityStr(List<String> userIdentityList,Map<String, String> userIdentityMap){
        List<String> str = new ArrayList<>();
        for (String s : userIdentityList) {
            str.add(userIdentityMap.get(s));
        }
        return String.join(",", str);
    }

    @Override
    public boolean updateUserStatus(String userId, Integer userStatus) {
        return userService.updateUserStatus(userId,userStatus);
    }

    @Override
    public boolean resetPassword(String userId) {
        // 获取默认密码
        String defaultPwd = systemUserProperties.getDefaultPwd();
        String encrypt = encryptionPolicy.encrypt(defaultPwd);
        String salt = getSalt();
        String password = encryptionPolicy.encrypt(encrypt + salt);
        boolean resetResult = userService.resetPassword(userId,salt, password);
        if (resetResult){
            //退出登录
            LogOutHandler.cleanUserLoginInfo();
        }
        return resetResult;
    }

    @Override
    public boolean updatePwd(UpdatePwdVo updatePwdVo) {
        String userId = updatePwdVo.getUserId();
        User user = userService.getUser(userId);
        String dbPassword = user.getPassword();
        boolean matches = encryptionPolicy.matches(updatePwdVo.getPassword() + user.getSalt(), dbPassword);
        if (!matches){
            throw new CustomException("原始密码输入错误，请重新输入！");
        }
        String newSalt = RandomUtil.randomStringUpper(6);
        String newPassword = updatePwdVo.getNewPassword() + newSalt;
        user.setPassword(encryptionPolicy.encrypt(newPassword));
        user.setSalt(newSalt);
        return userService.updateById(user);
    }


    @Transactional
    @Override
    public boolean batchResetPassword(List<String> ids) {
        if (ids==null || ids.isEmpty()) return false;
        for (String id : ids) {
            resetPassword(id);
        }
        return true;
    }

    @Override
    public boolean unlockUser(String userId) {
        return userService.updateUserStatus(userId,UserStatusEnum.DEFAULT.getStatus());
    }

    @Override
    public boolean batchUnlockUser(List<String> ids) {
        return userService.batchUpdateUserStatus(ids,UserStatusEnum.DEFAULT.getStatus());
    }

    @Override
    public IPage<UserRoleVo> queryUserListByOrgId(String orgId, String orgPath) {

        return null;
    }

    @Override
    public boolean updateUserIdentity(UpdateIdentity updateIdentity) {
        Assert.isTrue(StrUtil.isNotBlank(updateIdentity.userId()),"请指定用户信息！");
        Assert.isTrue(updateIdentity.identityList()!=null && !updateIdentity.identityList().isEmpty(),"用户身份不能为空！");
        List<String> identityList = updateIdentity.identityList();
        String identityStr = String.join(",", identityList);
        return userService.updateUserIdentity(updateIdentity.userId(),identityStr);
    }

    @Override
    public UserVo getUserInfoByAccount(String account) {
        User userByAccount = userService.getUserByAccount(account);
        if (userByAccount==null){
            return null;
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userByAccount,userVo);
        return userVo;
    }
    /**
     * 获取一个随机盐
     * @return 随机盐
     */
    private String getSalt(){
        return RandomUtil.randomStringUpper(6);
    }


    /**
     * 构建用户测试数据
     */
    public Map<String,Object> constructUserData(){
        Logger logger = LoggerFactory.getLogger(BusinessUserService.class);
        int pageNum = 1;
        int size = 100;
        int total = 0;
        while (true){
            Page<Organization> page = organizationService.lambdaQuery().page(new Page<>(pageNum, size));
            List<Organization> records = page.getRecords();
            if (records.isEmpty()) break;
            logger.info("当前第{}页",pageNum);
            pageNum++;
            List<User> userList = new ArrayList<>();
            List<UserOrg> userOrgList = new ArrayList<>();
            List<RoleBind> roleBindList = new ArrayList<>();
            for (Organization record : records) {
                //每个组织下20个人
                for (int i = 0; i < 20; i++) {
                    User testuser = getTestuser(i);
                    userList.add(testuser);
                    userOrgList.add(getTestUserOrg(testuser.getId(), record.getId()));
                    roleBindList.add(getTestRoleBind(testuser.getId(),record.getId()));
                    total++;
                }
            }
            userService.saveBatch(userList);
            userOrgService.saveBatch(userOrgList);
            roleBindService.saveBatch(roleBindList);
        }
        Map<String,Object> result = new HashMap<>();
        result.put("total",total);
        return result;
    }

    private RoleBind getTestRoleBind(String userId,String orgId){
        RoleBind roleBind = new RoleBind();
        roleBind.setRoleId("1752984583969726465");
        roleBind.setBindType(BindTypeEnum.USER.getBindType());
        roleBind.setBindId(userId);
        roleBind.setOrgId(orgId);
        return roleBind;
    }

    private UserOrg getTestUserOrg(String userId,String orgId){
        // 获取用户名
        UserOrg userOrg = new UserOrg();
        String id = String.valueOf(IdWorker.getId());
        userOrg.setType(UserOrgTypeEnum.DEFAULT.getType());
        userOrg.setOrgId(orgId);
        userOrg.setUserId(userId);
        userOrg.setId(id);
        return userOrg;
    }

    private User getTestuser(int i){
        // 获取用户名
        User user = new User();
        String id = String.valueOf(IdWorker.getId());
        user.setId(id);
        String phoneNumber = PhoneNumberUtil.createPhoneNumber(0);
        String name = ChineseUtil.randomChineseName();
        String pinyin = PinyinUtil.getPinyin(name, "");
        user.setAccount(pinyin);
        user.setGender((i % 2 == 0) ? "male" : "female");
        user.setHeadPortrait("1752935148946485250");
        user.setIdentity(IdentityEnum.DEFAULT_USER.getIdentity());
        user.setStatus(UserStatusEnum.DEFAULT.getStatus());
        user.setPhone(phoneNumber);
        user.setName(name);
        return user;
    }

}
