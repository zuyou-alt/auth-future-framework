package auth.future.component.websocket.channel;

import auth.future.component.common.exception.ImChannelManagerException;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 管理客户端连接通道
 */
public class ImChannelManager {
   static Logger log = LoggerFactory.getLogger(ImChannelManager.class);
    /**
     * ChannelGroup是Netty中用于管理一组相关联的Channel的特殊Channel。
     * 它提供了一些便捷的方法来管理和操作Channel，比如添加/移除Channel、向所有Channel广播消息、关闭所有Channel等。
     * 通过ChannelGroup，可以方便地对多个Channel进行集中管理和控制。
     */
    private  static final ChannelGroup globalGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private  static final ConcurrentMap<String, ChannelId> channelMap = new ConcurrentHashMap<>();

    private  static final ConcurrentMap<ChannelId, String> userMap = new ConcurrentHashMap<>();

    /**
     * 添加一个连接通道
     * @param userId 用户ID
     * @param channel 对应的通道
     */
    public static void addChannel(String userId, Channel channel){
        try {
            globalGroup.add(channel);
            channelMap.put(userId,channel.id());
            userMap.put(channel.id(),userId);
        }catch (Exception e){
            throw new ImChannelManagerException("连接通道绑定失败! ",e);
        }
    }

    /**
     * 删除连接通道
     * @param channel 通道对象
     */
    public static void removeChannel(Channel channel){
        try {
            globalGroup.remove(channel);
            String userId = userMap.get(channel.id());
            channelMap.remove(userId);
            log.info("连接断开，userId={}",userId);
        }catch (Exception e){
            throw new ImChannelManagerException("解除绑定失败! ",e);
        }
    }

    /**
     * 查找对应用户的连接通道
     * @param userId 用户ID
     * @return 连接通道
     */
    public static Channel getChannel(String userId){
        try {
            ChannelId channelId = channelMap.get(userId);
            if (channelId==null){
                return null;
            }
            return globalGroup.find(channelId);
        }catch (Exception e){
            throw new ImChannelManagerException("并未找到用户的有效连接 ! ",e);
        }
    }

    public static ChannelGroup getGlobalGroup(){
        return globalGroup;
    }

    public static ConcurrentMap<String, ChannelId> getChannelMap(){
        return channelMap;
    }
}
