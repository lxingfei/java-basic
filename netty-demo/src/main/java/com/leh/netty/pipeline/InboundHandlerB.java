package com.leh.netty.pipeline;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Auther: leh
 * @Date: 2019/11/6 16:21
 * @Description:
 */
public class InboundHandlerB extends ChannelInboundHandlerAdapter {
    /**
     * channelHandler收到激活事件后会对收到对象进行打印，并继续传播
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("InboundHandlerB: " + msg);
        //通过context调用fireChannelRead 会将事件从当前节点开始传播
        ctx.fireChannelRead(msg);
    }

    /**
     * 通道被激活的时候拿到对应的 pipeline ，激活一个ChannelRead事件
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //通过channel的Pipeline调用fireChannelRead
        //会将事件从pipeline的headContext节点传播
        ctx.channel().pipeline().fireChannelRead("hello netty");
    }
}
