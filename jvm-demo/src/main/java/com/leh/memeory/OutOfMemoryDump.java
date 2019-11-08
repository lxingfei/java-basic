package com.leh.memeory;

import com.leh.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Auther: leh
 * @Date: 2019/9/4 10:10
 * @Description:
 */
public class OutOfMemoryDump {

    /**
     *
     * JVM参数：
     * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\oom\oom.dump
     */

    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();

        int i = 0;

        while (true) {
            list.add(new User(i++, UUID.randomUUID().toString()));
            i++;
        }

    }

    /*

        Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
            at java.lang.Long.toUnsignedString0(Long.java:356)
            at java.lang.Long.toHexString(Long.java:272)
            at java.util.UUID.digits(UUID.java:386)
            at java.util.UUID.toString(UUID.java:377)
            at com.leh.memeory.OutOfMemoryDump.main(OutOfMemoryDump.java:28)

     */
}
