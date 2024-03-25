package auth.future.component.message.producer;

/**
 * @author hzy
 * @since 2023-11-04
 **/
public abstract class MessageProducer {

    public abstract void sendMessage(String target, String message);


}
