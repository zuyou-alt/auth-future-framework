package auth.future.component.websocket.event;

import auth.future.component.session.LogContext;
import auth.future.component.common.model.socket.WSMessageInfo;
import auth.future.component.websocket.send.SendResultStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * （1） 处理离线消息
 * @author hzy
 * {@code @description} 接收发送结果
 * {@code @date}  2023-08-07
 **/
public class ResultEventListener implements ResultListener {
    Logger log = LoggerFactory.getLogger(ResultEventListener.class);

    @Override
    public void onEvent(ResultEvent resultEvent) {
        SendResultStatus result = resultEvent.getResult();
        WSMessageInfo wsMessageInfo = resultEvent.getWsMessageInfo();
        if (result.equals(SendResultStatus.SUCCESS)) {
            String message = String.format("send: %s, receive: %s,sendTime: %s",wsMessageInfo.getSendId(),wsMessageInfo.getReceiveId(),wsMessageInfo.getSendTime());
            LogContext.infoTimeLog("websocket消息发送成功！"+message);
            return;
        }
        WSMessageInfo imMessageInfo = resultEvent.getWsMessageInfo();
        saveOfflineMessage(imMessageInfo);
    }

    /**
     * 将消息存储至redis
     * 后续优化 进行持久化
     * @param imMessageInfo 消息内容
     */
    protected void saveOfflineMessage(WSMessageInfo imMessageInfo){
        // 存储离线消息
        String receiveId = imMessageInfo.getReceiveId();
        LocalDateTime sendTime = imMessageInfo.getSendTime();
        long epochMilli = sendTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
//        RedisUtils.zSetAdd(ImKeys.OFFLINE_KEY+receiveId,imMessageInfo,epochMilli);
    }


}
