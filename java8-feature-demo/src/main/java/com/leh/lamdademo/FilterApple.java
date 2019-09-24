package com.leh.lamdademo;

import com.leh.model.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * lamda的引入
 */
public class FilterApple {

    //绿色
    public static List<Apple> findGreenApple(List<Apple> apples) {

        List<Apple> list = new ArrayList<>();

        for (Apple apple : apples) {
            if ("green".equals(apple.getColor())) {
                list.add(apple);
            }
        }
        return list;
    }

    //红色
    public static List<Apple> findRedApple(List<Apple> apples) {

        List<Apple> list = new ArrayList<>();

        for (Apple apple : apples) {
            if ("red".equals(apple.getColor())) {
                list.add(apple);
            }
        }
        return list;
    }

    //改造 1
    public static List<Apple> findApple(List<Apple> apples, String color) {
        List<Apple> list = new ArrayList<>();

        for (Apple apple : apples) {
            if (color.equals(apple.getColor())) {
                list.add(apple);
            }
        }

        return list;
    }

    //改造 2
    public static List<Apple> findApple(List<Apple> apples, String color, long weight) {
        List<Apple> list = new ArrayList<>();

        for (Apple apple : apples) {
            if (color.equals(apple.getColor()) && weight <=  apple.getWeight()) {
                list.add(apple);
            }
        }

        return list;
    }


    /**
     * 定义函数式接口
     */
    @FunctionalInterface
    public interface AppleFilter {

        boolean filter(Apple apple);

    }

    /*
        策略一
     */
    public static class GreenAnd160WeightFilter implements AppleFilter {

        @Override
        public boolean filter(Apple apple) {
            return (apple.getColor().equals("green") && apple.getWeight() >= 160);
        }
    }

    /*
        策略二
     */
    public static class YellowLess150WeightFilter implements AppleFilter {

        @Override
        public boolean filter(Apple apple) {
            return (apple.getColor().equals("yellow") && apple.getWeight() < 150);
        }
    }

    /*
        改造3 策略模式：
        jdk8之前，我们写的程序都是命令式的，例如我们要实现加减乘除的运算操作，可以定义四个方法

        或者定义一个方法，然后传递接口（ 分别对应不同的实现），那就是所谓的策略模式

        缺点：这样做太复杂
     */

    public static List<Apple> findApple(List<Apple> apples, AppleFilter appleFilter) {
        List<Apple> list = new ArrayList<>();

        for (Apple apple : apples) {
            if (appleFilter.filter(apple)){
                list.add(apple);
            }
        }
        return list;
    }

    public static void main(String[] args) throws InterruptedException {
        List<Apple> list = Arrays.asList(new Apple("green", 150), new Apple("yellow", 120), new Apple("green", 170));

        //最初
        List<Apple> greenApples = findGreenApple(list);
        assert greenApples.size() == 2;

        //改造后
        List<Apple> greenApples2 = findApple(list, "green");
        System.out.println(greenApples2);

        List<Apple> redApples = findApple(list, "red");
        System.out.println(redApples);

        //策略模式实现 --> 应对不断变化的需求 -->不断的增加filter
        List<Apple> result = findApple(list, new GreenAnd160WeightFilter());
        System.out.println(result);

        //匿名内部类实现方式 --> 应对不断变化的需求 -->代码体积庞大 不够简洁 异常抛不出去
        List<Apple> yellowList = findApple(list, new AppleFilter() {
            @Override
            public boolean filter(Apple apple) {
                return "yellow".equals(apple.getColor());
            }
        });
        System.out.println(yellowList);

        /*
            对比java8的lamda表达式， lamda表达式占用更少的内存空间
            jps -l
            jstat -gcutil pid 1000 10
         */

        List<Apple> lambdaResult = findApple(list, apple -> apple.getColor().equals("green"));

        System.out.println("lambdaResult:" + lambdaResult);

        //创建线程
        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();


        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();


        Thread.currentThread().join();
    }





}
