package auth.future.service.monitor.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import auth.future.component.common.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serial;

/**
 * <p>
 * 服务器基础信息表
 * </p>
 *
 * @author Hzy
 * @since 2023-12-29
 */
@TableName("t_mo_server_info")
public class MoServerInfo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId("F_ID")
    private String id;

    /**
     * 服务器名称
     */
    @TableField("F_SERVER_NAME")
    private String serverName;

    /**
     * 服务器IP
     */
    @TableField("F_SERVER_IP")
    private String serverIp;

    /**
     * SSH连接端口
     */
    @TableField("F_SERVER_PORT")
    private Integer serverPort;

    /**
     * 用户名
     */
    @TableField("F_USER_NAME")
    private String userName;

    /**
     * 密码
     */
    @TableField("F_PASSWORD")
    private String password;

    /**
     * 所属应用
     */
    @TableField("F_APP_ID")
    private String appId;

    /**
     * 项目ID
     */
    @TableField("F_PROJECT_ID")
    private String projectId;

    /**
     * 项目名称
     */
    @TableField("F_PROJECT_NAME")
    private String projectName;

    /**
     * 应用名称
     */
    @TableField("F_APP_NAME")
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
