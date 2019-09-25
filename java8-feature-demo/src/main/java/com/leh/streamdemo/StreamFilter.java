package com.leh.streamdemo;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @Auther: leh
 * @Date: 2019/9/25 14:02
 * @Description: 过滤
 */
public class StreamFilter {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,3,6,8,7,5,4,3,7,8,9);

        // 将偶数过滤出来  Stream<T> filter(Predicate<? super T> predicate);
        List<Integer> result = list.stream().filter(i -> i % 2 == 0).collect(toList());
        System.out.println("过滤偶数:" + result);

        // 去重
        result = list.stream().distinct().collect(toList());
        System.out.println("去重:" + result);

        //跳过前面3个
        result = list.stream().skip(3).collect(toList());
        System.out.println("跳过前面3个:" + result);


        result = list.stream().limit(5).collect(toList());
        System.out.println("截取前面5个:" + result);

        list.forEach(System.out::println);

        list.forEach(i -> System.out.println(i));
        list.forEach((Integer i) -> System.out.println(i));
    }
}

