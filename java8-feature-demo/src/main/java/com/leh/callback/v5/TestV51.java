package com.leh.callback.v5;

/**
 * @Description
 * @Author lveh
 * @Date 2021/3/4 11:33
 * @Version 1.0
 * 我们再来看看运行后的结果，这里可以看出老师问过问题后就去忙自己的事情了，
 * 等学生回答完问题之后，就回调了自己的函数tellAnswer
 **/
public class TestV51 {
    public static void main(String[] args) {
        Student st = new Student();
        Teacher th = new Teacher(st);
        th.askQuestion("1+1=?");
    }

    /**
     * output:
     * Teacher ask a question: 1+1=?
     * Teacher: do someting else
     * Student receive the question: 1+1=?
     * Student: I am busy
     * your answer is: 2
     *
     */

}
