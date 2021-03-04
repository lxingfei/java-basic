package com.leh.callback.v4;

/**
 * @Description
 * @Author lveh
 * @Date 2021/3/4 11:16
 * @Version 1.0
 * 只要实现了这个接口，就相当于按照统一的模式告诉小红得到结果之后的处理办法，
 * 按照之前说的使用内部类来做，代码如下：
 **/
public class StudentV4 {
    private String name = null;

    public StudentV4(String name)
    {
        // TODO Auto-generated constructor stub
        this.name = name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public class StudentHomeWork implements CalcExecutor
    {

        @Override
        public void fillBlank(int a, int b, int result)
        {
            // TODO Auto-generated method stub
            System.out.println(name + "求助小红计算:" + a + " + " + b + " = " + result);
        }

    }

    public void callHelp (int a, int b)
    {
        new SuperCalculatorV4().add(a, b, new StudentHomeWork());
    }
}
