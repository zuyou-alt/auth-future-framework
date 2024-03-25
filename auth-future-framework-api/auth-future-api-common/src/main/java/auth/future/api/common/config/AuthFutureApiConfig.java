package auth.future.api.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author hzy
 * @since 2024-03-12
 **/
@Configuration
@ConfigurationProperties(prefix = "auth.future.api")
public class AuthFutureApiConfig {
    /**
     * 组织用户平台地址
     */
    private String platformAddress;

    /**
     * 文件服务地址
     */
    private String fileAddress;

    /**
     * 消息服务地址
     */
    private String messageAddress;

    public String getPlatformAddress() {
        return platformAddress;
    }

    public void setPlatformAddress(String platformAddress) {
        this.platformAddress = platformAddress;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    public String getMessageAddress() {
        return messageAddress;
    }

    public void setMessageAddress(String messageAddress) {
        this.messageAddress = messageAddress;
    }
}
