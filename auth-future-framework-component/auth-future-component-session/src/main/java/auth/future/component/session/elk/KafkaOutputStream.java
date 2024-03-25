package auth.future.component.session.elk;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.lang.NonNull;

import java.io.OutputStream;
import java.nio.charset.Charset;

public class KafkaOutputStream extends OutputStream {


    private final Producer<String,String> logProducer;
    String topic;

    public KafkaOutputStream(Producer<String,String> producer, String topic) {
        this.logProducer = producer;
        this.topic = topic;
    }


    @Override
    public void write(int b){
        this.logProducer.send(new ProducerRecord<>(this.topic, String.valueOf(b)));
    }

    @Override
    public void write(@NonNull byte[] b){
        this.logProducer.send(new ProducerRecord<>(this.topic, new String(b, Charset.defaultCharset())));
    }

    @Override
    public void flush(){
        this.logProducer.flush();
    }
}

