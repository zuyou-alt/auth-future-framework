package auth.future.component.websocket.send;

import auth.future.component.websocket.channel.ImChannelManager;
import auth.future.component.common.model.socket.WSMessageInfo;
import auth.future.component.websocket.event.EventPublisher;
import auth.future.component.websocket.event.ResultEvent;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.List;

/**
 * 消息发送器
 */
public class WSMessageSender {
    /**
     * 发送所有用户消息
     * @param wsMessageInfo 消息内容
     */
    public static void sendAllMsg(WSMessageInfo wsMessageInfo){
        ChannelGroupFuture channelFutures = ImChannelManager.getGlobalGroup().writeAndFlush(new TextWebSocketFrame(wsMessageInfo.toString()));
        channelFutures.addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()){
                publicEvent(SendResultStatus.SUCCESS,wsMessageInfo);
            }else {
                publicEvent(SendResultStatus.FAIL,wsMessageInfo);
            }
        });
    }

    /**
     * 发送个人消息
     * @param wsMessageInfo 消息内容
     */
    public static void sendUser(WSMessageInfo wsMessageInfo) throws InterruptedException {
        Channel channel = ImChannelManager.getChannel(wsMessageInfo.getReceiveId());
        if(channel==null) {
            publicEvent(SendResultStatus.OFFLINE,wsMessageInfo);
            return;
        }
        ChannelFuture channelFuture = channel.writeAndFlush(new TextWebSocketFrame(JSONObject.toJSONString(wsMessageInfo)));
        channelFuture.addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()){
                publicEvent(SendResultStatus.SUCCESS,wsMessageInfo);
            }else {
                publicEvent(SendResultStatus.FAIL,wsMessageInfo);
            }
        });
    }

    /**
     * 发送群组消息
     * @param wsMessageInfo 消息内容
     */
    public static void sendGroup(WSMessageInfo wsMessageInfo){
        List<String> receiveIdList = wsMessageInfo.getReceiveIdList();
        ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
        for (String s : receiveIdList){
            Channel channel = ImChannelManager.getChannel(s);
            if (channel==null){
                publicEvent(SendResultStatus.OFFLINE,wsMessageInfo);
                continue;
            }
            channels.add(channel);
        }
        ChannelGroupFuture channelFutures = channels.writeAndFlush(new TextWebSocketFrame(wsMessageInfo.toString()));
        channelFutures.addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()){
                publicEvent(SendResultStatus.SUCCESS,wsMessageInfo);
            }else {
                publicEvent(SendResultStatus.FAIL,wsMessageInfo);
            }
        });
    }

    public static void publicEvent(SendResultStatus result,WSMessageInfo wsMessageInfo){
        // 创建事件发布者
        EventPublisher instance = EventPublisher.getInstance();
        // 创建事件对象
        ResultEvent event = new ResultEvent();
        event.setResult(result);
        event.setWsMessageInfo(wsMessageInfo);
        // 发布事件
        instance.publishEvent(event);
    }
}
