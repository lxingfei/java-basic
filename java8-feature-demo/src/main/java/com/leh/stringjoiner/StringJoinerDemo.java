package com.leh.stringjoiner;

import javafx.beans.binding.When;

import java.util.StringJoiner;
import java.util.stream.IntStream;

/**
 * @Description 为什么会新增StringJoiner这样一个string辅助类？
 * @Author lveh
 * @Date 2021/3/31 12:43
 * @Version 1.0
 * 原理：基于StringBuilder实现，add时就把prefix
 * 和分隔符给加上了，suffix永远都不加，
 * 直到toString和length调用时才加入计算。
 * 这样带来的merge操作实现的极大便利性！！！！
 **/
public class StringJoinerDemo {
    public static void main(String[] args) {
        testDelimiter1();
        testDelimiter2();
    }

    // 原有的stringbuilder太死板，不支持分割，如果想让最终的字符串以逗号隔开，需要这样写
    private static void testDelimiter1() {
        StringBuilder builder = new StringBuilder();
        IntStream.range(0, 10).forEach(i -> {
            builder.append(i + "");
            if(i < 9){
                builder.append(",");
            }
        });
        System.out.println(builder);

    }

    private static void testDelimiter2(){
        StringJoiner joiner = new StringJoiner(",");
        IntStream.range(0, 10).forEach(i -> joiner.add(i + ""));
        System.out.println(joiner);
    }


}
