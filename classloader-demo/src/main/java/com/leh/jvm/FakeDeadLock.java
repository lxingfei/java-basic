package com.leh.jvm;

/**
 * @Auther: leh
 * @Date: 2019/9/4 14:54
 * @Description:
 */
public class FakeDeadLock {

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();


    /**
     *
     * 此处看似死锁
     * 实则 程序正常执行
     *
     * 死锁的前提是两个人吃饭，都需要需要刀和叉但又只有一套， 其中一个人拿了叉，另一个拿了刀，就出现互相等待的情况。
     * lock1 和 lock2就相当于刀叉，但是你并没有说拿了叉，还会继续去拿刀。
     */
    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (lock1) {
                try {
                    System.out.println("thread1 start");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            synchronized (lock2) {
                System.out.println("thread1 end");
            }

        }, "thread1").start();


        new Thread(() -> {
            synchronized (lock2) {
                try {
                    System.out.println("thread2 start");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            synchronized (lock1) {
                System.out.println("thread2 end");
            }

        }, "thread2").start();

        System.out.println("------------the end");
    }


}
