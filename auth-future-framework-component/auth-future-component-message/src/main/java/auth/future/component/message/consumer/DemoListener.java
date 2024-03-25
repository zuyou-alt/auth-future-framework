package auth.future.component.message.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author hzy
 * @since 2023-11-19
 **/
@Component
public class DemoListener implements ApplicationListener<MessageTransferEvent> {
    Logger log = LoggerFactory.getLogger(DemoListener.class);

    @Override
    public void onApplicationEvent(MessageTransferEvent event) {
        String target = event.getTarget();
        Object message = event.getMessage();
        log.info("收到{}的消息{}",target,message);
    }
}
