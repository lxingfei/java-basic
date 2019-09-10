package com.leh;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 反射
 *
 * 通过反射生成的对象 和 直接new出来的对象有什么区别
 * Created by leh on 2019/9/1.
 */
public class ListTest {

    /*
        java 虚拟机 静态加载和动态加载
        Cat c = new Cat(); 编译时候，静态加载
        Class.forName("").newInstance(); 运行时动态加载
     */
    public static void main(String[] args) {

       /* if("cat".equals(args)){
            Cat c = new Cat(); //猫是存在的
        }else if ("dog".equals(args)){
            Dog c = new Dog();//狗不存在的
        }*/
        //因为狗不存在导致猫也不能执行 整个程序编译不通过

        try {
            if("cat".equals(args)){
                 Class.forName("com.leh.beans.Cat").newInstance();
            }else if ("dog".equals(args)){
                Class.forName("com.leh.beans.Dog").newInstance();//不仅编译通过,程序也可执行
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        /*
            反射可以跳过泛型
         */

        List list = new ArrayList();
        //明确list2中只能存放String类型
        List<String> list2 = new ArrayList<>();

        //list2.add(110);// 报错

        //反射是如何跳过泛型的

        Class c = list2.getClass();

        try {
            Method method = c.getDeclaredMethod("add", Object.class);

            method.invoke(list2, 10000);

            System.out.println(list2.size());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
