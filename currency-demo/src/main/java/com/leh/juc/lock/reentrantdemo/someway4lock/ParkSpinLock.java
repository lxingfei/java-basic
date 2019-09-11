package com.leh.juc.lock.reentrantdemo.someway4lock;

import java.util.Queue;


/**
 * 伪代码
 * 自旋 + park  --实现锁
 *
 * @Auther: leh
 * @Date: 2019/9/11 16:33
 * @Description: 要解决自旋锁的性能问题，必须让竞争锁失败的线程不空转
 * 而是在获取不到锁的时候将cpu给让出来。
 * <p>
 * 缺点：
 */
public class ParkSpinLock {

    volatile int status = 0;

    //等待队列
    Queue parkQueue;

    void lock() {
        while (!compareAndSet(0, 1)) {

            park();

        }

    }

    private void park() {
        //将当前线程加入到等待队列
        parkQueue.add(Thread.currentThread());
        //当前线程释放cpu
        releaseCpu();
    }

    private void releaseCpu() {
        //LockSupport.park();
    }

    private boolean compareAndSet(int expect, int newValue) {
        //cas 操作 修改status 成功则返回true
        if (status == expect) {
            status = newValue;
            return true;
        }
        return false;
    }

    void unlock() {
        status = 0;
        lockNotify();
    }

    private void lockNotify() {
        //得到要唤醒的线程的等待队列的头部线程
        Thread head = (Thread) parkQueue.poll();

        //唤醒等待线程
        unpark(head);

    }

    private void unpark(Thread t){

    }
}
