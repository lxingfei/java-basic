package com.leh.juc.waitnotify;

/**
 * @Auther: leh
 * @Date: 2019/9/18 10:27
 * @Description:
 * wait 等待并释放锁
 */
public class WaitAndNotifyDemo {

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        new Thread(() -> {
            System.out.println("111111111111");
            synchronized (lock) {
                System.out.println("222222222222");
                try {
                    lock.wait();
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

            /*
                调用wait() 和 notify()方法必须在同步块中执行，意味着需要先给对象上锁
                若不加同步块 会报错：
                Exception in thread "t2" java.lang.IllegalMonitorStateException
             */
            synchronized (lock){
                System.out.println("555555555555");
                lock.notify();
            }

        }, "t2").start();
    }

    /*
        output:
        111111111111
        222222222222
        444444444444
        555555555555
        333333333333
     */
}
