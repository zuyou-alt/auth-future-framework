package auth.future.api.monitor.model;

import auth.future.component.common.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;

/**
 * 服务器基础信息表
 * @author Hzy
 * @since 2023-12-27
 */
@Schema(name="ServerInfo对象", description="服务器基础信息表")
public class MoServerInfoPageVo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(title = "页码")
    private Long pageNum;

    @Schema(title = "每页条数")
    private Long pageSize;

    @Schema(title = "服务器名称")
    private String serverName;

    @Schema(title = "服务器IP")
    private String serverIp;

    @Schema(title = "SSH连接端口")
    private Integer serverPort;

    @Schema(title = "用户名")
    private String userName;

    @Schema(title = "所属应用")
    private String appId;

    @Schema(title = "应用名称")
    private String appName;

    @Schema(title = "项目ID")
    private String projectId;

    @Schema(title = "项目名称")
    private String projectName;

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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
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
}
