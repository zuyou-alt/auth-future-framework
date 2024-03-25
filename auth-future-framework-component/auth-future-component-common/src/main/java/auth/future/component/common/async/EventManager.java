package auth.future.component.common.async;

import jakarta.annotation.Resource;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.TimerTask;

/**
 * 异步事件管理器
 * @author hzy
 * @since 2023-11-04
 **/
@Component
public class EventManager {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    public void publicEvent(ApplicationEvent applicationEvent,boolean async){
        if (async){
            AsyncManager.getInstance().execute(new TimerTask() {
                @Override
                public void run() {
                    applicationEventPublisher.publishEvent(applicationEvent);
                }
            });
        }else{
            applicationEventPublisher.publishEvent(applicationEvent);
        }
    }

    public void publicEvent(ApplicationEvent applicationEvent){
        this.publicEvent(applicationEvent,false);
    }
}
