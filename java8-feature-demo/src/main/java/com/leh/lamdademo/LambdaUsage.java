package com.leh.lamdademo;

import com.leh.model.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

/**
 * lamda 的 使用
 */
public class LambdaUsage {

    //外部类的静态成员变量
    static int tmp2 = 2;
    //外部类的成员变量
    int tmp1 = 1;

    private static List<Apple> filter(List<Apple> source, Predicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple a : source) {
            if (predicate.test(a)) {
                result.add(a);
            }
        }
        return result;

    }

    private static List<Apple> filterByWeight(List<Apple> source, LongPredicate predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple a : source) {
            if (predicate.test(a.getWeight())) {
                result.add(a);
            }
        }
        return result;
    }

    private static List<Apple> filterByBiPredicate(List<Apple> source, BiPredicate<String, Long> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple a : source) {
            if (predicate.test(a.getColor(), a.getWeight())) {
                result.add(a);
            }
        }
        return result;
    }

    private static void simpleTestConsumer(List<Apple> source, Consumer<Apple> consumer) {
        for (Apple a : source) {
            consumer.accept(a);
        }
    }

    private static void simpleBiConsumer(String c, List<Apple> source, BiConsumer<Apple, String> consumer) {
        for (Apple a : source) {
            consumer.accept(a, c);
        }
    }

    private static String testFunction(Apple apple, Function<Apple, String> fun) {
        return fun.apply(apple);
    }

    private static Apple testBiFunction(String color, long weight, BiFunction<String, Long, Apple> fun) {
        return fun.apply(color, weight);
    }

    public static void main(String[] args) {

        Runnable rr1 = () -> System.out.println("Hello");

        Runnable rr2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello");
            }
        };

        process(rr1);
        process(rr2);
        process(() -> System.out.println("Hello"));


        List<Apple> list = Arrays.asList(new Apple("green", 120), new Apple("red", 150));

        List<Apple> greenList = filter(list, (apple) -> apple.getColor().equals("green"));
        System.out.println(greenList);


        List<Apple> result = filterByWeight(list, w -> w > 100);
        System.out.println(result);

        List<Apple> result2 = filterByBiPredicate(list, (s, w) -> s.equals("green") && w > 100);
        System.out.println(result2);

        System.out.println("================");
        simpleTestConsumer(list, a -> System.out.println(a));

        System.out.println("================");
        simpleBiConsumer("XXX", list, (a, s) -> System.out.println(s + a.getColor() + ":Weight=>" + a.getWeight()));

        System.out.println("================");
        String result3 = testFunction(new Apple("yellow", 100), (a) -> a.toString());
        System.out.println(result3);

        IntFunction<Double> f = i -> i * 100.0d;
        double result4 = f.apply(10);
        System.out.println("================");
        System.out.println(result4);

        System.out.println("================");
        Apple a = testBiFunction("Blue", 130, (s, w) -> new Apple(s, w));
        System.out.println(a);

        Supplier<String> s = String::new;   //method inference.
        System.out.println(s.get().getClass());

        System.out.println("================");

        Apple a2 = createApple(() -> new Apple("Green", 100));
        System.out.println(a2);


        int i = 0;

        Runnable r = new Runnable() {
            @Override
            public void run() {
                //i++;
                System.out.println(i);
            }
        };

        Runnable r2 = () -> System.out.println(i);


        BiFunction<String, Integer, Integer> stringIntegerIntegerBiFunction = Integer::parseInt;
        list.sort(Comparator.comparing(Apple::getWeight));

        Function<String, Integer> stringIntegerFunction = String::length;

//        BiConsumer<PrintStream, String> test = System.out::

        System.out.println();


        BiConsumer<Test, String> say = Test::say;

        Consumer<String> c = System.out::println;

    }

    public static void useTest(BiConsumer<Test, String> con, List<String> list) {

    }

    private static void process(Runnable r) {
        r.run();
    }

    private static Apple createApple(Supplier<Apple> supplier) {
        return supplier.get();
    }

    /*
           捕获（Capture）
           捕获的概念在于解决在λ表达式中我们可以使用哪些外部变量（即除了它自己的参数和内部定义的本地变量）的问题。
           在Java8以前，如果要在内部类访问外部对象的一个本地变量，那么这个变量必须声明为final才行。
           在Java8中，这种限制被去掉了，代之以一个新的概念，“effectively final”。
           它的意思是你可以声明为final，也可以不声明final但是按照final来用，也就是一次赋值永不改变。
           换句话说，保证它加上final前缀后不会出编译错误。
    */

    public void testCapture() {
        //没有声明为final，但是effectively final的本地变量
        int tmp3 = 3;
        //声明为final的本地变量
        final int tmp4 = 4;
        //普通本地变量
        int tmp5 = 5;
        Function<Integer, Integer> f1 = i -> i + tmp1;
        Function<Integer, Integer> f2 = i -> i + tmp2;
        Function<Integer, Integer> f3 = i -> i + tmp3;
        Function<Integer, Integer> f4 = i -> i + tmp4;
        /*Function<Integer, Integer> f5 = i -> {
            // 编译错！对tmp5赋值导致它不是effectively final的
            tmp5 += i;
            return tmp5;
        };*/
    }

    interface Test {
        void say(String s);
    }
}
