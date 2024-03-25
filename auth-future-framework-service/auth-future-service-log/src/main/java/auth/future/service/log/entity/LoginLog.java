package auth.future.service.log.entity;

import auth.future.component.common.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serial;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 登录日志表
 * @author Hzy
 * @since 2023-10-02
 */
@TableName("t_log_login")
public class LoginLog extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     *  主键ID
     */
    @TableId("F_ID")
    private String id;

    /**
     * 用户ID
     */
    @TableField("F_USER_ID")
    private String userId;

    /**
     * 用户账号
     */
    @TableField("F_USER_ACCOUNT")
    private String userAccount;

    /**
     * 用户名称
     */
    @TableField("F_USER_NAME")
    private String userName;

    /**
     * 用户身份
     */
    @TableField("F_USER_IDENTITY")
    private String userIdentity;

    /**
     * 组织ID
     */
    @TableField("F_ORG_ID")
    private String orgId;

    /**
     * 组织名称
     */
    @TableField("F_ORG_NAME")
    private String orgName;

    /**
     * 组织PATH
     */
    @TableField("F_ORG_PATH")
    private String orgPath;

    /**
     * 日志内容
     */
    @TableField("F_CONTENT")
    private String content;

    /**
     * 登录标志
     */
    @TableField("F_LOGIN_FLAG")
    private String loginFlag;

    /**
     * 登录时间
     */
    @TableField("F_LOGIN_TIME")
    private LocalDateTime loginTime;

    /**
     * 注销时间
     */
    @TableField("F_LOGOUT_TIME")
    private LocalDateTime logoutTime;

    /**
     * 登录类型：0 密码登录，1 CA登录 ...
     */
    @TableField("F_LOGIN_TYPE")
    private Integer loginType;

    /**
     * 终端类型：0电脑，1手机 ...
     */
    @TableField("F_TERMINAL_TYPE")
    private Integer terminalType;

    /**
     * 终端信息
     */
    @TableField("F_TERMINAL_INFO")
    private String terminalInfo;

    /**
     * 登录位置
     */
    @TableField("F_LOCATION")
    private String location;

    /**
     * 应用名称
     */
    @TableField("F_APP_KEY")
    private String appKey;

    /**
     * 租户ID
     */
    @TableField("F_TENANT_ID")
    private String tenantId;

    /**
     * 备注
     */
    @TableField("F_REMARK")
    private String remark;

    /**
     * 日志摘要
     */
    @TableField("F_DIGEST")
    private String digest;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
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

    public String getOrgPath() {
        return orgPath;
    }

    public void setOrgPath(String orgPath) {
        this.orgPath = orgPath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(String loginFlag) {
        this.loginFlag = loginFlag;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public LocalDateTime getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(LocalDateTime logoutTime) {
        this.logoutTime = logoutTime;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public Integer getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(Integer terminalType) {
        this.terminalType = terminalType;
    }

    public String getTerminalInfo() {
        return terminalInfo;
    }

    public void setTerminalInfo(String terminalInfo) {
        this.terminalInfo = terminalInfo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDigest() {
        return digest;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

}
