package com.leh.netty.performance.connection;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import static com.leh.netty.performance.connection.Constant.BEGIN_PORT;
import static com.leh.netty.performance.connection.Constant.END_PORT;

/**
 * @Auther: leh
 * @Date: 2019/11/19 17:52
 * @Description:
 */
public class MillionConnClient {

    private static final String SERVER_HOST = "127.0.0.1";

    public static void main(String[] args) {
        new MillionConnClient().start(BEGIN_PORT, END_PORT);
    }

    private void start(int beginPort, int endPort) {
        System.out.println("client starting....");
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_REUSEADDR, true);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) {
            }
        });

        int index = 0;
        int port;

        while (!Thread.interrupted()) {
            port = beginPort + index;
            try {
                ChannelFuture channelFuture = bootstrap.connect(SERVER_HOST, port);
                channelFuture.addListener((ChannelFutureListener) future -> {
                    if (!future.isSuccess()) {
                        System.out.println("connect failed, exit!");
                        System.exit(0);
                    }
                });
                channelFuture.get();
            } catch (Exception e) {
            }

            if (++index == endPort) {
                index = 0;
            }
        }
    }
}
