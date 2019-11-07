package com.leh.netty.exceptionspread;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Auther: leh
 * @Date: 2019/11/6 19:09
 * @Description: 终极异常处理器
 */
public class ExceptionCaughtHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //根据异常的类型进行处理
        if (cause instanceof BusinessException) {
            System.out.println("BusinessException");
        }
    }
}
