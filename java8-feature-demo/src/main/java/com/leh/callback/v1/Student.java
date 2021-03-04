package com.leh.callback.v1;

/**
 * @Description
 * @Author lveh
 * @Date 2021/3/4 10:33
 * @Version 1.0
 **/
public class Student
{
    private String name = null;

    public Student(String name)
    {
        this.name = name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    private int calcADD(int a, int b)
    {
        return a + b;
    }

    public void fillBlank(int a, int b)
    {
        int result = calcADD(a, b);
        System.out.println(name + "心算:" + a + " + " + b + " = " + result);
    }
}
