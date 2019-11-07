package com.leh.netty;

import com.leh.netty.pipeline.AuthHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.AttributeKey;

/**
 * @Auther: leh
 * @Date: 2019/10/25 14:47
 * @Description: netty服务端启动标准demo
 */
public final class StandardServer {

    public static void main(String[] args) {
        // bossGroup 对应 socket编程中 server端 的 线程
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // workGroup 对应 socket编程中 client端 的 线程
        EventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childAttr(AttributeKey.newInstance("childAttr"), "childAttrValue")
                .handler(new ServerHandler())
                .childHandler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new AuthHandler());
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter());
                        ch.pipeline().addLast(new ChannelOutboundHandlerAdapter());
                    }
                });
        try {
            //服务端创建的入口 bind()
            ChannelFuture channelFuture = bootstrap.bind(8888).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
