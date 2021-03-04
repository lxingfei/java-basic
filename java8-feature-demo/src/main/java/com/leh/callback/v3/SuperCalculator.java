package com.leh.callback.v3;

/**
 * @Description
 * JAVA回调机制(CallBack)详解 https://www.cnblogs.com/heshuchao/p/5376298.html
 * @Author lveh
 * @Date 2021/3/4 10:46
 * @Version 1.0
 **/
public class SuperCalculator {
    public void add(int a, int b, StudentV3  xiaoming)
    {
        int result = a + b;
        xiaoming.fillBlank(a, b, result);
    }
}
