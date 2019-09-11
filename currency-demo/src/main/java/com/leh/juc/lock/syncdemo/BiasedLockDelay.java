package com.leh.juc.lock.syncdemo;

import com.leh.juc.lock.MyLock;
import org.openjdk.jol.info.ClassLayout;

/**
 * @Auther: leh
 * @Date: 2019/9/4 17:26
 * @Description:synchronized原理 --  偏向锁延迟
 * * 对象头 64bit 组成
 *
 *                              |  lock:2
 *
 *
    32位JVM的Mark Word存储结构
    锁状态	    23 bits	    2 bits	    4 bits	    1 bit	    2 bits
    轻量级锁 	    指向栈中锁记录的指针 	                              00
    无锁状态 	    hash code 	            分代年龄 	      0 	      01
    偏向锁 	    Thread ID 	epoch 	    分代年龄 	      1 	      01
    重量级锁 	    指向监视器（monitor）的指针 	                      10
    GC标记 	            0 	                                      11
 */
public class BiasedLockDelay {

    static MyLock lock = new MyLock();


    public static void main(String[] args) {

        System.out.println("-------------------start-----------------------");

        //转16进制  jvm得到的hashcode
        System.out.println(Integer.toHexString(lock.hashCode()));


        System.out.println(ClassLayout.parseInstance(lock).toPrintable());

        synchronized (lock) {                   // 单线程执行，无竞争，应为偏向锁


            System.out.println(Thread.currentThread().getName() + "》》》lock ing");
            System.out.println(Thread.currentThread().getName() + ">>>>>>>>>>>>" + ClassLayout.parseInstance(lock).toPrintable());
        }


        System.out.println("-------------------end-----------------------");
    }

    /*
        不设置vm参数 ----》10100000  ------》输出锁状态 00 轻量级锁  而不是 偏向锁
        output:
        main>>>>>>>>>>>>MyLock object internals:
         OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
              0     4        (object header)                           a0 f8 68 03 (10100000 11111000 01101000 00000011) (57211040)
              4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
              8     4        (object header)                           43 c1 00 f8 (01000011 11000001 00000000 11111000) (-134168253)
             12     4        (loss due to the next object alignment)
        Instance size: 16 bytes
        Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

        设置vm参数 -XX:BiasedLockingStartupDelay=0  关闭延迟
     */


    /*
        关闭偏向锁
        JVM --》 默认将偏向锁延迟 4s
        偏向锁是Java 1.6新添加的内容，并且是jdk默认启动的选项，可以通过-XX:-UseBiasedLocking 来关闭偏向锁。
        另外，偏向锁默认不是立即就启动的，在程序启动后，通常有几秒的延迟，可以通过命令 -XX:BiasedLockingStartupDelay=0来关闭延迟
        为什么延迟？
        jvm运行main 方法时，不仅启动main线程，同时jvm需要执行自己的一些线程，去执行自己的代码，其中有很多加了关键字 synchronized，
        如果不延迟，jvm第一次执行就会加上偏向锁。而且jvm确定将来还会有其他线程来执行加了关键字的代码，需要撤销偏向锁并且膨胀，这个过程很耗性能
        所以，需要延迟，直接给轻量锁

     */


}
