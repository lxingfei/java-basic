package com.leh.jvm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: leh
 * @Date: 2019/9/4 14:54
 * @Description:
 */
public class DeadLockDemo2 {

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> {
            while (true) {
                synchronized (lock1) {
                    System.out.println("thread1 获取锁 lock1");
                    synchronized (lock2) {
                        System.out.println("thread1 获取锁 lock2");
                    }
                }
            }
        });

        service.execute(() -> {
            while (true) {
                synchronized (lock2) {
                    System.out.println("thread2 获取锁 lock2");
                    synchronized (lock1) {
                        System.out.println("thread2 获取锁 lock1");
                    }
                }
            }
        });

        service.shutdown();

    }

}
