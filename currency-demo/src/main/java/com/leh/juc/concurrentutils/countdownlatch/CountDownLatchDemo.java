package com.leh.juc.concurrentutils.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @Auther: leh
 * @Date: 2019/9/19 15:45
 * @Description:J.U.C 之并发工具类：CountDownLatch
 * 开会案例:老板进入会议室等待 5 个人全部到达会议室才会开会。
 * 这里有两种线程：老板等待开会线程、员工到达会议室线程：
 */
public class CountDownLatchDemo {

    private static CountDownLatch countDownLatch = new CountDownLatch(5);

    static class BossThread extends Thread {
        @Override
        public void run() {
            System.out.println("Boss在会议室等待，总共有" + countDownLatch.getCount() + "个人开会...");
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("所有人都已经到齐了，开会吧...");
        }
    }


    static class EmpleoyeeThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "，到达会议室....");

            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //boss线程启动
        new BossThread().start();
        //睡眠1s 保证boss线程启动时没有人到达会议室
        Thread.sleep(1);
        for (int i = 0; i < countDownLatch.getCount(); i++) {
            //员工线程启动
            new EmpleoyeeThread().start();
        }
    }

    /*
        output:
        Boss在会议室等待，总共有5个人开会...
        Thread-1，到达会议室....
        Thread-2，到达会议室....
        Thread-3，到达会议室....
        Thread-4，到达会议室....
        Thread-5，到达会议室....
        所有人都已经到齐了，开会吧...

     */


}
