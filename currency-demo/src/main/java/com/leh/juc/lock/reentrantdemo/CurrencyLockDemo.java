package com.leh.juc.lock.reentrantdemo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: leh
 * @Date: 2019/9/11 14:54
 * @Description:
 */
public class CurrencyLockDemo {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                testSync();
            }
        };

        t1.setName("t1");

        Thread t2 = new Thread() {
            @Override
            public void run() {
                testSync();
            }
        };

        t2.setName("t2");

        t1.start();
        t2.start();

        //System.out.println("当前线程》》》》" + Thread.currentThread().getName()); //main线程不好控制

    }

    /*
        output:
        当前线程》》》》t1

        当前线程》》》》t1》》》after 5s awake
        当前线程》》》》t2

        当前线程》》》》t2》》》after 5s awake
     */

    private static void testSync() {

        try {
            lock.lock();
            System.out.println("当前线程》》》》" + Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("当前线程》》》》" + Thread.currentThread().getName() + "》》》after 5s awake");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    /*
        分析：
              加锁的情况下，t1上锁 打印t1 - 睡眠5s - 醒来 --释放锁
                         t2 等待 ---------------------------- 上锁  打印t2 - 睡眠5s - 醒来 --释放锁

              t1 进入 testSync方法后，给 lock对象 上一把锁，打印出 t1 后进入睡眠
              主线程继续向下执行，t2启动，进入 testSync() 后尝试给 lock对象 上锁， 由于 lock对象 被线程 t1 持有
              t2 无法上锁，只能等待 t1睡醒 并 释放锁 unlock 以后，再去给 lock对象上锁，进而 打印 t2 -> 睡眠 5s -醒来
     */
}
