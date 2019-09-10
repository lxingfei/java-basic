package com.leh.lock.syncdemo;

import com.leh.lock.MyLock;
import com.leh.lock.MyLock2;
import org.openjdk.jol.info.ClassLayout;

/**
 * @Auther: leh
 * @Date: 2019/9/4 17:26
 * @Description:
 */
public class WhereObjectLockedDemo {

    static MyLock lock = new MyLock();
    static MyLock2 lock2 = new MyLock2();

    public static void main(String[] args) {

        System.out.println("start");

        //1 什么样子的锁？

        /**
         * 列举java当中的锁
         */

        //打印对象的布局
        System.out.println("----------------对什么都没有的类上锁---------------");

        System.out.println(lock.hashCode());

        //转16进制  jvm得到的hashcode
        System.out.println(Integer.toHexString(lock.hashCode()));


        System.out.println(ClassLayout.parseInstance(lock).toPrintable());

        synchronized (lock) {
            //sync 锁住的是对象的对象头，不是代码块
            // --》 加锁其实是修改了lock对象的对象头 ,对象头的结构包括堆对象的布局，类型，GC状态，同步状态和标识哈希码等基本信息

            System.out.println("lock ing");
        }

         /* 64位 jvm 规范 一个对象的大小是8的整数倍

        com.leh.lock.MyLock object internals:
         OFFSET  SIZE   TYPE DESCRIPTION                                        VALUE
              0     4        (object header)     -->对象头                      01 00 00 00 (00000001 00000000 00000000 00000000) (1)
              4     4        (object header)     -->对象头                      00 00 00 00 (00000000 00000000 00000000 00000000) (0)
              8     4        (object header)     -->对象头                      43 c1 00 f8 (01000011 11000001 00000000 11111000) (-134168253)
             12     4        (loss due to the next object alignment)  -->对齐字节  (对象头 4 + 4 + 4 12字节 不是8的倍数 ，需补足16字节，于是补4个字节)
        Instance size: 16 bytes
        Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

     */

        System.out.println("----------------对有成员变量的类上锁---------------");

        System.out.println(ClassLayout.parseInstance(lock2).toPrintable());

        /*
            com.leh.lock.MyLock2 object internals:
             OFFSET  SIZE     TYPE DESCRIPTION                               VALUE
                  0     4          (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
                  4     4          (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
                  8     4          (object header)                           81 c1 00 f8 (10000001 11000001 00000000 11111000) (-134168191)
                 12     4          (alignment/padding gap)
                 16     8   double MyLock2.d                                 0.0
            Instance size: 24 bytes  --（对象头[ 4 + 4 + 4 ] + 实例数据 [8] + 填充字节[4]）
            Space losses: 4 bytes internal + 0 bytes external = 4 bytes total

         */

        synchronized (lock2) {//sync 锁住的是对象，不是代码块
            System.out.println("lock2 lock ing");
        }

        System.out.println("end");
    }

/*
    synchronized对对象头加锁，对象头又包括 markword、klass pointer，那么究竟是在那一部分加锁的？
    //  64 bits:
    //  --------
    //  unused:25 hash:31 -->| unused:1   age:4    biased_lock:1 lock:2 (normal object)
    //  JavaThread*:54 epoch:2 unused:1   age:4    biased_lock:1 lock:2 (biased object)
    //  PromotedObject*:61 --------------------->| promo_bits:3 ----->| (CMS promoted object)
    //  size:64 ----------------------------------------------------->| (CMS free block)
    //
    //  unused:25 hash:31 -->| cms_free:1 age:4    biased_lock:1 lock:2 (COOPs && normal object)
    //  JavaThread*:54 epoch:2 cms_free:1 age:4    biased_lock:1 lock:2 (COOPs && biased object)
    //  narrowOop:32 unused:24 cms_free:1 unused:4 promo_bits:3 ----->| (COOPs && CMS promoted object)
    //  unused:21 size:35 -->| cms_free:1 unused:7 ------------------>| (COOPs && CMS free block)


    java 当中 拿某个东西计算时不能拿位数计算，但可以拿字节
    normal object 无锁情况下
    unused:25 hash:31 31无法拿整数字节，放一起确保可以按字节拿出来计算 --》前56位存放hashcode

    00000001 00000000 00000000 00000000

    00000000 00000000 00000000 00000000



    疑问：前56位存储hashcode,怎么只有第一个字节有值

    00000001===》转换16进制 0x78fd43----
    00000000===》转换16进制 0
    why?

    {
        本身这个对象没有hashcode，
        只有调用hashcode方法的对象才有hashcode
    }

    markword = 8 byte = 64bit
    大小端模式 把数据的高字节保存在内存的高地址中

    age:4 GC的分代年龄 用4位标识 0000 最大值16
        GC{
            to--from 16次 ----> 进入老年代
        }
    biased_lock:1  表示是否是偏向锁
    lock:2  锁标志位 00 未加锁 01加了锁

    加锁修改的是 后3位  biased_lock + lock
    jdk1.4 引入nio
    jdk1.6 升级锁的概念

    偏向锁
    轻量级锁
    重量级锁
 */


}
