package com.leh.unsafe;

import java.util.Arrays;

/**
 * @Description 非线程安全的计数器
 * @Author lveh
 * @Date 2021/4/7 20:50
 * @Version 1.0
 **/
public class UnsafeCounter {
    private long count;

    private void counter() {
        long start = 0;
        while (start++ < 10000){
            count++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UnsafeCounter unsafeCounter = new UnsafeCounter();
        Thread t1 = new Thread(unsafeCounter::counter, "线程1");
        Thread t2 = new Thread(unsafeCounter::counter, "线程2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(unsafeCounter.getCount());
    }

    private long getCount(){
        return count;
    }
}
