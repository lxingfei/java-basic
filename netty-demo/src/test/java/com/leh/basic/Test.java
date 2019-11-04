package com.leh.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: leh
 * @Date: 2019/10/28 14:04
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(10 | 10);
        System.out.println(1 << 4);//16

        // Arrays.asList 返回的list不能进行更改删除操作 返回的其实是Arrays的一个内部类 不是java.util.ArrayList
        List<String> strings = Arrays.asList("1", "2", "3");
        //Exception in thread "main" java.lang.UnsupportedOperationException
        //strings.remove(1);
        List<String> names=new ArrayList<String>();
        names.add("1aaa");
        names.add("2bbb");
        names.add("3ccc");
        System.out.println(String.join(",", names));

    }

}
