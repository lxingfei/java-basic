package com.leh.callback.v1;

/**
 * @Description
 * JAVA回调机制(CallBack)详解 https://www.cnblogs.com/heshuchao/p/5376298.html
 * @Author lveh
 * @Date 2021/3/4 10:35
 * @Version 1.0
 **/
public class Test {
    public static void main(String[] args) {
        int a = 1;
        int b = 1;
        Student s = new Student("小明");
        s.fillBlank(a, b);
    }
}
