package com.leh.juc.lock.conditiondemo;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 同一个锁支持创建多个Condition
 *  1. 使用condition的步骤：创建condition对象，获取锁，然后调用condition的方法
 *  2. 一个ReentrantLock支持床多个condition对象
 * @Description 使用两个Condition来实现一个阻塞队列的例子
 * @Author lveh
 * @Date 2021/5/13 20:28
 * @Version 1.0
 **/
public class BlockingQueueDemo<E> {
    // 阻塞队列最大容量
    int size;
    ReentrantLock lock = new ReentrantLock();
    // 队列底层实现
    LinkedList<E> list = new LinkedList<>();
    // 队列满时候的等待条件
    Condition fullCond = lock.newCondition();
    // 队列空时候的等待条件
    Condition emptyCond = lock.newCondition();

    public BlockingQueueDemo(int size) {
        this.size = size;
    }

    public void enqueue(E e) throws InterruptedException {
        lock.lock();
        try {
            while (list.size() == size) {
                // 队列已满，在fullCond条件上等待
                fullCond.await();
            }
            // 入队：加入链表末尾
            list.add(e);
            //通知在emptyCond条件上等待的线程
            emptyCond.signal();
        } finally {
            lock.unlock();
        }
    }

    public E dequeue() throws InterruptedException {
        E e;
        lock.lock();
        try{
            // 队列为空，在emptyCond条件上等待
            while(list.size() == 0){
                emptyCond.await();
            }
            // 出队:移除链表首元素
            e = list.removeFirst();
            //通知在fullCond条件上等待的线程
            fullCond.signal();
            return e;
        }finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {

        BlockingQueueDemo<Integer> queue = new BlockingQueueDemo<>(2);
        for (int i = 0; i <10 ; i++) {
            int data = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        queue.enqueue(data);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        for (int i = 0; i <10 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Integer data = queue.dequeue();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }

    /**
     * 代码非常容易理解，创建了一个阻塞队列，大小为3，队列满的时候，会被阻塞，等待其他线程去消费，
     * 队列中的元素被消费之后，会唤醒生产者，生产数据进入队列。
     * 上面代码将队列大小置为1，可以实现同步阻塞队列，生产1个元素之后，生产者会被阻塞，
     * 待消费者消费队列中的元素之后，生产者才能继续工作
     */

}
