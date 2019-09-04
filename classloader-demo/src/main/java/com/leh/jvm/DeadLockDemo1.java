package com.leh.jvm;

/**
 * @Auther: leh
 * @Date: 2019/9/4 14:54
 * @Description:
 */
public class DeadLockDemo1 {

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();


    /**
     * 不一定会导致死锁，因为你没有做循环，只做一次操作，万一 thread1跑的快，瞬间就做完所有操作了，也不会阻塞thread2.
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

                synchronized (lock2) {
                    System.out.println("thread1 end");
                }

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

                synchronized (lock1) {
                    System.out.println("thread2 end");
                }
            }

        }, "thread2").start();

        System.out.println("------------the end");
    }


}
