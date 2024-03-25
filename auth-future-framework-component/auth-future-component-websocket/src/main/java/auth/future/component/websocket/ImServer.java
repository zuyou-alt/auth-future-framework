package auth.future.component.websocket;

import auth.future.component.websocket.channel.ImChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImServer {

    public static void main(String[] args) {
        startServer(4356);
    }


    public static void startServer(Integer port){
        Logger logger = LoggerFactory.getLogger(ImServer.class);
        // 处理连接请求
        EventLoopGroup connectGroup = new NioEventLoopGroup();
        // 处理业务请求
        EventLoopGroup serviceGroup = new NioEventLoopGroup();
        // 创建服务端对象
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(connectGroup,serviceGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ImChannelInitializer());
        try{
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            logger.info("websocket starting by {}",port);
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
    }
}
