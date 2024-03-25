package auth.future.component.message.rocketmq;

import auth.future.component.message.consumer.MessageListener;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

/**
 * @author hzy
 * @since 2023-11-25
 **/
@Component
@ConditionalOnExpression("${auth.component.message.enable:false} && '${auth.component.message.messaging-platform}'.contains('rocketmq')")
@RocketMQMessageListener(topic = "#{'${auth.component.message.targets}'.split(',')}", consumerGroup = "${auth.component.message.group-id}")
public class RocketmqMessageListener  extends MessageListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        publish("",message);
    }
}
