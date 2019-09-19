package com.leh.juc.waitnotify;

/**
 * @Auther: leh
 * @Date: 2019/9/18 11:03
 * @Description: sleep 使线程暂时停止执行但不释放锁
 * Causes the currently executing thread to sleep (temporarily cease
 * execution) for the specified number of milliseconds, subject to
 * the precision and accuracy of system timers and schedulers. The thread
 * does not lose ownership of any monitors.
 *
 *
 */
public class InterruptSleepDemo {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        Thread t1 = new Thread(() -> {
            System.out.println("111111111111");
            synchronized (lock) {
                System.out.println("222222222222");
                try {
                    Thread.sleep(5000);
                    System.out.println("333333333333");
                } catch (InterruptedException e) {
                    System.out.println("xxxxxxxxxxxx when interrupt t1");
                }

            }

        }, "t1");

        t1.start();

        // 主线程睡眠 10ms 模拟顺序执行
        Thread.sleep(10);


        new Thread(() ->{
            System.out.println("444444444444");

            synchronized (lock){

                System.out.println("555555555555");

            }

        }, "t2").start();

        /*
            t1被打断，释放锁，捕获InterruptedException异常，t2获得锁

         */
        t1.interrupt();


    }

    /*
        output：
        111111111111
        222222222222
        xxxxxxxxxxxx when interrupt t1
        444444444444
        555555555555

     */
}
