package com.leh.callback.v4;

/**
 * @Description
 * @Author lveh
 * @Date 2021/3/4 11:15
 * @Version 1.0
 * 修改计算器，使其可以同时处理不同的实现了Executor接口的人
 **/
public class SuperCalculatorV4 {
    public void add(int a, int b, CalcExecutor customer) {
        int result = a + b;
        customer.fillBlank(a, b, result);
    }
}
