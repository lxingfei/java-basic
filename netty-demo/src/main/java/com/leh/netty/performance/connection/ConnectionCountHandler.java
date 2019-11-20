package com.leh.netty.performance.connection;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: leh
 * @Date: 2019/11/19 17:38
 * @Description:
 */
public class ConnectionCountHandler extends ChannelInboundHandlerAdapter {

    private AtomicInteger connNum = new AtomicInteger();

    public ConnectionCountHandler(){
        //每2s统计一次连接数
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            System.out.println("connNum is : " + connNum.get());
        }, 0, 2, TimeUnit.SECONDS);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        connNum.getAndIncrement();

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        connNum.decrementAndGet();
    }
}
