package com.leh.juc.lock.reentrantdemo.someway4lock;

/**
 * 假设 ReentrantLock 出现之前，如何自己实现一把锁
 *
 * @Auther: leh
 * @Date: 2019/9/11 11:48
 * @Description: 自旋方式 （等待）
 *
 * 缺点：
 * 耗费CPU资源
 * 没有竞争到锁的线程会一直占用CPU进行CAS操作
 * 当获得锁的线程需要花费 n 秒 处理业务逻辑
 * 那么另外没有获得锁的线程就会白白的浪费 n 秒 的 cpu
 *
 * 解决思路：
 * 让得不到锁的线程让出CPU
 *  yield + 自旋
 */
public class Spin4Lock {

    //标识 ---- 是否有线程上锁成功
    volatile int status = 0;

    /**
     * 分析：
     * 初始 status = 0;
     * t1 进入 testSync方法后，调用lock方法，通过 compareAndSet（ 0 , 1） 将 status 赋值 1，
     * status = 1;
     * compareAndSet 返回true，不走while循环，lock方法返回，实现给 lock对象 上锁 ，打印 t1 后睡眠
     *
      主线程继续向下执行，t2启动，进入 testSync() 后尝试给 lock对象 上锁， 当 t2 进行 compareAndSet（ 0 , 1）时
      status 已经变为 1，compareAndSet（ 0 , 1）返回false ，从而进入while死循环，会一直占用cpu （cpu可能会无限飙高）
      不断的循环，进行 CAS compareAndSet(0, 1) ，
      直到 t1 释放锁，将 status 改为 0
      status = 0;
      t2 compareAndSet 返回 true lock对象上锁成功，进而 打印 t2 -> 睡眠 5s -醒来
     */
    void lock() {
        while (!compareAndSet(0, 1)) {

        }
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
