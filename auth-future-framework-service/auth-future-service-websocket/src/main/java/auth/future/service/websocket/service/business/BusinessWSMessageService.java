package auth.future.service.websocket.service.business;

import auth.future.api.websocket.WSMessageServiceApi;
import auth.future.component.common.context.CurrentContext;
import auth.future.component.common.model.socket.WSMessageInfo;
import auth.future.component.websocket.send.WSMessageSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author hzy
 * @since 2024-03-01
 **/
@Service
public class BusinessWSMessageService implements WSMessageServiceApi {

    @Override
    public void sendMessage(WSMessageInfo wsMessageInfo) {
        wsMessageInfo.setSendTime(LocalDateTime.now());
        try {
            WSMessageSender.sendUser(wsMessageInfo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sentMessageAll(WSMessageInfo wsMessageInfo) {

    }
}
