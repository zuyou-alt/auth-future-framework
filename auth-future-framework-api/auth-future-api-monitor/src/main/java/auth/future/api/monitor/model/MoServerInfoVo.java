package auth.future.api.monitor.model;

import auth.future.component.common.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;

/**
 * <p>
 * 服务器基础信息表
 * </p>
 *
 * @author Hzy
 * @since 2023-12-29
 */
@Schema(name = "服务器基础信息对象")
public class MoServerInfoVo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(title = "主键ID")
    private String id;

    /**
     * 服务器名称
     */
    @Schema(title = "服务器名称")
    private String serverName;

    /**
     * 服务器IP
     */
    @Schema(title = "服务器IP")
    private String serverIp;

    /**
     * SSH连接端口
     */
    @Schema(title = "SSH连接端口")
    private Integer serverPort;

    /**
     * 用户名
     */
    @Schema(title = "用户名")
    private String userName;

    /**
     * 密码
     */
    @Schema(title = "密码")
    private String password;

    /**
     * 所属应用
     */
    @Schema(title = "所属应用")
    private String appId;

    /**
     * 项目ID
     */
    @Schema(title = "项目ID")
    private String projectId;

    /**
     * 项目名称
     */
    @Schema(title = "项目名称")
    private String projectName;

    /**
     * 应用名称
     */
    @Schema(title = "应用名称")
    private String appName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }
    public Integer getServerPort() {
        return serverPort;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
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
}
