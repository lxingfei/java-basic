package com.leh.netty.exceptionspread;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;

/**
 * @Auther: leh
 * @Date: 2019/11/6 16:21
 * @Description:
 */
public class ExceptionOutboundHandlerB extends ChannelOutboundHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("ExceptionOutboundHandlerB.exceptionCaught");
        ctx.fireExceptionCaught(cause);
    }
}
