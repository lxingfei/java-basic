package com.leh.netty.performance.thread;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.ThreadLocalRandom;

@ChannelHandler.Sharable
public class ServerBusinessHandler extends SimpleChannelInboundHandler<ByteBuf> {
    public static final ChannelHandler INSTANCE = new ServerBusinessHandler();


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) {
        ByteBuf data = Unpooled.directBuffer();
        data.writeBytes(msg);
        //程序的性能都是在getResult这个地方被堵住了，基于reactor模型，最终一条线程可能影响这条线程管理的所有的连接
        //解决办法：将比较耗时的部分扔到业务线程池后台执行
        Object result = getResult(data);
        ctx.channel().writeAndFlush(result);
    }

    protected Object getResult(ByteBuf data) {
        // 90.0% == 1ms
        // 95.0% == 10ms   1000 50 > 10ms
        // 99.0% == 100ms  1000 10 > 100ms
        // 99.9% == 1000ms 1000 1 > 1000ms
        int level = ThreadLocalRandom.current().nextInt(1, 1000);

        int time;
        if (level <= 900) {
            time = 1;
        } else if (level <= 950) {
            time = 10;
        } else if (level <= 990) {
            time = 100;
        } else {
            time = 1000;
        }

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }

        return data;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // ignore
    }
}
