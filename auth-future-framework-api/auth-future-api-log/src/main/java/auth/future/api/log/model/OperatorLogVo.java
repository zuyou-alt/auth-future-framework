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
 * 操作日志表
 * @author Hzy
 * @since 2023-10-02
 */
@Schema(name = "操作日志对象")
public class OperatorLogVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 操作日志ID
     */
    @Schema(title = "操作日志ID")
    private String id;

    /**
     *登录日志ID
     */
    @Schema(title = "登录日志ID")
    private String loginId;

    /**
     * 用户ID
     */
    @Schema(title = "用户ID")
    private String userId;

    /**
     * 用户账号
     */
    @Schema(title = "用户账号")
    private String userAccount;

    /**
     * 用户名称
     */
    @Schema(title = "用户名称")
    private String userName;

    /**
     * 用户身份
     */
    @Schema(title = "用户身份")
    private String userIdentity;

    /**
     * 组织ID
     */
    @Schema(title = "组织ID")
    private String orgId;

    /**
     * 机构名称
     */
    @Schema(title = "机构名称")
    private String orgName;

    /**
     * 机构PATH
     */
    @Schema(title = "机构PATH")
    private String orgPath;

    /**
     * 日志标题
     */
    @Schema(title = "日志标题")
    private String title;

    /**
     * 日志内容
     */
    @Schema(title = "日志内容")
    private String content;

    /**
     * 业务类型：0其它 1新增 2修改 3删除
     */
    @Schema(title = "业务类型：0其它 1新增 2修改 3删除")
    private Integer bizType;

    /**
     * 业务模块
     */
    @Schema(title = "业务模块")
    private String bizModel;

    /**
     * 记录时间
     */
    @Schema(title = "记录时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime recordTime;


    /**
     * 操作目标
     */
    @Schema(title = "操作目标")
    private String target;

    /**
     * 操作结果：0成功，非0失败
     */
    @Schema(title = "操作结果：0成功，非0失败")
    private Integer result;

    /**
     * 操作发起者
     */
    @Schema(title = "操作发起者")
    private String sender;


    /**
     * 请求URL
     */
    @Schema(title = "请求URL")
    private String requestUrl;

    /**
     * 请求参数
     */
    @Schema(title = "请求参数")
    private String requestParam;

    /**
     * 响应结果
     */
    @Schema(title = "响应结果")
    private String responseResult;

    /**
     * 用户IP
     */
    @Schema(title = "用户IP")
    private String location;
    /**
     * 应用ID
     */
    @Schema(title = "应用标识")
    private String appKey;


    /**
     * 租户ID
     */
    @Schema(title = "租户ID")
    private String tenantId;

    /**
     * 备注
     */
    @Schema(title = "备注")
    private String remark;

    /**
     * 日志摘要
     */
    @Schema(title = "日志摘要")
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

    public String getBizModel() {
        return bizModel;
    }

    public void setBizModel(String bizModel) {
        this.bizModel = bizModel;
    }

    public LocalDateTime getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(LocalDateTime recordTime) {
        this.recordTime = recordTime;
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

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
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
