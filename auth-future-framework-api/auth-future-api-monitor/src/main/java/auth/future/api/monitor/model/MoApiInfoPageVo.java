package auth.future.api.monitor.model;

import auth.future.component.common.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;

/**
 * 接口基础信息表
 * @author Hzy
 * @since 2023-12-27
 */
@Schema(title="ApiInfo对象", description="接口基础信息表")
public class MoApiInfoPageVo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(title = "页码")
    private Long pageNum;

    @Schema(title = "每页条数")
    private Long pageSize;

    @Schema(title = "接口名称")
    private String name;

    @Schema(title = "接口主机")
    private String apiHost;

    @Schema(title = "接口端口")
    private Integer apiPort;

    @Schema(title = "接口URI")
    private String apiUri;

    @Schema(title = "接口类型 POST GET ...")
    private String apiMethod;

    @Schema(title = "接口所属应用")
    private String appId;

    @Schema(title = "接口参数（json格式）")
    private String apiParameters;

    @Schema(title = "接口请求体（json格式，如post）")
    private String apiBody;

    @Schema(title = "项目名称")
    private String projectName;

    @Schema(title = "项目ID")
    private String projectId;

    @Schema(title = "应用名称")
    private String appName;

    public Long getPageNum() {
        return pageNum;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
