package auth.future.component.message.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author hzy
 * @since 2023-11-04
 **/
@Configuration
@ConfigurationProperties(prefix = "auth.component.message")
public class CommonMessageProperties {
    /**
     * 是否开启消息服务
     */
    private boolean enable;

    /**
     * 消息平台
     */
    private String messagingPlatform;

    /**
     * 消息目标（topic）
     */
    private String targets;

    /**
     * 组Id
     */
    private String groupId;

    /**
     * 是否异步发送
     */
    private boolean async;


    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getMessagingPlatform() {
        return messagingPlatform;
    }

    public void setMessagingPlatform(String messagingPlatform) {
        this.messagingPlatform = messagingPlatform;
    }

    public String getTargets() {
        return targets;
    }

    public void setTargets(String targets) {
        this.targets = targets;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public boolean isAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }
}
