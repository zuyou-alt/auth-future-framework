package auth.future.api.log.model;


import auth.future.component.common.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 审计日志表
 * @author Hzy
 * @since 2023-10-02
 */
@Schema(name = "审计日志对象")
public class AuditLogVo extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Schema(title = "主键ID")
    @TableId("F_ID")
    private String id;

    @Schema(title = "登录日志ID")
    @TableField("F_LOGIN_ID")
    private String loginId;

    @Schema(title = "用户ID")
    @TableField("F_USER_ID")
    private String userId;

    @Schema(title = "用户编号")
    @TableField("F_USER_ACCOUNT")
    private String userAccount;

    @Schema(title = "用户编号")
    @TableField("F_USER_NAME")
    private String userName;

    @Schema(title = "用户身份")
    @TableField("F_USER_IDENTITY")
    private String userIdentity;

    @Schema(title = "组织ID")
    @TableField("F_ORG_ID")
    private String orgId;

    @Schema(title = "组织名称")
    @TableField("F_ORG_NAME")
    private String orgName;

    @Schema(title = "组织PATH")
    @TableField("F_ORG_PATH")
    private String orgPath;

    @Schema(title = "日志标题")
    @TableField("F_TITLE")
    private String title;

    @Schema(title = "日志内容")
    @TableField("F_CONTENT")
    private String content;

    @Schema(title = "业务类型：0其它 1新增 2修改 3删除")
    @TableField("F_BIZ_TYPE")
    private Integer bizType;

    @Schema(title = "事件级别")
    @TableField("F_EVENT_LEVEL")
    private Integer eventLevel;

    @Schema(title = "操作目标")
    @TableField("F_TARGET")
    private String target;

    @Schema(title = "操作结果：0成功，非0失败")
    @TableField("F_RESULT")
    private Integer result;

    @Schema(title = "操作时间")
    @TableField("F_OPERATOR_LOG")
    private LocalDateTime recordTime;

    @Schema(title = "请求URL")
    @TableField("F_REQUEST_URL")
    private String requestUrl;

    @Schema(title = "请求参数")
    @TableField("F_REQUEST_PARAM")
    private String requestParam;

    @Schema(title = "响应结果")
    @TableField("F_RESPONSE_RESULT")
    private String responseResult;

    @Schema(title = "操作位置")
    @TableField("F_LOCATION")
    private String location;

    @Schema(title = "应用ID")
    @TableField("F_APP_ID")
    private String appId;

    @Schema(title = "租户ID")
    @TableField("F_TENANT_ID")
    private String tenantId;

    @Schema(title = "备注")
    @TableField("F_REMARK")
    private String remark;

    @Schema(title = "日志摘要")
    @TableField("F_DIGEST")
    private String digest;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getBizType() {
        return bizType;
    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    public Integer getEventLevel() {
        return eventLevel;
    }

    public void setEventLevel(Integer eventLevel) {
        this.eventLevel = eventLevel;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public LocalDateTime getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(LocalDateTime recordTime) {
        this.recordTime = recordTime;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }

    public String getResponseResult() {
        return responseResult;
    }

    public void setResponseResult(String responseResult) {
        this.responseResult = responseResult;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
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
}
