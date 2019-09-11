package com.leh.juc.lock.syncdemo;

import com.leh.juc.lock.MyLock;
import org.openjdk.jol.info.ClassLayout;

/**
 * @Auther: leh
 * @Date: 2019/9/4 17:26
 * @Description:synchronized原理 --重量级锁
 * * 对象头 64bit 组成
 *        前62位
 *        ptr_to_heavyweight_monitor:62                lock:2
 */
public class HeavyWeightLock {

    static MyLock lock = new MyLock();


    public static void main(String[] args) {

        System.out.println("-------------------start-----------------------");


        //转16进制  jvm得到的hashcode
        System.out.println(Integer.toHexString(lock.hashCode()));


        System.out.println(Thread.currentThread().getName() + "》》》" + ClassLayout.parseInstance(lock).toPrintable());

        synchronized (lock) {                   // ------------------------ 1
                                                /*
                                                    当main线程第一次执行到这里对lock加锁，没有竞争 --》加偏向锁 --》对象头的64bit会发生变化
                                                    通过CAS将上锁的线程id 存到对象头中
                                                 */

            System.out.println(Thread.currentThread().getName() + "》》》lock ing");
        }

        new Thread(() -> {
            test();                             // ------------------------ 2
            try {
                Thread.sleep(1000 * 5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                                                /*
                                                    当存在另外一个线程交替获取同一把锁且没有竞争，此时 由偏向锁  膨胀为 轻量级锁

                                                 */
        }, "t2").start();


        new Thread(() -> {
            test();                             // ------------------------ 3

                                                /*
                                                    此刻又有另外一个线程同时去竞争同一把锁，此时 轻量级锁  膨胀为 重量级锁
                                                    会变成jdk1.6之前的调用操作系统函数 mutex 完成互斥

                                                 */
        }, "t3").start();


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
     * 1.6 --》 有多个线程执行 {
     *
     *     1》多个线程交替执行 t1 执行完 t2执行 。。。不存在同时竞争 ---》轻量级锁
     *        main线程中代码顺序执行 。先执行 1 处同步代码块 ，执行完，再执行 2处 创建线程执行 test方法
     *
     *     2》多个线程同时执行（竞争）  ----》 重量级锁
     *
     *
     *  public synchronized(this) void test(){ 锁的是调用方法的对象

        }
     *
     */

    public static void test(){
        synchronized (lock){
            System.out.println(Thread.currentThread().getName() + "》》》》lock ing");
            System.out.println(Thread.currentThread().getName() + "》》》" + ClassLayout.parseInstance(lock).toPrintable());
        }
    }


}
