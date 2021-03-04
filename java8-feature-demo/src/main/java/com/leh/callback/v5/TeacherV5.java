package com.leh.callback.v5;

import com.leh.callback.v4.CalcExecutor;

/**
 * @Description
 * @Author lveh
 * @Date 2021/3/4 11:36
 * @Version 1.0
 **/
public class TeacherV5 implements Callback {
    private Student student;

    public TeacherV5(Student student){
        this.student = student;
    }

    public void askQuestion(final String question){
        System.out.println("Teacher ask a question: "+ question);
        new Thread(new Runnable(){

            @Override
            public void run() {
                // TODO Auto-generated method stub
                //这里需要注意必须声明是Teacher.this
                student.resolveQuestion(TeacherV5.this,question);

            }

        }).start();
        System.out.println("Teacher: do someting else");
    }

    @Override
    public void tellAnswer(int answer){
        System.out.println("your answer is: " + answer);
    }
}
