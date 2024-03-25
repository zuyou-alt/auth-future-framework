package auth.future.component.session.elk;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.OutputStreamAppender;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.OutputStream;
import java.util.Properties;

public class KafkaAppender<E> extends OutputStreamAppender<E> {
    private Producer<String,String> logProducer;
    private String bootstrapServers;
    private Layout<E> layout;
    private String topic;
    @Override
    protected void append(E event) {
        if (event instanceof ILoggingEvent) {
            String msg = layout.doLayout(event);
            Level level = ((ILoggingEvent) event).getLevel();
            String string = level.toString();
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, 0,string, msg);
            logProducer.send(producerRecord);
        }
    }

    @Override
    public void start() {
        logProducer = createProducer();
        OutputStream targetStream = new KafkaOutputStream(logProducer, topic);
        super.setOutputStream(targetStream);
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
        if (logProducer != null) {
            logProducer.close();
        }
    }

    //创建生产者
    private Producer<String,String> createProducer() {
        synchronized (this) {
            if (logProducer != null) {
                return logProducer;
            }
            Properties props = new Properties();
            props.put("bootstrap.servers", bootstrapServers);
            //判断是否成功，我们指定了“all”将会阻塞消息 0.关闭 1.主broker确认 -1（all）.所在节点都确认
            props.put("acks", "0");
            //失败重试次数
            props.put("retries", 0);
            //延迟100ms，100ms内数据会缓存进行发送
            props.put("linger.ms", 100);
            //超时关闭连接
            //props.put("connections.max.idle.ms", 10000);
            props.put("batch.size", 16384);
            props.put("buffer.memory", 33554432);
            //该属性对性能影响非常大，如果吞吐量不够，消息生产过快，超过本地buffer.memory时，将阻塞1000毫秒，等待有空闲容量再继续
            props.put("max.block.ms",1000);
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            return new KafkaProducer<>(props);
        }
    }

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    public Layout<E> getLayout() {
        return layout;
    }

    @Override
    public void setLayout(Layout<E> layout) {
        this.layout = layout;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}


