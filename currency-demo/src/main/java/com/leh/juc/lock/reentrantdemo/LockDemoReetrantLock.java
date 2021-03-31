package com.leh.juc.lock.reentrantdemo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @Author lveh
 * @Date 2021/3/26 15:54
 * @Version 1.0
 **/
public class LockDemoReetrantLock {
    private volatile int i=0;
    private ReentrantLock reentrantLock=new ReentrantLock();
    public void inCreate(){
        // 断点
        reentrantLock.lock();
        try{
            i++;
        }finally {
            reentrantLock.unlock();//注意：一般的释放锁的操作都放到finally中，
            // 多线程可能会出错而停止运行，如果不释放锁其他线程都不会拿到该锁
        }
    }


    public static void main(String[] args) {
        LockDemoReetrantLock lockDemoReetrantLock = new LockDemoReetrantLock();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                lockDemoReetrantLock.inCreate();
            }).start();
        }
    }

}
