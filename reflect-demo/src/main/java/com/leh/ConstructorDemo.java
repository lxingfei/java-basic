package com.leh;

import java.lang.reflect.Constructor;

/**
 * Created by leh on 2019/9/1.
 */
public class ConstructorDemo {


    public static void main(String[] args) {

        /**
         * 使用反射获取构造函数
         */

        try {
            //1 获取Class类型
            Class clazz = Class.forName("com.leh.beans.Cat");

            //2 获取构造函数
            //2.1 获取一堆
            //2.1.1 获取一堆公有的构造函数
            System.out.println("--------获取一堆公有权限的构造函数----------");
            Constructor[] cs = clazz.getConstructors();
            for (Constructor c : cs) {
                System.out.println(c);
            }

            //2.1.2
            System.out.println("--------获取一堆任意权限的构造函数----------");
            cs = clazz.getDeclaredConstructors();
            for (Constructor c : cs) {
                System.out.println(c);
            }

            //2.2 获取单个
            // 2.2.1 获取单个公有权限的构造函数
            System.out.println("--------获取单个公有权限的构造函数----------");
            Constructor constructor = clazz.getConstructor(null);

            System.out.println(constructor);

            // 2.2.2 获取单个任意权限的构造函数
            System.out.println("--------获取单个任意权限的构造函数----------");
            constructor = clazz.getDeclaredConstructor(int.class);

            System.out.println(constructor);

            //反射面前 就像剥光衣服

            //2.2.3 私有的构造方法是不能用来new对象的，如何调用私有的构造函数

            /*
                java.lang.IllegalAccessException: Class com.leh.ConstructorDemo can not access a member of class com.leh.beans.Cat with modifiers "private"
                将权限去掉（暴力访问）
             */
            constructor.setAccessible(true);

            //实例化
            constructor.newInstance(10);//相当于 new Cat(10);

            /*

            output:
            私有的构造方法执行了.........
             */




        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 应用场景
         * SSM 配置文件配置bean的全限定名 通过虚拟机应用反射动态去创建bean
         */
    }
}
