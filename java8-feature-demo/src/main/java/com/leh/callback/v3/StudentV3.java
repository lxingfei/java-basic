package com.leh.callback.v3;

/**
 * @Description
 * 小明这边现在已经不需要心算，也不需要使用计算器了，
 * 因此只需要有一个方法可以向小红寻求帮助就行了，代码如下：
 * @Author lveh
 * @Date 2021/3/4 10:47
 * @Version 1.0
 **/
public class StudentV3 {
    private String name = null;

    public StudentV3(String name)
    {
        // TODO Auto-generated constructor stub
        this.name = name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void callHelp (int a, int b)
    {
        new SuperCalculator().add(a, b, this);
    }

    public void fillBlank(int a, int b, int result)
    {
        System.out.println(name + "求助小红计算:" + a + " + " + b + " = " + result);
    }
}
