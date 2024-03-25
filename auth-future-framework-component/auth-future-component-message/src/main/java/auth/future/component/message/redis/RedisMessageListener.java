package auth.future.component.message.redis;

import auth.future.component.message.consumer.MessageListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.data.redis.connection.Message;
import org.springframework.stereotype.Component;


/**
 * redis消息监听器
 * @author hzy
 * @since 2023-11-19
 **/
@Component
@ConditionalOnExpression("${auth.component.message.enable:false} && '${auth.component.message.messaging-platform}'.contains('redis')")
public class RedisMessageListener extends MessageListener implements org.springframework.data.redis.connection.MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        publish(new String(message.getChannel()),message.toString());
    }
}
