package auth.future.component.message.producer;

import auth.future.component.common.async.AsyncManager;
import auth.future.component.message.exception.MessageProducerException;
import auth.future.component.message.properties.MessagePlatformType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hzy
 * @since 2023-11-19
 **/
public class MessageProducerManager {

    private static   final Map<String,MessageProducer> messageProducerMap = new HashMap<>();

    public static final MessageProducerManager ME = new MessageProducerManager();

    public static MessageProducerManager getInstance() {
        return ME;
    }

    private MessageProducerManager() {

    }

    public void put(MessagePlatformType type,MessageProducer messageProducer){
        messageProducerMap.put(type.getName(),messageProducer);
    }

    /**
     * 发送消息
     * @param target 消息通道
     * @param message 消息内容
     */
    public static void sendMessage(String target,String message){
        for (MessageProducer messageProducer : messageProducerMap.values()) {
            messageProducer.sendMessage(target,message);
        }
    }

    /**
     * 指定平台发送消息
     * @param target 消息通道
     * @param type 消息平台
     * @param message 消息内容
     */
    public static void sendMessage(MessagePlatformType type, String target, String message){
        MessageProducer messageProducer = messageProducerMap.get(type.getName());
        if (messageProducer==null){
            throw new MessageProducerException("消息发送失败，消息组件未初始化！"+type.getName());
        }
        messageProducer.sendMessage(target,message);
    }
}
