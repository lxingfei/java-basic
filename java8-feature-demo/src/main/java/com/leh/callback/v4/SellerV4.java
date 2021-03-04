package com.leh.callback.v4;

/**
 * @Description
 * @Author lveh
 * @Date 2021/3/4 11:20
 * @Version 1.0
 **/
public class SellerV4 {
    private String name = null;

    public SellerV4(String name)
    {
        // TODO Auto-generated constructor stub
        this.name = name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public class SellerWork implements CalcExecutor
    {

        @Override
        public void fillBlank(int a, int b, int result)
        {
            // TODO Auto-generated method stub
            System.out.println(name + "求助小红算账:" + a + " + " + b + " = " + result + "元");
        }

    }

    public void callHelp (int a, int b)

    {
        new SuperCalculatorV4().add(a, b, new SellerWork());
    }
}
