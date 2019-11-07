package com.leh.netty.exceptionspread;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Auther: leh
 * @Date: 2019/11/6 16:21
 * @Description:
 */
public class ExceptionInboundHandlerC extends ChannelInboundHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("ExceptionInboundHandlerC.exceptionCaught");
        ctx.fireExceptionCaught(cause);
    }
}
