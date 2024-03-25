package auth.future.service.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import auth.future.component.common.model.BaseEntity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 应用信息表
 * @author Hzy
 * @since 2022-09-28
 */
@TableName("T_APPLICATION_INFO")
public class ApplicationInfo extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "F_ID")
    private String id;
    /**
     * 客户名称
     */
    @TableField("F_APP_NAME")
    private String appName;
    /**
     * 客户唯一标识
     */
    @TableField("F_APP_KEY")
    private String appKey;

    /**
     * 密钥
     */
    @TableField("F_APP_SECRET")
    private String appSecret;

    /**
     * 应用描述
     */
    @TableField("F_APP_DES")
    private String appDes;

    /**
     * 授权类型，多个授权类型用逗号分隔
     */
    @TableField("F_AUTH_TYPES")
    private String authTypes;
    /**
     * 授权范围，多个范围用逗号分隔
     */
    @TableField("F_SCOPES")
    private String scopes;
    /**
     * 回调URI
     */
    @TableField("F_REDIRECT_URI")
    private String redirectUri;
    /**
     * 开始时间
     */
    @TableField("F_BEGIN_TIME")
    private LocalDateTime beginTime;
    /**
     * 结束时间
     */
    @TableField("F_END_TIME")
    private LocalDateTime endTime;

    /**
     * 0 公共应用   1内部应用 2外部应用
     */
    @TableField("F_TYPE")
    private Integer type;

    /**
     * 是否启用(1 正常  2 禁用)
     *
     */
    @TableField("F_APP_STATUS")
    private Integer appStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAuthTypes() {
        return authTypes;
    }

    public void setAuthTypes(String authTypes) {
        this.authTypes = authTypes;
    }

    public String getScopes() {
        return scopes;
    }

    public void setScopes(String scopes) {
        this.scopes = scopes;
    }

    public String getAppDes() {
        return appDes;
    }

    public void setAppDes(String appDes) {
        this.appDes = appDes;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(Integer appStatus) {
        this.appStatus = appStatus;
    }

}
