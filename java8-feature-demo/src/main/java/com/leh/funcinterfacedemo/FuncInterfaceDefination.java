package com.leh.funcinterfacedemo;

/**
 * @Auther: leh
 * @Date: 2019/9/20 11:22
 * @Description:函数式接口
    两种实现方式：
	2.1.1 在接口中定义唯一一个抽象方法
    2.1.2 接口上使用 @FunctionalInterface
 */
@FunctionalInterface
public interface FuncInterfaceDefination {

    String teach(String content);

    static void staticMethod() {
        System.out.println("这是接口中的静态方法");
    }

    default void defaultMethod() {
        System.out.println("这是接口中的普通方法");
    }


    /*
        允许定义Object类的 public方法  如 toString
     */
    @Override
    boolean equals(Object object);


    //String teach2(String content); 放开后 不满足@FunctionalInterface条件，编译不通过

}
