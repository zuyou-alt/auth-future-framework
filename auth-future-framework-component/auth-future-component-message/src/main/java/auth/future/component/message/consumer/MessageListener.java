package auth.future.component.message.consumer;


import auth.future.component.common.async.EventManager;
import auth.future.component.common.utils.SpringUtil;

/**
 * 消息监听父类
 * 监听到消息后，将消息事件统一进行发布
 * @author hzy
 * @since 2023-11-04
 **/
public abstract class MessageListener {
    private EventManager eventManager;

    public void publish(String target,Object message){
        getEventManager().publicEvent(new MessageTransferEvent(target, message));
    }

    private EventManager getEventManager(){
        if (eventManager==null){
            eventManager = SpringUtil.getBean(EventManager.class);
        }
        return eventManager;
    }

}
