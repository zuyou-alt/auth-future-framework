package auth.future.component.websocket.event;


import auth.future.component.common.model.socket.WSMessageInfo;
import auth.future.component.websocket.send.SendResultStatus;

/**
 * @author hzy
 * {@code @description}
 * {@code @date}  2023-08-07
 **/
public class ResultEvent {
    private WSMessageInfo wsMessageInfo;

    private SendResultStatus result;

    public WSMessageInfo getWsMessageInfo() {
        return wsMessageInfo;
    }

    public void setWsMessageInfo(WSMessageInfo wsMessageInfo) {
        this.wsMessageInfo = wsMessageInfo;
    }

    public SendResultStatus getResult() {
        return result;
    }

    public void setResult(SendResultStatus result) {
        this.result = result;
    }
}
