package com.leh.netty.encoder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Auther: leh
 * @Date: 2019/11/15 16:51
 * @Description:
 */
public class EncoderServer {
    public static void main(String[] args) {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, work).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new Encoder());
                            ch.pipeline().addLast(new BizHandler());
                        }
                    });
            ChannelFuture future = bootstrap.bind(8888).sync();
            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }

    }
}
