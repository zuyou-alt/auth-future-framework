package auth.future.component.message.rocketmq;


import auth.future.component.message.producer.MessageProducer;
import jakarta.annotation.Resource;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

/**
 * @author HuZuYou
 */
@Component
@ConditionalOnExpression("${auth.component.message.enable:false} && '${auth.component.message.messaging-platform}'.contains('rocketmq')")
public class RocketmqMessageProducer extends MessageProducer {

    @Resource
    private DefaultMQProducer producer;

    @Override
    public void sendMessage(String target, String message) {
        Message msg = new Message(target,"tag",message.getBytes());
        try {
            producer.send(msg);
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
