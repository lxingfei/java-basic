package com.leh.juc.lock.syncdemo;

import java.util.concurrent.TimeUnit;

/**
 * @Description synchronized中实现线程等待和唤醒示例
 * @Author lveh
 * @Date 2021/4/29 13:37
 * @Version 1.0
 **/
public class WaitNotifyWtihSyncDemo {
    private static Object lock = new Object();

    public static class WaitDemo extends Thread {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + "," + this.getName() + "准备获取锁");
            synchronized (lock) {
                System.out.println(System.currentTimeMillis() + "," + this.getName() + "获取锁成功");
                try {
                    System.out.println(System.currentTimeMillis() + "," + this.getName() + "将进入等待状态并释放锁");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(System.currentTimeMillis() + "," + this.getName() + "被唤醒成功");
        }
    }

    public static class NotifyDemo extends Thread {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + "," + this.getName() + "准备获取锁");
            synchronized (lock) {
                System.out.println(System.currentTimeMillis() + "," + this.getName() + "获取锁成功");
                lock.notify();
                System.out.println(System.currentTimeMillis() + "," + this.getName() + ", notify!");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + "," + this.getName() + "准备释放锁");
            }
            System.out.println(System.currentTimeMillis() + "," + this.getName() + "释放锁成功");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WaitDemo waitThread = new WaitDemo();
        waitThread.setName("waitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(5);
        NotifyDemo notifyThread = new NotifyDemo();
        notifyThread.setName("notifyThread");
        notifyThread.start();
        /**
         *     output:
         *          1619681949535,waitThread准备获取锁
         *          1619681949535,waitThread获取锁成功
         *          1619681949535,waitThread将进入等待状态并释放锁
         *          1619681954536,notifyThread准备获取锁
         *          1619681954536,notifyThread获取锁成功
         *          1619681954536,notifyThread, notify!
         *          1619681959537,notifyThread准备释放锁
         *          1619681959537,notifyThread释放锁成功
         *          1619681959537,waitThread被唤醒成功
         *
         * 代码结合输出的结果我们分析一下：
         * 线程waitThread先获取锁，然后调用了wait()方法将线程置为等待状态，然后会释放lock的锁
         * 主线程等待5秒之后，启动线程notifyThread，notifyThread获取到了锁，结果中1、3行时间相差5秒左右
         * notifyThread调用lock.notify()方法，准备将等待在lock上的线程waitThread唤醒，notify()方法之后又休眠了5秒，
         * 看一下输出的5、8可知，notify()方法之后，waitThread并不能立即被唤醒，需要等到notifyThread将synchronized块执行完毕，
         * 释放锁之后，waitThread才被唤醒。
         * wait()方法和notify()方法必须放在同步块内调用（synchronized块内），否则会报错
         */
    }


}
