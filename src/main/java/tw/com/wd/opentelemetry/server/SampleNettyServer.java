package tw.com.wd.opentelemetry.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import tw.com.wd.netty.server.NettyServerInitializer;

public class SampleNettyServer {
    private String ipAddress;
    private int port;



    public static void main(String[] args) {
        SampleNettyServer server = new SampleNettyServer("127.0.0.1", 9080);
        server.start();
    }

    public SampleNettyServer(String host, int port) {
        super();
        this.ipAddress = host;
        this.port = port;
    }

    public void start() {
        // Configure the server.
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.option(ChannelOption.SO_BACKLOG, 1024);

            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(new NettyServerInitializer());

            Channel ch = b.bind(this.ipAddress, this.port).sync().channel();

            System.out.printf("Server started on http://%s:%d\n", this.ipAddress, this.port);

            ch.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
