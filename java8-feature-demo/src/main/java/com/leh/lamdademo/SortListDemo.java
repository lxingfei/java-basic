package com.leh.lamdademo;

import com.leh.model.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Auther: leh
 * @Date: 2019/9/24 15:58
 * @Description:
 */
public class SortListDemo {
    public static void main(String[] args) {

        List<Apple> list = Arrays.asList(new Apple("abc", 123), new Apple("Green", 110), new Apple("red", 123));

        //排序1 匿名内部类
        list.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getColor().compareTo(o2.getColor());
            }
        });

        //排序2 lamda
        list.sort((a1, a2) -> a1.getColor().compareTo(a2.getColor()));

        //排序3
        List<Apple> list2 = Arrays.asList(new Apple("abc", 123), new Apple("Green", 110), new Apple("red", 123));

        list2.sort(Comparator.comparing(Apple::getColor));

        System.out.println(list2);
    }
}
