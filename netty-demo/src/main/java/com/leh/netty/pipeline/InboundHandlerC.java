package com.leh.netty.pipeline;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Auther: leh
 * @Date: 2019/11/6 16:21
 * @Description:
 */
public class InboundHandlerC extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("InboundHandlerC: " + msg);
        ctx.fireChannelRead(msg);
    }
}
