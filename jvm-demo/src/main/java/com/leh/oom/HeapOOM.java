package com.leh.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * Java堆内存溢出异常测试
     * 将堆的最小值-Xms参数与最大值-Xmx参数设置为一样即可避免堆自动扩展
     * 通过参数-XX：+HeapDumpOnOutOf-MemoryError可以让虚拟机
     * 在出现内存溢出异常的时候Dump出当前的内存堆转储快照以便进行事后分析
 * @Description VM Args：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * @Author lveh
 * @Date 2021/7/16 22:32
 * @Version 1.0
 **/
public class HeapOOM {
    static class OOMObject {
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true) {
            list.add(new OOMObject());
        }
    }

    /**
     * java.lang.OutOfMemoryError: Java heap space
     * Dumping heap to java_pid28644.hprof ...
     * Heap dump file created [28230353 bytes in 0.159 secs]
     * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     * 	at java.util.Arrays.copyOf(Arrays.java:3210)
     * 	at java.util.Arrays.copyOf(Arrays.java:3181)
     * 	at java.util.ArrayList.grow(ArrayList.java:265)
     * 	at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:239)
     * 	at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:231)
     * 	at java.util.ArrayList.add(ArrayList.java:462)
     * 	at com.leh.oom.HeapOOM.main(HeapOOM.java:21)
     */
}
