package auth.future.api.log.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 登录日志表
 * @author Hzy
 * @since 2023-10-02
 */
@Schema(name = "登录日志对象")
public class LoginLogVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(title = "登录日志ID")
    private String id;

    @Schema(title = "用户ID")
    private String userId;

    @Schema(title = "用户编号")
    private String userAccount;

    @Schema(title = "用户名称")
    private String userName;

    @Schema(title = "用户身份")
    private String userIdentity;

    @Schema(title = "组织ID")
    private String orgId;

    @Schema(title = "组织名称")
    private String orgName;

    @Schema(title = "组织PATH")
    private String orgPath;

    @Schema(title = "日志内容")
    private String content;

    @Schema(title = "登录标志")
    private String loginFlag;

    @Schema(title = "登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime loginTime;

    @Schema(title = "注销时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime logoutTime;

    @Schema(title = "登录类型：0 密码登录，1 CA登录 ...")
    private Integer loginType;

    @Schema(title = "终端类型：1电脑，2手机 ...")
    private Integer terminalType;

    @Schema(title = "终端信息")
    private String terminalInfo;

    @Schema(title = "登录位置")
    private String location;

    @Schema(title = "应用Key")
    private String appKey;


    @Schema(title = "租户ID")
    private String tenantId;

    @Schema(title = "备注")
    private String remark;

    @Schema(title = "日志摘要")
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

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}
