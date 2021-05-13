package com.leh.juc.lock.conditiondemo;

import com.leh.juc.lock.syncdemo.WaitNotifyWtihSyncDemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description 任何一个java对象都天然继承于Object类，
 * 在线程间实现通信的往往会应用到Object的几个方法，比如wait()、
 * wait(long timeout)、wait(long timeout, int nanos)与notify()、
 * notifyAll()几个方法实现等待/通知机制，同样的， 在java Lock体系下
 * 依然会有同样的方法实现等待/通知机制。
 * @Author lveh
 * @Date 2021/4/29 17:10
 * @Version 1.0
 * @see WaitNotifyWtihSyncDemo
 **/
public class ConditionDemo {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    /**
     * 从整体上来看Object的wait和notify/notify是与对象监视器配合完成线程间的等待/通知机制，
     * 而Condition与Lock配合完成等待通知机制，
     * 前者是java底层级别的，后者是语言级别的，具有更高的可控制性和扩展性。
     * 两者除了在使用方式上不同外，在功能特性上还是有很多的不同：
     * 1) Condition能够支持不响应中断，而通过使用Object方式不支持
     * 2) Condition能够支持多个等待队列（new 多个Condition对象），而Object方式只能支持一个
     * 3) Condition能够支持超时时间的设置，而Object不支持
     * <p>
     * Condition由ReentrantLock对象创建，并且可以同时创建多个，
     * Condition接口在使用前必须先调用ReentrantLock的lock()方法获得锁，
     * 之后调用Condition接口的await()将释放锁，并且在该Condition上等待，
     * 直到有其他线程调用Condition的signal()方法唤醒线程，使用方式和wait()、notify()类似。
     */
    public static class T1 extends Thread {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + "," + this.getName() + "准备获取锁");
            lock.lock();
            try {
                System.out.println(System.currentTimeMillis() + "," + this.getName() + "获取锁成功");
                System.out.println(System.currentTimeMillis() + "," + this.getName() + "将进入等待状态并释放锁");
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println(System.currentTimeMillis() + "," + this.getName() + "释放锁成功");
        }
    }

    public static class T2 extends Thread {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + "," + this.getName() + "准备获取锁!");
            lock.lock();
            try {
                System.out.println(System.currentTimeMillis() + "," + this.getName() + "获取锁成功!");
                condition.signal();
                System.out.println(System.currentTimeMillis() + "," + this.getName() + "signal!");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + "," + this.getName() + "准备释放锁");
            } finally {
                lock.unlock();
            }
            System.out.println(System.currentTimeMillis() + "," + this.getName() + "释放锁成功!");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1();
        t1.setName("t1");
        t1.start();
        TimeUnit.SECONDS.sleep(5);
        T2 t2 = new T2();
        t2.setName("t2");
        t2.start();
    }

    /**
     * output:
     * 1620907877287,t1准备获取锁
     * 1620907877288,t1获取锁成功
     * 1620907877288,t1将进入等待状态并释放锁
     * 1620907882288,t2准备获取锁!
     * 1620907882288,t2获取锁成功!
     * 1620907882289,t2signal!
     * 1620907887290,t2准备释放锁
     * 1620907887290,t1释放锁成功
     * 1620907887290,t2释放锁成功!
     */

    /**
     * 输出的结果和使用synchronized关键字的实例类似。
     *
     * Condition.await()方法和Object.wait()方法类似，当使用Condition.await()方法时，
     * 需要先获取Condition对象关联的ReentrantLock的锁，在Condition.await()方法被调用时，
     * 当前线程会释放这个锁，并且当前线程会进行等待（处于阻塞状态）。
     * 在signal()方法被调用后，系统会从Condition对象的等待队列中唤醒一个线程，
     * 一旦线程被唤醒，被唤醒的线程会尝试重新获取锁，一旦获取成功，就可以继续执行了。
     * 因此，在signal被调用后，一般需要释放相关的锁，让给其他被唤醒的线程，让他可以继续执行。
     */

}
