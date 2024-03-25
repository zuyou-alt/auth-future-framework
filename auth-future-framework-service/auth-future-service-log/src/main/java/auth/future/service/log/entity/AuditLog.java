package auth.future.service.log.entity;

import auth.future.component.common.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serial;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 审计日志表
 * @author Hzy
 * @since 2023-10-02
 */
@TableName("t_log_audit")
public class AuditLog  extends BaseEntity implements Serializable {


    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 审计日志ID
     */
    @TableId("F_ID")
    private String id;

    /**
     *登录日志ID
     */
    @TableField("F_LOGIN_ID")
    private String loginId;

    /**
     *用户ID
     */
    @TableField("F_USER_ID")
    private String userId;

    /**
     *用户编号
     */
    @TableField("F_USER_ACCOUNT")
    private String userAccount;

    /**
     * 用户编号
     */
    @TableField("F_USER_NAME")
    private String userName;

    /**
     *用户身份
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
     * 日志标题
     */
    @TableField("F_TITLE")
    private String title;

    /**
     * 日志内容
     */
    @TableField("F_CONTENT")
    private String content;

    /**
     * 业务类型：0其它 1新增 2修改 3删除
     */
    @TableField("F_BIZ_TYPE")
    private Integer bizType;

    /**
     * 事件级别
     */
    @TableField("F_EVENT_LEVEL")
    private Integer eventLevel;

    /**
     * 操作目标
     */
    @TableField("F_TARGET")
    private String target;

    /**
     * 操作结果：0成功，非0失败
     */
    @TableField("F_RESULT")
    private Integer result;

    /**
     * 操作发起者
     */
    @TableField("F_SENDER")
    private String sender;

    /**
     * 操作时间
     */
    @TableField("F_OPERATOR_LOG")
    private LocalDateTime recordTime;

    /**
     * 请求URL
     */
    @TableField("F_REQUEST_URL")
    private String requestUrl;
    /**
     * 请求参数
     */
    @TableField("F_REQUEST_PARAM")
    private String requestParam;
    /**
     * 响应结果
     */
    @TableField("F_RESPONSE_RESULT")
    private String responseResult;
    /**
     * 操作位置
     */
    @TableField("F_LOCATION")
    private String location;
    /**
     * 应用ID
     */
    @TableField("F_APP_ID")
    private String appId;
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

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
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
