package com.leh.juc.lock.reentrantdemo.someway4lock;

/**
 * @Auther: leh
 * @Date: 2019/9/11 14:54
 * @Description:
 */
public class ParkSpinLockDemo {


    private static ParkSpinLock lock = new ParkSpinLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            testSync();
        }, "t1");

        Thread t2 = new Thread(() -> {
            testSync();
        }, "t2");

        t1.start();
        t2.start();

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
            Thread.sleep(20000);
            System.out.println("当前线程》》》》" + Thread.currentThread().getName() + "》》》after 20s awake");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    /*
        分析：
            t1上锁成功 打印 t1 -睡眠5s ------ 醒来 --释放锁
            t2等待 -----------------------------------上锁成功 打印 t2 - 睡眠5s - 醒来 --释放锁
     */
}
