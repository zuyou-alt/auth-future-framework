package auth.future.component.websocket.channel;

import auth.future.component.common.exception.ImChannelManagerException;
import auth.future.component.common.model.socket.WSMessageInfo;
import auth.future.component.websocket.common.baseenum.MsgContentTypeEnum;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.Log4J2LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 处理客户端单个连接
 */
public class ImChannelHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

   static Logger log = LoggerFactory.getLogger(ImChannelHandler.class);

    /**
     * 读取客户端传入的数据
     * @param ctx 上下文对象
     * @param msg 客户端传入的数据
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest request){
            Channel channel = ctx.channel();
            String uri = request.uri();
            Map<String, String> params = parseParams(uri);
            String userId = params.get("userId");
            if (StrUtil.isBlank(userId)){
                throw new ImChannelManagerException("未知的连接用户！");
            }
            ImChannelManager.addChannel(userId,channel);
            request.setUri("/");
            log.info("连接成功！userId={}",userId);
        }
        super.channelRead(ctx, msg);
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
        WSMessageInfo wsMessageInfo = new WSMessageInfo();
        wsMessageInfo.setMsgContent("success");
        wsMessageInfo.setMsgContentType(MsgContentTypeEnum.CONNECT.getType());
        ctx.channel().writeAndFlush(new TextWebSocketFrame(JSONObject.toJSONString(wsMessageInfo)));
    }


    /**
     * 和服务端建立连接
     * @param ctx 连接上下文
     * @throws Exception 异常
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
    }

    /**
     * 和服务端断开连接
     * @param ctx 连接上下文
     * @throws Exception 异常
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        ImChannelManager.removeChannel(ctx.channel());
        super.handlerRemoved(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        InternalLogger logger = Log4J2LoggerFactory.getInstance(ImChannelHandler.class);
        logger.error("连接出现异常",cause);
        super.exceptionCaught(ctx, cause);
    }
    private Map<String,String> parseParams(String uri){
        Map<String,String> paramsMap = new HashMap<>();
        if (uri!=null && !uri.isEmpty()){
            if (uri.contains("?")){
                String paramsStr = uri.substring(uri.indexOf("?")+1);
                String[] paramsArr = paramsStr.split("&");
                for (String param : paramsArr) {
                    String[] split = param.split("=");
                    paramsMap.put(split[0], split[1]);
                }
            }
        }
        return paramsMap;
    }

}
