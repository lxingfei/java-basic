package com.leh.netty.pipeline;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther: leh
 * @Date: 2019/11/5 18:06
 * @Description: 权限校验
 */
public class AuthHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        if (pass(msg)) {
            //校验通过将当前节点删除
            ctx.pipeline().remove(this);
        } else {
            //校验不通过直接关闭连接
            ctx.close();
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("test 回调删除");
    }

    private boolean pass(ByteBuf password) {
        return true;
    }
}
