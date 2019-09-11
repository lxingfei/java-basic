package com.leh.juc.lock.reentrantdemo;

/**
 * @Auther: leh
 * @Date: 2019/9/11 14:54
 * @Description:
 */
public class CurrencyNoLockDemo {

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
        当前线程》》》》t2

        当前线程》》》》t2》》》after 5s awake
        当前线程》》》》t1》》》after 5s awake

     */

    private static void testSync() {
        System.out.println("当前线程》》》》" + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("当前线程》》》》" + Thread.currentThread().getName() + "》》》after 5s awake");
    }

    /*
        分析：
              未加锁的情况下，  t1  t2 几乎同时打印
              main线程调用 main方法，t1启动，调用 testSync() 打印出t1 后进入睡眠
              但不影响主线程继续向下执行，t2启动，调用 testSync() 打印出t2 后进入睡眠

              5s后 t1 t2 又几乎同时醒来

              如何保证同步呢？ 一前一后执行
     */
}
