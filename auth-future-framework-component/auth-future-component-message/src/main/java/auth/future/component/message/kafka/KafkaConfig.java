package auth.future.component.message.kafka;

import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * kafka初始化配置类
 * @author hzy
 * @since 2023-11-04
 **/
@Configuration
@ConditionalOnExpression("${auth.component.message.enable:false} && '${auth.component.message.messaging-platform}'.contains('kafka')")
public class KafkaConfig {

    @Resource
    private KafkaProperties kafkaProperties;

    @Bean
    public Map<String, Object> producerConfigs() {
        return new HashMap<>(kafkaProperties.buildProducerProperties());
    }

    @Bean
    public Map<String, Object> consumerConfigs() {
        return new HashMap<>(kafkaProperties.buildConsumerProperties());
    }

    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }
}
