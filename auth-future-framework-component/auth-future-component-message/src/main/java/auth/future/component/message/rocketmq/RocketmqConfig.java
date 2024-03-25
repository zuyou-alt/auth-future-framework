package auth.future.component.message.rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.autoconfigure.RocketMQProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hzy
 * @since 2023-11-25
 **/
@Configuration
@ConditionalOnExpression("${auth.component.message.enable:false} && '${auth.component.message.messaging-platform}'.contains('rocketmq')")
public class RocketmqConfig {

    @Bean
    public RocketMQProperties rocketMQProperties(){
        return new RocketMQProperties();
    }

    @Bean
    public DefaultMQProducer producer(RocketMQProperties rocketMQProperties){
        DefaultMQProducer producer = new DefaultMQProducer(rocketMQProperties.getProducer().getGroup());
        producer.setNamesrvAddr(rocketMQProperties.getNameServer());
        try {
            producer.start();
        } catch (MQClientException e) {
            throw new RuntimeException(e);
        }
        return producer;
    }
}
