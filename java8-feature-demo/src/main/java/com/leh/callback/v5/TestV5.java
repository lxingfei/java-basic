package com.leh.callback.v5;

/**
 * @Description
 * @Author lveh
 * @Date 2021/3/4 11:33
 * @Version 1.0
 **/
public class TestV5 {
    public static void main(String[] args) {
        Student st = new Student();
        Teacher th = new Teacher(st);
        th.askQuestion("1+1=?");
    }

    /**
     * output:
     * Teacher ask a question: 1+1=?
     * Student receive the question: 1+1=?
     * Student: I am busy
     * your answer is: 2
     * Teacher: do someting else
     * 从运行结果可以看出，这里的回调机制其实是同步的，
     * 也就是说老师问过问题之后，会阻塞在那里，等待学生回答问题，然后才会去做自己的事情。
     *
     * 但实际中，老师问完问题，并不会等待学生回答，而是继续做其他的事情，也就是异步回调。
     * 实现异步回调，我们只需要修改Teacher这个类就可以了，
     * 将调用学生回答问题的操作封装在一个线程中就可以了，也就是黄色部分。
     */

}
