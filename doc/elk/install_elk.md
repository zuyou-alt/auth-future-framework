在分布式的项目中，各功能模块产生的日志比较分散，同时为满足性能要求，同一个微服务会集群化部署，当某一次业务报错后，如果不能确定产生的节点，那么只能逐个节点去查看日志文件；logback中RollingFileAppender，ConsoleAppender这类同步化记录器也降低系统性能，综上一些问题，可能考虑采用ELK （elasticsearch+logstash+kibana）配合消息中间件去异步采集，统一展示去解决。

这里之所以要加入kafka是因为

1.  如果直接利用logstash同步日志，则每个节点都需要部署logstash，且logstash会严重消耗性能、浪费资源；
2.  当访问量特别高时，产生的日志速度也会特别快，kafka可以削峰限流、降低logstash的压力；
3.  当logstash故障时消息可以存储到kafka中不会丢失。

## 二、 整体流程图

![在这里插入图片描述](https://img-blog.csdnimg.cn/cb83fd9ff65c4477890ba14a04da3457.png)

## 三、搭建kafka+zk环境

### 1、创建文件夹

```bash
mkdir /usr/elklog/kafka
```

### 2、在创建好的文件夹下创建文件docker-compose.yml

```bash
version: "2" services: zookeeper: image: docker.io/bitnami/zookeeper:3.8 ports: - "2181:2181" environment: - ALLOW_ANONYMOUS_LOGIN=yes networks: - es_default kafka: image: docker.io/bitnami/kafka:3.2 user: root ports: - "9092:9092" environment: - ALLOW_PLAINTEXT_LISTENER=yes - KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper:2181 - KAFKA_CFG_ADVERTISED_LISTENERS=PLAINTEXT://192.168.3.22:9092 depends_on: - zookeeper networks: - es_default
networks: es_default: name: es_default volumes: zookeeper_data: driver: local kafka_data: driver: local



```

### 3、在docker-compose.yml同级目录中输入启动命令

```bash
docker-compose up -d  
```

**这里用的是docker-compose方式安装，安装之前需要先安装好docker和docker-compose**

[docker安装方式：https://blog.csdn.net/qq\_38639813/article/details/129384923](https://blog.csdn.net/qq_38639813/article/details/129384923)

[docker-compose安装方式：https://blog.csdn.net/qq\_38639813/article/details/129751441](https://blog.csdn.net/qq_38639813/article/details/129751441)

## 四、搭建elk环境

### 1、拉取elk所需镜像

```bash
docker pull elasticsearch:7.10.1
docker pull kibana:7.10.1
docker pull elastic/metricbeat:7.10.1
docker pull elastic/logstash:7.10.1
```

### 2、创建文件夹：

```bash
mkdir /usr/elklog/elk
mkdir /usr/elklog/elk/logstash
mkdir /usr/elklog/elk/logstash/pipeline mkdir /usr/elklog/elk/es
mkdir /usr/elklog/elk/es/data
```

### 3、给data文件夹授权

```bash
chmod 777 /usr/elklog/elk/es/data
```

### 4、在/usr/elklog/elk/logstash/pipeline中创建logstash.conf

logstash.conf文件作用是将kafka中的日志消息获取出来 ，再推送给elasticsearch

```bash
input { kafka { bootstrap_servers => "192.168.3.22:9092" client_id => "logstash" auto_offset_reset => "latest" consumer_threads => 5 topics => ["demoCoreKafkaLog","webapiKafkaApp"] type => demo }
} output { stdout { } elasticsearch { hosts => ["http://192.168.3.22:9200"] index => "demolog-%{+YYYY.MM.dd}" }
}

```

也可以按照如下方式去写

```bash
input{ kafka{ bootstrap_servers => "192.168.3.22:9092" client_id => "logstash" auto_offset_reset => "latest" consumer_threads => 5 topics => ["demoCoreKafkaLog","webapiKafkaApp"] type => "json" codec => json { charset => "UTF-8" } }
}
output { if [@metadata][kafka][topic] == "demoCoreKafkaLog" { elasticsearch { hosts => "http://192.168.3.22:9200" index => "demoCoreKafkaLog" timeout => 300 } } if [@metadata][kafka][topic] == "webapiKafkaApp" { elasticsearch { hosts => "http://192.168.3.22:9200" index => "webapiKafkaApp" timeout => 300 } } stdout {}
}

```

### 5、在/usr/elklog/elk中创建docker-compose.yml

```bash
version: "2" services: elasticsearch: image: elasticsearch:7.10.1 restart: always privileged: true ports: - "9200:9200" - "9300:9300" volumes: - /usr/elklog/elk/es/data:/usr/share/elasticsearch/data environment: - discovery.type=single-node networks: - es_default kibana: image: kibana:7.10.1 restart: always privileged: true ports: - "5601:5601" environment: - ELASTICSEARCH_URL=http://192.168.3.22:9200 depends_on: - elasticsearch networks: - es_default metricbeat: image: elastic/metricbeat:7.10.1 restart: always user: root environment: - ELASTICSEARCH_HOSTS=http://192.168.3.22:9200 depends_on: - elasticsearch - kibana command: -E setup.kibana.host="192.168.3.22:5601" -E setup.dashboards.enabled=true -E setup.template.overwrite=false -E output.elasticsearch.hosts=["192.168.3.22:9200"] -E setup.ilm.overwrite=true
    networks:
      - es_default
  logstash:
    image: elastic/logstash:7.10.1
    restart: always
    user: root
    volumes:
      - /usr/elklog/elk/logstash/pipeline:/usr/share/logstash/pipeline/  
    depends_on:
      - elasticsearch
      - kibana
    networks:
      - es_default
networks:
  es_default:
    driver: bridge
    name: es_default


```

### 6、启动服务

```bash
docker-compose up -d
```

检验es是否安装成功：http://192.168.3.22:9200

![在这里插入图片描述](https://img-blog.csdnimg.cn/cdcdb0c2bdf94a7081a86de14efbcf5e.png)

检验kibana是否安装成功：192.168.3.22:5601  
![在这里插入图片描述](https://img-blog.csdnimg.cn/43f5d507be0b49f7b0d6802b62dfcd21.png)

### 7、kibana设置中文

从容器中复制出kibana.yml，修改该文件，再复制回去，重启容器：

```bash
docker cp elk-kibana-1:/usr/share/kibana/config/kibana.yml kibana.yml 在这个文件最后加上： i18n.locale: "zh-CN" docker cp kibana.yml elk-kibana-1:/usr/share/kibana/config/kibana.yml


重启kibana容器便可
```

## 五、springboot代码

### 1、引入依赖

```bash
<!-- Kafka资源的引入 -->
<dependency> <groupId>org.apache.kafka</groupId> <artifactId>kafka-clients</artifactId>
</dependency>
<dependency> <groupId>com.github.danielwegener</groupId> <artifactId>logback-kafka-appender</artifactId> <version>0.2.0-RC1</version>
</dependency>
<dependency> <groupId>net.logstash.logback</groupId> <artifactId>logstash-logback-encoder</artifactId> <version>6.4</version>
</dependency>
```

### 2、创建KafkaOutputStream

```bash
package com.elk.log; import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord; import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset; public class KafkaOutputStream extends OutputStream { Producer logProducer; String topic; public KafkaOutputStream(Producer producer, String topic) { this.logProducer = producer; this.topic = topic; } @Override public void write(int b) throws IOException { this.logProducer.send(new ProducerRecord<>(this.topic, b)); } @Override public void write(byte[] b) throws IOException { this.logProducer.send(new ProducerRecord<String, String>(this.topic, new String(b, Charset.defaultCharset()))); } @Override public void flush() throws IOException { this.logProducer.flush(); }
}

```

### 3、创建KafkaAppender

```bash
package com.elk.log; import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.OutputStreamAppender;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.util.StringUtils; import java.io.OutputStream;
import java.util.Properties; public class KafkaAppender<E> extends OutputStreamAppender<E> { private Producer logProducer; private String bootstrapServers; private Layout<E> layout; private String topic; public void setLayout(Layout<E> layout) { this.layout = layout; } public void setBootstrapServers(String bootstrapServers) { this.bootstrapServers = bootstrapServers; } public void setTopic(String topic) { this.topic = topic; } @Override protected void append(E event) { if (event instanceof ILoggingEvent) { String msg = layout.doLayout(event); ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, 0,((ILoggingEvent) event).getLevel().toString(), msg); logProducer.send(producerRecord); } } @Override public void start() { if (StringUtils.isEmpty(topic)) { topic = "Kafka-app-log"; } if (StringUtils.isEmpty(bootstrapServers)) { bootstrapServers = "localhost:9092"; } logProducer = createProducer(); OutputStream targetStream = new KafkaOutputStream(logProducer, topic); super.setOutputStream(targetStream); super.start(); } @Override public void stop() { super.stop(); if (logProducer != null) { logProducer.close(); } } //创建生产者 private Producer createProducer() { synchronized (this) { if (logProducer != null) { return logProducer; } Properties props = new Properties(); props.put("bootstrap.servers", bootstrapServers); //判断是否成功，我们指定了“all”将会阻塞消息 0.关闭 1.主broker确认 -1（all）.所在节点都确认 props.put("acks", "0"); //失败重试次数 props.put("retries", 0); //延迟100ms，100ms内数据会缓存进行发送 props.put("linger.ms", 100); //超时关闭连接 //props.put("connections.max.idle.ms", 10000); props.put("batch.size", 16384); props.put("buffer.memory", 33554432); //该属性对性能影响非常大，如果吞吐量不够，消息生产过快，超过本地buffer.memory时，将阻塞1000毫秒，等待有空闲容量再继续 props.put("max.block.ms",1000); props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer"); props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer"); return new KafkaProducer<String, String>(props); } } }


```

### 4、创建logback-spring.xml，放到application.yml的同级目录

```bash
<?xml version="1.0" encoding="UTF-8"?>
<configuration scan="true" scanPeriod="60 seconds"> <!-- <include resource="org/springframework/boot/logging/logback/base.xml"/>--> <logger name="com.elk" level="info"/> <!-- 定义日志文件 输入位置 --> <property name="logPath" value="logs" />
<!-- <property name="logPath" value="D:/logs/truckDispatch" />--> <!-- 控制台输出日志 --> <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender"> <encoder> <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger -%msg%n</pattern> <charset class="java.nio.charset.Charset">UTF-8</charset> </encoder> </appender> <!-- INFO日志文件 --> <appender name="infoAppender" class="ch.qos.logback.core.rolling.RollingFileAppender"> <filter class="ch.qos.logback.classic.filter.LevelFilter"> <level>INFO</level> <onMatch>ACCEPT</onMatch> <onMismatch>DENY</onMismatch> </filter> <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy"> <!-- 文件名称 --> <fileNamePattern>${logPath}\%d{yyyyMMdd}\info.log</fileNamePattern> <!-- 文件最大保存历史天数 --> <MaxHistory>30</MaxHistory> </rollingPolicy> <encoder> <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger - %msg%n</pattern> <charset class="java.nio.charset.Charset">UTF-8</charset> </encoder> </appender> <!-- DEBUG日志文件 --> <appender name="debugAppender" class="ch.qos.logback.core.rolling.RollingFileAppender"> <filter class="ch.qos.logback.classic.filter.LevelFilter"> <level>DEBUG</level> <onMatch>ACCEPT</onMatch> <onMismatch>DENY</onMismatch> </filter> <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy"> <!-- 文件名称 --> <fileNamePattern>${logPath}\%d{yyyyMMdd}\debug.log</fileNamePattern> <!-- 文件最大保存历史天数 --> <MaxHistory>30</MaxHistory> </rollingPolicy> <encoder> <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger - %msg%n</pattern> <charset class="java.nio.charset.Charset">UTF-8</charset> </encoder> </appender> <!-- WARN日志文件 --> <appender name="warnAppender" class="ch.qos.logback.core.rolling.RollingFileAppender"> <filter class="ch.qos.logback.classic.filter.LevelFilter"> <level>WARN</level> <onMatch>ACCEPT</onMatch> <onMismatch>DENY</onMismatch> </filter> <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy"> <!-- 文件名称 --> <fileNamePattern>${logPath}\%d{yyyyMMdd}\warn.log</fileNamePattern> <!-- 文件最大保存历史天数 --> <MaxHistory>30</MaxHistory> </rollingPolicy> <encoder> <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger - %msg%n</pattern> <charset class="java.nio.charset.Charset">UTF-8</charset> </encoder> </appender> <!-- ERROR日志文件 --> <appender name="errorAppender" class="ch.qos.logback.core.rolling.RollingFileAppender"> <filter class="ch.qos.logback.classic.filter.LevelFilter"> <level>ERROR</level> <onMatch>ACCEPT</onMatch> <onMismatch>DENY</onMismatch> </filter> <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy"> <!-- 文件名称 --> <fileNamePattern>${logPath}\%d{yyyyMMdd}\error.log</fileNamePattern> <!-- 文件最大保存历史天数 --> <MaxHistory>30</MaxHistory> </rollingPolicy> <encoder> <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger - %msg%n</pattern> <charset class="java.nio.charset.Charset">UTF-8</charset> </encoder> </appender> <!-- 往kafka推送日志 --> <appender name="kafkaAppender" class="com.elk.log.KafkaAppender"> <!-- kafka地址 --> <bootstrapServers>192.168.3.22:9092</bootstrapServers> <!-- 配置topic --> <topic>demoCoreKafkaLog</topic> <!-- encoder负责两件事，一是将一个event事件转换成一组byte数组，二是将转换后的字节数据输出到文件中 --> <encoder> <pattern>${HOSTNAME} %date [%thread] %level %logger{36} [%file : %line] %msg%n</pattern> <charset>utf8</charset> </encoder> <!-- layout主要的功能就是：将一个event事件转化为一个String字符串 --> <layout class="ch.qos.logback.classic.PatternLayout"> <pattern>${HOSTNAME} %date [%thread] %level %logger{36} [%file : %line] %msg%n</pattern> </layout> </appender> <!-- 指定这个包的日志级别为error --> <logger name="org.springframework" additivity="false"> <level value="ERROR" /> <!-- 控制台输出 -->
<!-- <appender-ref ref="STDOUT" />--> <appender-ref ref="errorAppender" /> </logger> <!-- 由于启动的时候，以下两个包下打印debug级别日志很多 ，所以调到ERROR--> <!-- 指定这个包的日志级别为error --> <logger name="org.apache.tomcat.util" additivity="false"> <level value="ERROR"/> <!-- 控制台输出 -->
<!-- <appender-ref ref="STDOUT"/>--> <appender-ref ref="errorAppender"/> </logger> <!-- 默认spring boot导入hibernate很多的依赖包，启动的时候，会有hibernate相关的内容，直接去除 --> <!-- 指定这个包的日志级别为error --> <logger name="org.hibernate.validator" additivity="false"> <level value="ERROR"/> <!-- 控制台输出 -->
<!-- <appender-ref ref="STDOUT"/>--> <appender-ref ref="errorAppender"/> </logger> <!-- 监控所有包，日志输入到以下位置，并设置日志级别 --> <root level="WARN"><!--INFO--> <!-- 控制台输出 --> <appender-ref ref="STDOUT"/> <!-- 这里因为已经通过kafka往es中导入日志，所以就没必要再往日志文件中写入日志，可以注释掉下面四个，提高性能 --> <appender-ref ref="infoAppender"/> <appender-ref ref="debugAppender"/> <appender-ref ref="warnAppender"/> <appender-ref ref="errorAppender"/> <appender-ref ref="kafkaAppender"/> </root>
</configuration>

```

### 5、配置文件无需任何修改

```bash
server: tomcat: uri-encoding: UTF-8 max-threads: 1000 min-spare-threads: 30 port: 8087
  connection-timeout: 5000ms
  servlet:
    context-path: /
```

### 6、编写测试类

```bash
package com.elk.log; import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; @Slf4j
@RestController
@RequestMapping("/test")
public class TestController { @GetMapping("/testLog") public String testLog() { log.warn("gotest"); return "ok"; } @GetMapping("/testLog1") public Integer testLog1() { int i = 1/0; return i; }
}

```

## 六、利用kibana查看日志

![在这里插入图片描述](https://img-blog.csdnimg.cn/18023e36de3546128df3b29e5fecddff.png)

![在这里插入图片描述](https://img-blog.csdnimg.cn/457ea11ece90453398da5feb587d67f3.png)

![在这里插入图片描述](https://img-blog.csdnimg.cn/eebcc442fdb44dfd827a9a2cd0bd0516.png)

注意：这里的索引名字就是logstash.conf中创建的索引名，出现这个也意味着整个流程成功

![在这里插入图片描述](https://img-blog.csdnimg.cn/f8c2ebdc2d33436aa51f30ce813e7758.png)

![在这里插入图片描述](https://img-blog.csdnimg.cn/182a9615d8c949ccb865fea073d33e5e.png)  
此时索引模式创建完毕，我创建的索引模式名字是demo\*

![在这里插入图片描述](https://img-blog.csdnimg.cn/0c7c381acc8b4d178e804e63e9449389.png)  
![在这里插入图片描述](https://img-blog.csdnimg.cn/0b1188db3f0f48958a9a221d3d1376f6.png)  
这时就可以看到日志了，可以进一步调用测试接口去验证，我这里不在展示，至此全部完毕