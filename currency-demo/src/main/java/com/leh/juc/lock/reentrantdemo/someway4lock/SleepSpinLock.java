package com.leh.juc.lock.reentrantdemo.someway4lock;

/**
 * 自旋 + sleep  --实现锁
 * @Auther: leh
 * @Date: 2019/9/11 16:33
 * @Description:
 * 要解决自旋锁的性能问题，必须让竞争锁失败的线程不空转
 * 而是在获取不到锁的时候将cpu给让出来。
 *
 * 缺点：
 * sleep 睡眠时间不确定,易造成cpu资源浪费
 * 假设 t1 只用了 10s 执行完  t2却睡眠了 20s 就浪费了 10s
 */
public class SleepSpinLock {

    volatile int status = 0;

    void lock() {
        while (!compareAndSet(0, 1)) {

            // 让出 cpu，线程
            sleep(1000 * 10);

        }
    }

    private void sleep(int time) {
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
