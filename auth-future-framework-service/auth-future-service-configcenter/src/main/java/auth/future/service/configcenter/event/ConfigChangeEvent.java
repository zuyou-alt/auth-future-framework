package auth.future.service.configcenter.event;

import org.springframework.context.ApplicationEvent;

public class ConfigChangeEvent extends ApplicationEvent {
    public ConfigChangeEvent(Object source) {
        super(source);
    }
}
