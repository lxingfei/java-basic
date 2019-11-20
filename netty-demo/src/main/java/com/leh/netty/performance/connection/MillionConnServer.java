package com.leh.netty.performance.connection;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import static com.leh.netty.performance.connection.Constant.BEGIN_PORT;
import static com.leh.netty.performance.connection.Constant.END_PORT;

/**
 * @Auther: leh
 * @Date: 2019/11/19 17:31
 * @Description:
 * 服务端绑定100个端口
 * 修改局部文件句柄限制
 * 修改全局文件句柄限制
 */
public class MillionConnServer {
    public static void main(String[] args) {
        new MillionConnServer().start(BEGIN_PORT, END_PORT);
    }

    public void start(int beginPort, int endPort){
        System.out.println("server starting....");
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_REUSEADDR, true);
       bootstrap.childHandler(new ConnectionCountHandler());

        for (int i = 0; i < endPort; i++) {
            int port = beginPort + i;
            bootstrap.bind(port).addListener((ChannelFutureListener) future -> {
                System.out.println("bind success in port " + port);
            });
        }
        System.out.println("server started!");
    }
}
