package com.leh.callback.v2;

/**
 * @Description
 * JAVA回调机制(CallBack)详解 https://www.cnblogs.com/heshuchao/p/5376298.html
 * @Author lveh
 * @Date 2021/3/4 10:43
 * @Version 1.0
 **/
public class StudentV2 {
    private String name = null;

    public StudentV2(String name)
    {
        // TODO Auto-generated constructor stub
        this.name = name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @SuppressWarnings("unused")
    private int calcADD(int a, int b)
    {
        return a + b;
    }

    private int useCalculator(int a, int b)
    {
        return new Calculator().add(a, b);
    }

    public void fillBlank(int a, int b)
    {
        int result = useCalculator(a, b);
        System.out.println(name + "使用计算器:" + a + " + " + b + " = " + result);
    }
}
