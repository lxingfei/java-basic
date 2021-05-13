package com.leh.streamdemo;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Author lveh
 * @Date 2021/5/8 13:18
 * @Version 1.0
 **/
public class StreamSplitCollection {
    public static void main(String[] args) {
        List list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 10, 20, 50, 12);

        //使用guava对list进行分割
        List<List<Integer>> partitions = Lists.partition(list, 100);
        partitions.stream().forEach(e -> System.out.println("guava split -- " + e.size()));
    }

}
