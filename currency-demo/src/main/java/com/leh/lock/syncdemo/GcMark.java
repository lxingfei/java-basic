package com.leh.lock.syncdemo;

import com.leh.lock.MyLock;
import org.openjdk.jol.info.ClassLayout;

/**
 * @Auther: leh
 * @Date: 2019/9/4 17:26
 * @Description:synchronized原理 --  GC 标记
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
public class GcMark {

    static MyLock lock = new MyLock();


    public static void main(String[] args) {

        System.out.println("-------------------start-----------------------");

        //转16进制  jvm得到的hashcode
        System.out.println(Integer.toHexString(lock.hashCode()));


        System.out.println(ClassLayout.parseInstance(lock).toPrintable());

        synchronized (lock) {                   // ------------------------ 1


            System.out.println(Thread.currentThread().getName() + "》》》lock ing");
            System.out.println(Thread.currentThread().getName() + ">>>>>>>>>>>>" + ClassLayout.parseInstance(lock).toPrintable());
        }

        System.out.println("-------------------end-----------------------");
    }



    public static void test(){
        synchronized (lock){
            System.out.println(Thread.currentThread().getName() + "》》》》lock ing");
        }
    }

    /*


        疑问？？？？
        lock 锁标志位 2位 最多能表示4个状态 怎么去表示5种锁状态？


        com.leh.lock.MyLock2 object internals:
         OFFSET  SIZE     TYPE DESCRIPTION                               VALUE
              0     4          (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
              4     4          (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
              8     4          (object header)                           81 c1 00 f8 (10000001 11000001 00000000 11111000) (-134168191)
             12     4          (alignment/padding gap)
             16     8   double MyLock2.d                                 0.0
        Instance size: 24 bytes  --（对象头[ 4 + 4 + 4 ] + 实例数据 [8] + 填充字节[4]）
        Space losses: 4 bytes internal + 0 bytes external = 4 bytes total


        //  JavaThread*:54 epoch:2    cms_free:1 age:4    biased_lock:1 lock:2 (COOPs && biased object)

        根据大小端存储  后面8位
        00000001  -- 》
        00000    0（是否偏向锁）  01 （无锁状态）

        无锁       00000     0   01
        偏向锁     00000     1   01
        轻量级锁   62 -------    00
        重量级锁   62 -------    10
        GC标记    62 -------    11


     */


}
