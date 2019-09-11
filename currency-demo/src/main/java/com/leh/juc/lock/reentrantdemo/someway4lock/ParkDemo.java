package com.leh.juc.lock.reentrantdemo.someway4lock;

import java.util.concurrent.locks.LockSupport;

/**
 * @Auther: leh
 * @Date: 2019/9/11 17:02
 * @Description:
 */
public class ParkDemo {
    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            testSync();
        }, "t1");

        t1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main线程睡眠1s醒来------1");

        //即时唤醒阻塞的线程
        LockSupport.unpark(t1);

        System.out.println("调用 unpark(t1) ，t1线程被唤醒------If the thread was blocked on park then it will unblock");
    }

    private static void testSync() {
        System.out.println(Thread.currentThread().getName() + "-------1");

        System.out.println(Thread.currentThread().getName() + "将调用park, 使线程阻塞  Disables the current thread for thread scheduling purposes unless the permit is available");
        //使当前线程阻塞
        LockSupport.park();

        System.out.println(Thread.currentThread().getName() + "-------2");
    }

    /*
        output:
        t1-------1
        t1将调用park, 使线程阻塞  Disables the current thread for thread scheduling purposes unless the permit is available
        main线程睡眠1s醒来------1
        t1-------2
        调用 unpark(t1) ，t1线程被唤醒------If the thread was blocked on park then it will unblock
     */
}
