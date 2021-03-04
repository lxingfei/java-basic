package com.leh.callback.v5;

/**
 * @Description
 * @Author lveh
 * @Date 2021/3/4 11:30
 * @Version 1.0
 **/
public class Teacher implements Callback {
    private Student student;

    public Teacher(Student student){
        this.student = student;
    }

    public void askQuestion(final String question){
        System.out.println("Teacher ask a question: "+ question); //提出一个问题
        student.resolveQuestion(this,question);                      //询问学生
        System.out.println("Teacher: do someting else");  //忙自己的事
    }

    @Override
    //重写回调函数
    public void tellAnswer(int answer){
        System.out.println("your answer is: " + answer);
    }
}
