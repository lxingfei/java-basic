package com.leh;

import com.leh.beans.Cat;

/**
 * 反射的基本使用
 * Created by leh on 2019/9/1.
 * 一切皆对象，反射是对一个类的解剖
 * Class ： java中的一个类 --字节码类型 （定义的每一个 class也是一个对象， 它的类型即大写的 Class）
 * 类类型
 * ClassTest.java 编译成---> ClassTest.class--->执行
 */
public class ClassTest {

    public static void main(String[] args) {
        /**
         * 获取类类型的三种方式
         * 1. 类名.class
         * 2. 对象.getClass();
         * 3. Class.forName(类的全限定名)
         *
         * 3种方式哪一种比较好：
         * 类名.class 需要先导入包  耦合
         * 对象.getClass 对象都有了，再用反射没有必要
         * Class.forName 推荐使用
         */

        Class c1 = Cat.class;

        Cat cat = new Cat();

        Class c2 = cat.getClass();
        Class c3 = null;

        try {
            c3 = Class.forName("com.leh.beans.Cat");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(c1 == c2);//jvm 加载类类型只会加载一次，后面拿到的都是同一个
        System.out.println(c2 == c3);

        /**
         * output:
         公有的构造方法执行了.........
         true
         true
         */


    }
}
