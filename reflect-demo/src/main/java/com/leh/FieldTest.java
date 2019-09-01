package com.leh;

import java.lang.reflect.Field;

/**
 * Created by lverhui on 2019/9/1.
 */
public class FieldTest {

    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("com.leh.beans.Cat");

            System.out.println("-------------公有---------------------");
            Field[] fields = clazz.getFields();

            for (Field f : fields) {
                System.out.println(f);
            }


            System.out.println("-------------任意---------------------");
            fields = clazz.getDeclaredFields();

            for (Field f : fields) {
                System.out.println(f);
            }

            //指定私有的
            Field f = clazz.getDeclaredField("i");

            System.out.println(f);

            //为私有变量赋值 需要获取对象
            Object obj = clazz.newInstance(); // ==》 Cat c = new Cat()

            //暴力访问
            f.setAccessible(true);
            f.set(obj, 10000); // ==> c.i = 10000

            System.out.println(obj);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
