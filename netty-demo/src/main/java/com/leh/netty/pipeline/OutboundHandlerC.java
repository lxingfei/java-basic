package com.leh.netty.pipeline;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @Auther: leh
 * @Date: 2019/11/6 17:37
 * @Description:
 */
public class OutboundHandlerC extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("OutboundHandlerC: " + msg);
        //继续向下传播
        ctx.write(msg, promise);
    }
}
