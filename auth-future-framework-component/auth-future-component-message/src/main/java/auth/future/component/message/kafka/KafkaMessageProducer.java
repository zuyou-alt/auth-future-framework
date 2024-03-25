package auth.future.component.message.kafka;

import auth.future.component.message.producer.MessageProducer;
import auth.future.component.message.producer.MessageProducerManager;
import auth.future.component.message.properties.MessagePlatformType;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author hzy
 * @since 2023-11-19
 **/
@Component
@ConditionalOnExpression("${auth.component.message.enable:false} && '${auth.component.message.messaging-platform}'.contains('kafka')")
public class KafkaMessageProducer extends MessageProducer {

    @Resource
    private KafkaTemplate<String,String> kafkaTemplate;

    public KafkaMessageProducer() {
        MessageProducerManager.getInstance().put(MessagePlatformType.KAFKA,this);
    }

    @Override
    public void sendMessage(String target, String message) {
        kafkaTemplate.send(target,message);
    }
}
