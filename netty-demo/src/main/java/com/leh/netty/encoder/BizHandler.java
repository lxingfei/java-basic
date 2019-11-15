package com.leh.netty.encoder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Auther: leh
 * @Date: 2019/11/15 16:48
 * @Description:
 */
public class BizHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        User user = new User(18, "zhangsan");
        ctx.channel().writeAndFlush(user);
    }
}
