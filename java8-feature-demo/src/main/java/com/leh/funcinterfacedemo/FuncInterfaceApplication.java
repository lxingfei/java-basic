package com.leh.funcinterfacedemo;

/**
 * @Auther: leh
 * @Date: 2019/9/20 11:22
 * @Description:函数式接口 使用
 */

public class FuncInterfaceApplication {

    public static void main(String[] args) {

        //JDK1.8之前的方式：使用匿名内部类
        FuncInterfaceDefination demo = new FuncInterfaceDefination() {
            @Override
            public String teach(String content) {
                return content;
            }
        };
        System.out.println("JDK1.8之前使用匿名内部类,输出:" + demo.teach("java8特性"));

        //JDK1.8之后 使用Lamda表达式
        FuncInterfaceDefination demo2 = (content) -> { // 多行
            return content;
        };
        System.out.println("JDK1.8之后使用lamda,输出:" + demo2.teach("java8特性"));


        FuncInterfaceDefination demo3 = (a) -> a;// 单行
        System.out.println("更优化的方式，JDK1.8之后使用lamda,输出:" + demo3.teach("java8特性"));


        FuncInterfaceDefination demo4 = String :: valueOf;
        System.out.println("更优化的方式,使用String的静态方法,JDK1.8之后使用lamda,输出:" + demo4.teach("java8特性"));


        FuncInterfaceDefination demo5 = (a) -> a + a;//a + a 表达式
        System.out.println("更优化的方式，JDK1.8之后使用lamda,输出:" + demo5.teach("java8特性"));

    }
}
