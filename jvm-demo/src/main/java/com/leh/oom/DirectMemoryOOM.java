package com.leh.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/** 本机直接内存溢出
 * @Description 使用unsafe分配本机内存
 * @Author lveh
 * @Date 2021/7/16 14:26
 * @Version 1.0
 **/
public class DirectMemoryOOM {
    /**
     * VM Args：-Xmx20M -XX:MaxDirectMemorySize=10M
     */
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }

    /**
     * Exception in thread "main" java.lang.OutOfMemoryError
     * 	at sun.misc.Unsafe.allocateMemory(Native Method)
     * 	at com.leh.oom.DirectMemoryOOM.main(DirectMemoryOOM.java:24)
     */
}
