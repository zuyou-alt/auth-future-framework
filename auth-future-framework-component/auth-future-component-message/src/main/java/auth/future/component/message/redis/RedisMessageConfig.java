package auth.future.component.message.redis;

import auth.future.component.message.properties.CommonMessageProperties;
import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hzy
 * @since 2023-11-19
 **/
@Configuration
@ConditionalOnExpression("${auth.component.message.enable:false} && '${auth.component.message.messaging-platform}'.contains('redis')")
public class RedisMessageConfig {

    @Resource
    private CommonMessageProperties commonMessageProperties;

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(new RedisMessageListener(), getChannels()); // 指定要监听的消息通道
        return container;
    }

    /**
     * 获取配置的监听通道
     */
    private List<ChannelTopic> getChannels(){
        String targets = commonMessageProperties.getTargets();
        List<ChannelTopic> channels = new ArrayList<>();
        if (StrUtil.isNotBlank(targets)){
            String[] split = targets.split(",");
            for (String s : split) {
                channels.add(new ChannelTopic(s));
            }
        }
        return channels;
    }
}
