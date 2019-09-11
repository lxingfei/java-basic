package com.leh.juc.lock.reentrantdemo.someway4lock;

/**
 * 自旋 + yield  --实现锁
 * @Auther: leh
 * @Date: 2019/9/11 16:33
 * @Description:
 * 要解决自旋锁的性能问题，必须让竞争锁失败的线程不空转
 * 而是在获取不到锁的时候将cpu给让出来。
 * yield方法就能让出cpu资源
 * 当线程竞争锁失败的时候，调用yield方法让出cpu
 * 缺点：yield 是由cpu调度的，下次调用哪个线程不确定
 * 自旋 + yield的方式并未完全解决问题
 * 当系统只有两个线程竞争时，yield是有效的。
 * 注意：yield方法只是当前让出cpu。有可能操作系统下次还是选择运行该线程。
 */
public class YieldSpinLock {

    volatile int status = 0;

    void lock() {
        while (!compareAndSet(0, 1)) {

            // 让出 cpu，线程
            yield();

        }
    }

    //自己实现
    private void yield() {

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
    }
}
