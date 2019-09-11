package com.leh.juc.lock.syncdemo;

import com.leh.juc.lock.MyLock;
import org.openjdk.jol.info.ClassLayout;

/**
 * @Auther: leh
 * @Date: 2019/9/4 17:26
 * @Description:synchronized原理 --无锁 （但可偏向状态）
 *
 * 对象头 64bit 组成
 *      无锁与偏向锁区别： 无锁没有偏向的线程id
 *  //  unused:25 hash:31 -->| cms_free:1 age:4    biased_lock:1 lock:2 (COOPs && normal object)
 */
public class NoLock {

    static MyLock lock = new MyLock();


    public static void main(String[] args) {

        System.out.println("-------------------start-----------------------");


        System.out.println("----------------对什么都没有的类上锁---------------");

        System.out.println(lock.hashCode());

        //转16进制  jvm得到的hashcode
        System.out.println(Integer.toHexString(lock.hashCode()));


        System.out.println(ClassLayout.parseInstance(lock).toPrintable());


        System.out.println("-------------------end-----------------------");
    }

/*

    java里程碑
    jdk1.4 引入nio
    jdk1.6 升级锁的概念

    无锁
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
