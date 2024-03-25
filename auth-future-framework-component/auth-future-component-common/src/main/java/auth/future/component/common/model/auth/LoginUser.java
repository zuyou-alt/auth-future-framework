package auth.future.component.common.model.auth;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 存储在token中的信息
 * @author hzy
 * @since 2023-12-21
 **/
public class LoginUser implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 登录Id（一般为登录日志Id）
     */
    private String loginId;

    /**
     * 登录类型
     */
    private Integer loginType;

    /**
     * 用户ID
     */
    private String appId;


    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用key
     */
    private String appKey;

    /**
     *  主键
     */
    private String userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户电话
     */
    private String userPhone;

    /**
     * 用户账号
     */
    private String userAccount;


    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户头像
     */
    private String headPortrait;

    /**
     * 用户性别;male 男 female 女
     */
    private String gender;

    /**
     * 用户来源;1 自主注册 2 系统添加  3 第三方系统 4 APP注册
     */
    private Integer sourceTo;

    /**
     * 用户状态;1 正常（默认） 2 锁定  3 禁用
     */
    private Integer status;

    /**
     * 审核状态;1 正常  2  待审核  3 审核不通过
     */
    private Integer auditStatus;

    /**
     * 用户身份;default 普通用户  admin管理员 super 超级管理员
     */
    private String identity;

    /**
     * 用户登录标识/用户是否登录过;1 从未登录  2已经登录
     */
    private Integer loginFlag;

    /**
     * 用户排序码
     */
    private Integer userSort;

    /**
     *  主键
     */
    private String orgId;
    /**
     * 组织名称
     */
    private String orgName;
    /**
     * 组织父级ID
     */
    private String orgParentId;

    /**
     * 组织排序码
     */
    private Integer orgSort;

    /**
     * 组织类型
     */
    private String orgType;

    /**
     * 组织路径
     */
    private String orgPath;

    /**
     * 用户角色ID集合
     */
    List<String> roleIdList;

    /**
     * 用户角色Key集合
     */
    List<String> roleKeyList;

    List<UserOrgVo> userOrgVoList;

    /**
     * 登录时间
     */
    private LocalDateTime loginTime;

    /**
     * 过期时间
     */
    private LocalDateTime expirationTime;

    /**
     * 登录使用的验证码
     */
    private String captcha;

    /**
     * 登录IP地址
     */
    private String ipAddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    public LoginUser() {
    }

    public List<UserOrgVo> getUserOrgVoList() {

        return userOrgVoList;
    }

    public void setUserOrgVoList(List<UserOrgVo> userOrgVoList) {
        this.userOrgVoList = userOrgVoList;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getLoginId() {
        return loginId;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getAppId() {
        return appId;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getSourceTo() {
        return sourceTo;
    }

    public void setSourceTo(Integer sourceTo) {
        this.sourceTo = sourceTo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public Integer getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(Integer loginFlag) {
        this.loginFlag = loginFlag;
    }

    public Integer getUserSort() {
        return userSort;
    }

    public void setUserSort(Integer userSort) {
        this.userSort = userSort;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgParentId() {
        return orgParentId;
    }

    public void setOrgParentId(String orgParentId) {
        this.orgParentId = orgParentId;
    }

    public Integer getOrgSort() {
        return orgSort;
    }

    public void setOrgSort(Integer orgSort) {
        this.orgSort = orgSort;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getOrgPath() {
        return orgPath;
    }

    public void setOrgPath(String orgPath) {
        this.orgPath = orgPath;
    }

    public List<String> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<String> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public List<String> getRoleKeyList() {
        return roleKeyList;
    }

    public void setRoleKeyList(List<String> roleKeyList) {
        this.roleKeyList = roleKeyList;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getLoginLocation() {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation) {
        this.loginLocation = loginLocation;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public void initUserInfo(UserVo userVo){
        this.userId = userVo.getId();
        this.userName = userVo.getName();
        this.userPhone = userVo.getPhone();
        this.nickName = userVo.getNickName();
        this.headPortrait = userVo.getHeadPortrait();
        this.gender = userVo.getGender();
        this.sourceTo = userVo.getSourceTo();
        this.status = userVo.getStatus();
        this.auditStatus = userVo.getAuditStatus();
        this.identity = userVo.getIdentity();
        this.loginFlag = userVo.getLoginFlag();
        this.userSort = userVo.getUserSort();
        this.userAccount = userVo.getAccount();
    }
}
