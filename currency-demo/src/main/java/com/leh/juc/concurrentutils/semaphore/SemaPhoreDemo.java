package com.leh.juc.concurrentutils.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Auther: leh
 * @Date: 2019/9/18 17:57
 * @Description:
 * 应用场景：
 * 1）可以设置共享文件的最大客户端访问个数
 * 2）停车场最多允许停车数
 *
 * 信号量(Semaphore)，有时被称为信号灯，是在多线程环境下使用的一种设施,
 * 它负责协调各个线程, 以保证它们能够正确、合理的使用公共资源.
 *
 * 以一个停车场运作为例。为了简单起见，假设停车场只有三个车位，一开始三个车位都是空的。
 * 这时如果同时来了五辆车，看门人允许其中三辆不受阻碍的进入，然后放下车拦，剩下的车则必须在入口等待，
 * 此后来的车也都不得不在入口处等待。
 * 这时，有一辆车离开停车场，看门人得知后，打开车拦，放入一辆，
 * 如果又离开两辆，则又可以放入两辆，如此往复。
 *
 * 在这个停车场系统中，车位是公共资源，每辆车好比一个线程，看门人起的就是信号量的作用。
 */
public class SemaPhoreDemo {
    public static void main(String[] args) {
        // 线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        // 只能5个线程同时访问
        final Semaphore semp = new Semaphore(5);
        // 模拟20个客户端访问
        for (int index = 0; index < 20; index++) {
            final int num = index;
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    try {
                        // 获取许可
                        semp.acquire();
                        System.out.println("Accessing: " + num);
                        Thread.sleep((long) (Math.random() * 6000));
                        // 访问完后，释放
                        semp.release();
                        //availablePermits()指的是当前信号灯库中有多少个可以被使用
                        System.out.println("-----------------" + semp.availablePermits());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            exec.execute(run);
        }
        // 退出线程池
        exec.shutdown();
    }

    /**
        在java中，还可以设置该信号量是否采用公平模式，如果以公平方式执行，则线程将会按到达的顺序（FIFO）执行，
        如果是非公平，则可以后请求的有可能排在队列的头部。
        JDK中定义如下：
        Semaphore(int permits, boolean fair)
     　　   创建具有给定的许可数和给定的公平设置的Semaphore。
     */


}
