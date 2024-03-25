package auth.future.component.websocket.channel;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class ImChannelInitializer extends ChannelInitializer<SocketChannel> {
    /**
     * （1）HttpServerCodec： 将字节解码为HttpRequest\HttpContent和LastHttp-Content, 并将HttpRequest、HttpContent 和Last-HttpContent 编码为字节.
     * （2）ChunkedWriteHandler： 处理分块写入数据的ChannelHandler。它可以将大数据分割为较小的块进行逐个写入，以减少内存占用。
     * （3）HttpObjectAggregator：HttpObjectAggregator是Netty中用于聚合Http请求或响应消息体的ChannelHandler。它能够将分段的HttpContent聚合成一个完整的FullHttpRequest或FullHttpResponse，方便后续处理。
     *      需要在ChannelPipeline中添加HttpObjectAggregator，并确保它的位置正确，通常在Http编解码器之后。
     * （4）自定义处理器
     * （5）WebSocketServerProtocolHandler： WebSocketServerProtocolHandler是Netty中用于实现WebSocket服务器端的握手协议和帧处理的ChannelHandler。它能够将HTTP请求升级为WebSocket协议，自动处理WebSocket的握手过程，并对WebSocket帧进行处理和转发。
     * 需要在ChannelPipeline中添加WebSocketServerProtocolHandler，并确保它的位置正确，通常在Http编解码器和HttpObjectAggregator之后。
     */
    @Override
    protected void initChannel(SocketChannel ch) {
        ch.pipeline()
                .addLast(new HttpServerCodec())
                .addLast(new ChunkedWriteHandler())
                .addLast(new HttpObjectAggregator(64 * 1024))
                .addLast(new ImChannelHandler())
                .addLast(new WebSocketServerProtocolHandler("/"));
    }
}
