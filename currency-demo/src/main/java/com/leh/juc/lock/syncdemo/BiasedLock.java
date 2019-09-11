package com.leh.juc.lock.syncdemo;

import com.leh.juc.lock.MyLock;
import org.openjdk.jol.info.ClassLayout;

/**
 * @Auther: leh
 * @Date: 2019/9/4 17:26
 * @Description:synchronized原理 --偏向锁
 *
 * 对象头 64bit 组成
 *          如何存处id --》CAS替换
 *          线程id     时代，朝代           分代年龄   是否偏向       锁标志位
 * //  JavaThread*:54 epoch:2 cms_free:1 age:4    biased_lock:1 lock:2 (COOPs && biased object)
 */
public class BiasedLock {

    static MyLock lock = new MyLock();


    public static void main(String[] args) {

        System.out.println("-------------------start-----------------------");

        //转16进制  jvm得到的hashcode
        System.out.println(Integer.toHexString(lock.hashCode()));


        System.out.println(ClassLayout.parseInstance(lock).toPrintable());

        synchronized (lock) {                   // ------------------------ 1

                                                /*
                                                    当main线程第一次执行到这里对lock加锁，没有竞争 --》加偏向锁 --》对象头的64bit会发生变化
                                                    通过CAS将上锁的线程id 存到对象头中
                                                 */

            System.out.println(Thread.currentThread().getName() + "》》》lock ing one ");
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        }

        synchronized (lock) {                   // ------------------------ 2
                                                /*
                                                    当 main线程第二次执行到这里就不会基于CAS上锁，直接对比当前线程id与lock对象里面
                                                    存的线程id 是否相同，若相同，直接拿到锁，直接执行同步代码，
                                                    不会执行任何操作系统级别甚至cpu级别的任何指令，
                                                    仅做了简单的对比判断，甚至认为根本没有加锁 ==》 等同于3处代码

                                                 */
            System.out.println(Thread.currentThread().getName() + "》》》lock ing two ");
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        }

        //synchronized (lock) {                   // ------------------------ 3

            System.out.println(Thread.currentThread().getName() + "》》》lock ing three ");

        //}


        System.out.println("-------------------end-----------------------");
    }

/*

    java里程碑
    jdk1.4 引入nio
    jdk1.6 升级锁的概念

    偏向锁
    轻量级锁
    重量级锁
 */


    /**
     * 程序员对于写的方法
     * 很多时候不清楚是否会被多线程同时调用，
     * 为避免多线程并发访问的问题，确保安全，通常会在方法上加synchronized 关键字
     * 但是加了之后：
     * jdk1.6之前，不管谁来调用，都会涉及 os mutex  --》 linux系统实现互斥 --》性能低下
     * sun公司经过研究，很多同步方法在大多数情况下，都只有一个线程来调用，仅仅是程序员出于安全考虑，加了同步。
     * 实际开发中，很多时候并不存在资源竞争，但依然使用了synchronized，引发 os mutex 性能问题
     * 于是，在jdk1.6之后，sun公司针对这种没有资源竞争的同步代码，做了优化--》偏向锁
     *
     * 1.6 --》 os mutex
     * 1.6 --》 没有资源竞争的同步代码 --》偏向锁  只有一个线程调用
     *
     *
     * 1.6 --》 有多个线程执行 {
     *
     *     1》多个线程交替执行 t1 执行完 t2执行 。。。不存在同时竞争 ---》轻量级锁
     *
     *
     *     2》多个线程同时执行（竞争）  ----》 重量级锁
     *
     *
     *  public synchronized(this) void test(){ 锁的是调用方法的对象

        }
     *
     */


}
