package com.leh;

import java.lang.reflect.Method;

/**
 * Created by lverhui on 2019/9/1.
 */
public class MainMethodTest {

    public static void main(String[] args) {

        //1 获取Class类型
        try {
            Class clazz = Class.forName("com.leh.beans.Cat");

            //2 获取main 方法

            Method method = clazz.getDeclaredMethod("main", String[].class);


            //3 调用 main 方法 --- invoke

            // main方法比较特殊 静态的不需要对象  必须传参数 String数组 必须Object类型

            method.invoke(null, (Object) new String[]{""});

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
