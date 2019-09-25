package com.leh.streamdemo;

import com.leh.model.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @Auther: leh
 * @Date: 2019/9/25 14:14
 * @Description: 映射
 */
public class StreamMap {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 3, 6, 8, 7, 5, 4, 3, 7, 8, 9);
        // <R> Stream<R> map(Function<? super T, ? extends R> mapper)

        List<Integer> result = list.stream().map(i -> i * 5).collect(toList());

        System.out.println(result);

        listDish().stream().map(d ->d.getName()).forEach(System.out :: println);

        List<String> ret = listDish().stream().map(d -> d.getName()).collect(toList());

        System.out.println(ret);

        /*
            flatMap flat(扁平化)
         */

        String[] words = {"Hello", "World"};

        //{h,e,l,l,o},{W,o,r,l,d}
        Stream<String[]> stream = Arrays.stream(words).map(w -> w.split(""));//Stream<String[]>

        //H,e,l,l,o,W,o,r,l,d  将多个Stream合并成一个Stream
        Stream<String> stringStream = stream.flatMap(Arrays::stream);

        stringStream.distinct().forEach(System.out::println);



    }


    private static List<Dish> listDish() {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
        return menu;
    }
}
