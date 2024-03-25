package auth.future.service.file.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author hzy
 * @since 2024-01-15
 **/
@Configuration
@ConfigurationProperties(prefix = "auth.future.service.file")
public class FileConfigProperties {
    /**
     * 文件服务主机
     */
    private String fileServerHost;

    /**
     * 文件服务端口
     */
    private String filerServerPort;

    public String getFileServerHost() {
        return fileServerHost;
    }

    public void setFileServerHost(String fileServerHost) {
        this.fileServerHost = fileServerHost;
    }

    public String getFilerServerPort() {
        return filerServerPort;
    }

    public void setFilerServerPort(String filerServerPort) {
        this.filerServerPort = filerServerPort;
    }
}
