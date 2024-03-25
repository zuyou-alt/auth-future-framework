package auth.future.api.log.model;

import auth.future.component.common.model.PageEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(name = "日志查询对象")
public class PageAuditLogVo extends PageEntity {
    @Schema(title = "用户名称")
    private String userName;

    @Schema(title = "用户账号")
    private String userAccount;

    @Schema(title = "用户身份")
    private String userIdentity;

    @Schema(title = "组织名称")
    private String orgName;

    @Schema(title = "操作开始时间")
    private LocalDateTime operatorStartTime;

    @Schema(title = "操作结束时间")
    private LocalDateTime operatorEndTime;

    @Schema(title = "应用名称")
    private String appName;

    @Schema(title = "业务模块")
    private String bizModel;

    @Schema(title = "操作目标")
    private String target;

    @Schema(title = "用户IP")
    private String userIp;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public LocalDateTime getOperatorStartTime() {
        return operatorStartTime;
    }

    public void setOperatorStartTime(LocalDateTime operatorStartTime) {
        this.operatorStartTime = operatorStartTime;
    }

    public LocalDateTime getOperatorEndTime() {
        return operatorEndTime;
    }

    public void setOperatorEndTime(LocalDateTime operatorEndTime) {
        this.operatorEndTime = operatorEndTime;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getBizModel() {
        return bizModel;
    }

    public void setBizModel(String bizModel) {
        this.bizModel = bizModel;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }
}
