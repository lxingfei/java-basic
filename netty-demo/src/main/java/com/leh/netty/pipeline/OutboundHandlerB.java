package com.leh.netty.pipeline;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: leh
 * @Date: 2019/11/6 17:37
 * @Description:
 */
public class OutboundHandlerB extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("OutboundHandlerB: " + msg);
        //继续向下传播
        ctx.write(msg, promise);
    }

    /**
     * 做一个定时器的调用
     * 模拟实际项目中读到数据处理完毕后给客户端一个响应
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        ctx.executor().schedule(() -> {
            ctx.channel().write("hello netty");
            //ctx.write("hello netty");
        }, 3, TimeUnit.SECONDS);
    }
}
