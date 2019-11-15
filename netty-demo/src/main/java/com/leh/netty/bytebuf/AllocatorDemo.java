package com.leh.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;

/**
 * @Auther: leh
 * @Date: 2019/11/13 16:57
 * @Description:
 */
public class AllocatorDemo {
    public static void main(String[] args) {
        PooledByteBufAllocator allocator = PooledByteBufAllocator.DEFAULT;
        //申请一块内存
        ByteBuf byteBuf = allocator.directBuffer(16);
        //释放内存
        byteBuf.release();

    }
}
