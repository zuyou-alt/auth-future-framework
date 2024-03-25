package auth.future.component.message.kafka;

import auth.future.component.common.async.EventManager;
import auth.future.component.message.consumer.MessageListener;
import auth.future.component.message.consumer.MessageTransferEvent;
import jakarta.annotation.Resource;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * kafka监听类
 * @author hzy
 * @since 2023-11-04
 **/
@Component
@ConditionalOnExpression("${auth.component.message.enable:false} && '${auth.component.message.messaging-platform}'.contains('kafka')")
public class KafkaMessageListener extends MessageListener {

    @KafkaListener(groupId = "${auth.component.message.group-id}",topics = {"#{'${auth.component.message.targets}'.split(',')}"})
    public void kafkaListener(ConsumerRecord<?,?> record){
        publish(record.topic(),record.value());
    }
}
