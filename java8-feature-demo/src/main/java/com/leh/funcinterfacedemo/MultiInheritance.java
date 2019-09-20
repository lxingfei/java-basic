package com.leh.funcinterfacedemo;

/**
 * @Auther: leh
 * @Date: 2019/9/20 12:08
 * @Description:函数式接口 多继承
 * MyInterface1 MyInterface2 都是函数式接口且方法签名相同
 */
@FunctionalInterface
public interface MultiInheritance extends MyInterface1, MyInterface2{


}
