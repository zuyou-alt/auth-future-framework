package auth.future.component.message.consumer;

import org.springframework.context.ApplicationEvent;

/**
 * 消息监听事件
 * @author hzy
 * @since 2023-11-04
 **/
public class MessageTransferEvent extends ApplicationEvent {
    private String target;

    private Object message;

    public MessageTransferEvent(String target, Object message) {
        super(message);
        this.setTarget(target);
        this.setMessage(message);
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
