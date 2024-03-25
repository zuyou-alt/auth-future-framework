package auth.future.api.websocket;

import auth.future.component.common.model.socket.WSMessageInfo;

/**
 * websocket消息接口
 * @author hzy
 * @since 2024-03-09
 **/
public interface WSMessageServiceApi {
    /**
     * 发送websocket
     * @param wsMessageInfo 消息内容
     */
    void sendMessage(WSMessageInfo wsMessageInfo);

    /**
     * 发送websocket
     * @param wsMessageInfo 消息内容
     */
    void sentMessageAll(WSMessageInfo wsMessageInfo);
}
