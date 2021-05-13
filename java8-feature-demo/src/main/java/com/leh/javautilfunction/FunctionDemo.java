package com.leh.javautilfunction;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @Auther: leh
 * @Date: 2019/9/20 15:43
 * @Description:内建函数式接口 四个核心类
 */

class Demo {
    void print(String s) {
        System.out.println(s);
    }
}

public class FunctionDemo {

    public static void main(String[] args) {
        //完整功能型
        Function<String, Boolean> func1 = "hello xxx"::startsWith;
        System.out.println(func1.apply("h"));

        //消费型
        Consumer<String> func2 = new Demo() :: print;
        Consumer<String> func21 = System.out :: println;
        func2.accept("hello");
        func21.accept("hello");

        //供给型
        Supplier<String> func3 = "HAHA" :: toLowerCase;
        System.out.println(func3.get());

        //断言型
        Predicate<String> func4 = "abc" :: equals;
        System.out.println(func4.test("acb"));

    }

}
