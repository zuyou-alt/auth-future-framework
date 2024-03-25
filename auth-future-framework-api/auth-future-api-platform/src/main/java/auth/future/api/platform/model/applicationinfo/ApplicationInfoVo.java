package auth.future.api.platform.model.applicationinfo;

import auth.future.component.common.model.BaseEntity;
import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 应用信息表
 * @author Hzy
 * @since 2022-09-28
 */
@Schema(title = "应用基础信息")
public class ApplicationInfoVo extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(title = "主键")
    private String id;

    @Schema(title = "应用名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String appName;

    @Schema(title = "应用标识",requiredMode = Schema.RequiredMode.REQUIRED)
    private String appKey;

    @Schema(title = "应用秘钥")
    private String appSecret;

    @Schema(title = "应用描述")
    private String appDes;

    @Schema(title = "授权类型，多个授权类型用逗号分隔",requiredMode = Schema.RequiredMode.REQUIRED)
    private String authTypes;

    @Schema(title = "授权类型，转为集合")
    private List<String>  authTypeList = new ArrayList<>();

    @Schema(title = "授权范围，多个范围用逗号分隔",requiredMode = Schema.RequiredMode.REQUIRED)
    private String scopes;

    @Schema(title = "授权范围，转为集合")
    private List<String>  scopeList = new ArrayList<>();

    @Schema(title = "回调URI")
    private String redirectUri;

    @Schema(title = "应用有效期开始时间",requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime beginTime;

    @Schema(title = "应用有效期结束时间",requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime endTime;

    @Schema(title = "应用类型: 1=公共应用 2=内部应用 3=外部应用",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer type;

    @Schema(title = "配合前端回显使用",requiredMode = Schema.RequiredMode.REQUIRED)
    private List<String> typeStr = new ArrayList<>();

    @Schema(title = "应用状态: 1=正常  2=禁用",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer appStatus;

    @Schema(title = "应用状态配合前端回显使用",requiredMode = Schema.RequiredMode.REQUIRED)
    private List<String> appStatusStr = new ArrayList<>();

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

    public String getAppDes() {
        return appDes;
    }

    public void setAppDes(String appDes) {
        this.appDes = appDes;
    }

    public String getAuthTypes() {
        return authTypes;
    }

    public void setAuthTypes(String authTypes) {
        this.authTypes = authTypes;
    }

    public List<String> getAuthTypeList() {
        if (StrUtil.isNotBlank(authTypes)){
            return Arrays.asList(authTypes.split(","));
        }
        return authTypeList;
    }

    public void setAuthTypeList(List<String> authTypeList) {
        this.authTypeList = authTypeList;
    }

    public String getScopes() {
        return scopes;
    }

    public void setScopes(String scopes) {
        this.scopes = scopes;
    }

    public List<String> getScopeList() {
        if (StrUtil.isNotBlank(scopes)){
            return Arrays.asList(scopes.split(","));
        }
        return scopeList;
    }

    public void setScopeList(List<String> scopeList) {
        this.scopeList = scopeList;
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

    public List<String> getTypeStr() {
        if (type!=null){
            typeStr.add(type.toString());
        }
        return typeStr;
    }

    public void setTypeStr(List<String> typeStr) {
        this.typeStr = typeStr;
    }

    public List<String> getAppStatusStr() {
        if (appStatus!=null){
            appStatusStr.add(appStatus.toString());
        }
        return appStatusStr;
    }

    public void setAppStatusStr(List<String> appStatusStr) {
        this.appStatusStr = appStatusStr;
    }
}
