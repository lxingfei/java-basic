package com.leh.classloader;

/**
 * @Auther: leh
 * @Date: 2019/9/10 11:05
 * @Description:静态变量和静态代码块的执行顺序
 *
 * 静态变量先于静态代码块执行，整个执行顺序是：
 * 父类静态变量初始化---》父类静态代码块-------》
 * 子类静态变量初始化------》子类静态语句块--------》
 * 父类变量初始化----------》父类代码块----------》父类构造函数 ------》
 * 子类变量初始化--------》子类语句块----------》子类构造函数
 *
 */
public class LoadOrderDemo {

    private static String name1 = null;


    static {
        name1 = "test name1";
        name2 = "test name2";
        name3 = "test name3";
    }

    private static String name2 = null;

    private static String name3;

    public static void main(String[] args) {
        System.out.println("name1>>>>>>>>>>>>>" + name1);
        System.out.println("name2>>>>>>>>>>>>>" + name2);
        System.out.println("name3>>>>>>>>>>>>>" + name3);

        /*
            output:
            name1>>>>>>>>>>>>>test name1
            name2>>>>>>>>>>>>>null
            name3>>>>>>>>>>>>>test name3

            ==>变量名首先被加载，而赋值的时候，无论是直接在变量上赋值还是在静态代码块中赋值，都是按照代码的顺序赋值的
         */
    }
}
