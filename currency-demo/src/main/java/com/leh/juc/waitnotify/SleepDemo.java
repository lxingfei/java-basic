package com.leh.juc.waitnotify;

/**
 * @Auther: leh
 * @Date: 2019/9/18 11:03
 * @Description: sleep 使线程暂时停止执行但不释放锁
 * Causes the currently executing thread to sleep (temporarily cease
 * execution) for the specified number of milliseconds, subject to
 * the precision and accuracy of system timers and schedulers. The thread
 * does not lose ownership of any monitors.
 */
public class SleepDemo {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        new Thread(() -> {
            System.out.println("111111111111");
            synchronized (lock) {
                System.out.println("222222222222");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("xxxxxxxxxxxx when interrupt");
                }
                System.out.println("333333333333");
            }

        }, "t1").start();

        // 主线程睡眠 10ms 保证 t1 和 t2 的先后顺序
        Thread.sleep(10);

        new Thread(() ->{
            System.out.println("444444444444");

            synchronized (lock){

                System.out.println("555555555555");

            }

        }, "t2").start();
    }

    /*
        output：
        111111111111
        222222222222
        444444444444

        ----2s后----
        333333333333
        555555555555
     */
}
