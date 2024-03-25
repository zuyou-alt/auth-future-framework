package auth.future.component.message.redis;

import auth.future.component.message.producer.MessageProducer;
import auth.future.component.message.producer.MessageProducerManager;
import auth.future.component.message.properties.MessagePlatformType;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

/**
 * @author hzy
 * @since 2023-11-19
 **/
@Component
@ConditionalOnExpression("${auth.component.message.enable:false} && '${auth.component.message.messaging-platform}'.contains('redis')")
public class RedisMessageProducer extends MessageProducer {

    public RedisMessageProducer() {
        MessageProducerManager.getInstance().put(MessagePlatformType.REDIS,this);
    }

    @Override
    public void sendMessage(String target, String message) {
//        RedisUtilsV3.sendMessage(target,message);
    }
}
