package com.leh.character;


import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import java.util.Arrays;

/**
 * @Description 以面向对象思想处理字符串:Joiner/Splitter/CharMatcher
 * @Author lveh
 * @Date 2021/3/31 13:29
 * @Version 1.0
 **/
public class SplitDemo {
    // 连接器
    private static final Joiner joiner = Joiner.on(",").skipNulls();
    // 分割器
    private static final Splitter splitter = Splitter.on(",").trimResults().omitEmptyStrings();

    public static void main(String[] args) {
        // 把集合、数组中的元素join在一起
        String join = joiner.join(Lists.newArrayList("a", null, "b"));
        System.out.println("join = " + join);

        for (String tmp : splitter.split(" a,  ,b,,")) {
            System.out.println("|" + tmp + "|");
        }
    }

}
