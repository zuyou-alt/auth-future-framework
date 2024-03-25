package auth.future.component.websocket.event;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hzy
 * {@code @description}
 * {@code @date}  2023-08-07
 **/
public class EventPublisher {
    List<ResultListener> listeners = new ArrayList<>();

    private static final EventPublisher me = new EventPublisher();
    private EventPublisher(){
        listeners.add(new ResultEventListener());
    }

    public static EventPublisher getInstance(){

        return me;
    }
    public void addListener(ResultListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ResultListener listener) {
        listeners.remove(listener);
    }

    public void publishEvent(ResultEvent event) {
        for (ResultListener listener : listeners) {
            listener.onEvent(event);
        }
    }
}
