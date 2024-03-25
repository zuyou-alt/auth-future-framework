package auth.future.api.monitor.model;

import auth.future.component.common.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;

/**
 * <p>
 * 接口基础信息表
 * </p>
 *
 * @author Hzy
 * @since 2023-12-29
 */
@Schema(name = "接口信息对象")
public class MoApiInfoVo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(title = "主键")
    private String id;

    /**
     * 接口名称
     */
    @Schema(title = "接口名称")
    private String name;

    /**
     * 接口主机
     */
    @Schema(title = "接口主机")
    private String apiHost;

    /**
     * 接口端口
     */
    @Schema(title = "接口端口")
    private Integer apiPort;

    /**
     * 接口URI
     */
    @Schema(title = "接口URI")
    private String apiUri;

    /**
     * 接口类型 POST GET ...
     */
    @Schema(title = "接口类型 POST GET ...")
    private String apiMethod;

    /**
     * 接口所属应用
     */
    @Schema(title = "接口所属应用")
    private String appId;

    /**
     * 接口参数（json格式）
     */
    @Schema(title = "接口参数（json格式）")
    private String apiParameters;

    /**
     * 接口请求体（json格式，如post）
     */
    @Schema(title = "接口请求体（json格式，如post）")
    private String apiBody;

    /**
     * 所属项目ID
     */
    @Schema(title = "所属项目ID")
    private String projectId;

    /**
     * 所属项目名称
     */
    @Schema(title = "所属项目名称")
    private String projectName;

    /**
     * 所属应用名称
     */
    @Schema(title = "所属应用名称")
    private String appName;

    /**
     * 接口协议
     */
    @Schema(title = "接口协议")
    private String protocol;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getApiHost() {
        return apiHost;
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }
    public Integer getApiPort() {
        return apiPort;
    }

    public void setApiPort(Integer apiPort) {
        this.apiPort = apiPort;
    }
    public String getApiUri() {
        return apiUri;
    }

    public void setApiUri(String apiUri) {
        this.apiUri = apiUri;
    }
    public String getApiMethod() {
        return apiMethod;
    }

    public void setApiMethod(String apiMethod) {
        this.apiMethod = apiMethod;
    }
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
    public String getApiParameters() {
        return apiParameters;
    }

    public void setApiParameters(String apiParameters) {
        this.apiParameters = apiParameters;
    }
    public String getApiBody() {
        return apiBody;
    }

    public void setApiBody(String apiBody) {
        this.apiBody = apiBody;
    }
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
    
}
