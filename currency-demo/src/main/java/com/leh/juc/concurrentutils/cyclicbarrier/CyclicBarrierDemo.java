package com.leh.juc.concurrentutils.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * @Auther: leh
 * @Date: 2019/9/19 16:50
 * @Description:CyclicBarrier ，一个同步辅助类
 *  它允许一组线程互相等待，直到到达某个公共屏障点 (Common Barrier Point)。
 *  在涉及一组固定大小的线程的程序中，这些线程必须不时地互相等待，此时 CyclicBarrier 很有用。
 *  因为该 Barrier 在释放等待线程后可以重用，所以称它为循环( Cyclic ) 的 屏障( Barrier )
 *  通俗点讲就是：让一组线程到达一个屏障时被阻塞，直到最后一个线程到达屏障时，
 *  屏障才会开门，所有被屏障拦截的线程才会继续干活。
 *  CyclicBarrier 的内部是使用重入锁 ReentrantLock 和 Condition
 */
public class CyclicBarrierDemo {

    private static CyclicBarrier cyclicBarrier;

    static class CyclicBarrierThread extends Thread{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "到了");
            try {
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "开始干活");
        }
    }

    public static void main(String[] args) {
        cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " 人到齐了，开会吧....");
            }
        });

        for (int i = 0; i < 5; i++) {
            new CyclicBarrierThread().start();
        }
    }

    /*
        output:
        Thread-0到了
        Thread-3到了
        Thread-2到了
        Thread-1到了
        Thread-4到了
        Thread-0 人到齐了，开会吧....
        Thread-0开始干活
        Thread-1开始干活
        Thread-2开始干活
        Thread-3开始干活
        Thread-4开始干活
     */
}
