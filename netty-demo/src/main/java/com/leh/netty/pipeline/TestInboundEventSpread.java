package com.leh.netty.pipeline;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.AttributeKey;

/**
 * @Auther: leh
 * @Date: 2019/10/25 14:47
 * @Description: netty服务端启动标准demo
 */
public final class TestInboundEventSpread {

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
                .childHandler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new InboundHandlerA());
                        ch.pipeline().addLast(new InboundHandlerB());
                        ch.pipeline().addLast(new InboundHandlerC());
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
