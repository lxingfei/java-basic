package com.leh.juc.future.fururetask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 *
 * @Description https://zhuanlan.zhihu.com/p/54459770
 * @Author lveh
 * @Date 2021/4/15 23:38
 * @Version 1.0
 * FutureTask是Future的具体实现。
 * FutureTask实现了RunnableFuture接口。
 * RunnableFuture接口又同时继承了Runnable 和 Future 接口。
 * 所以FutureTask既可以作为Runnable被线程执行，又可以作为Future得到Callable的返回值。
 **/
public class FutureTaskTest {

    /**
     *举个例子，假设我们要执行一个算法，算法需要两个输入 input1 和 input2,
     * 但是input1和input2需要经过一个非常耗时的运算才能得出。
     * 由于算法必须要两个输入都存在，才能给出输出，
     * 所以我们必须等待两个输入的产生。接下来就模仿一下这个过程
     **/
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long starttime = System.currentTimeMillis();
        //input1生成，需要耗费2秒
        FutureTask<Integer> inputOne = new FutureTask<>(new Callable() {
            @Override
            public Object call() throws Exception {
                Thread.sleep(2000);
                return 2;
            }
        });
        new Thread(inputOne).start();

        //input2生成，需要耗费3秒
        FutureTask<Integer> inputTwo = new FutureTask<>(new Callable() {
            @Override
            public Object call() throws Exception {
                Thread.sleep(3000);
                return 3;
            }
        });
        new Thread(inputTwo).start();

        Integer integer1 = inputOne.get();
        Integer integer2 = inputTwo.get();
        System.out.println(algorithm(integer1, integer2));
        long endtime = System.currentTimeMillis();
        System.out.println("用时：" + String.valueOf(endtime - starttime));

        /**
         * output:
         * 5
         * 用时：3004
         */
    }

    //这是我们要执行的算法
    public static int algorithm(int input, int input2) {
        return input + input2;
    }

}
