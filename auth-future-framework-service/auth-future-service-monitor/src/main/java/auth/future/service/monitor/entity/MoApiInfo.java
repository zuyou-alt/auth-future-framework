package auth.future.service.monitor.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import auth.future.component.common.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * <p>
 * 接口基础信息表
 * </p>
 *
 * @author Hzy
 * @since 2023-12-29
 */
@TableName("t_mo_api_info")
public class MoApiInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("F_ID")
    private String id;

    /**
     * 接口名称
     */
    @TableField("F_NAME")
    private String name;

    /**
     * 接口主机
     */
    @TableField("F_API_HOST")
    private String apiHost;

    /**
     * 接口端口
     */
    @TableField("F_API_PORT")
    private Integer apiPort;

    /**
     * 接口URI
     */
    @TableField("F_API_URI")
    private String apiUri;

    /**
     * 接口类型 POST GET ...
     */
    @TableField("F_API_METHOD")
    private String apiMethod;

    /**
     * 接口所属应用
     */
    @TableField("F_APP_ID")
    private String appId;

    /**
     * 接口参数（json格式）
     */
    @TableField("F_API_PARAMETERS")
    private String apiParameters;

    /**
     * 接口请求体（json格式，如post）
     */
    @TableField("F_API_BODY")
    private String apiBody;

    /**
     * 所属项目ID
     */
    @TableField("F_PROJECT_ID")
    private String projectId;

    /**
     * 所属项目名称
     */
    @TableField("F_PROJECT_NAME")
    private String projectName;

    /**
     * 所属应用名称
     */
    @TableField("F_APP_NAME")
    private String appName;

    /**
     * 接口协议
     */
    @TableField("F_PROTOCOL")
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
