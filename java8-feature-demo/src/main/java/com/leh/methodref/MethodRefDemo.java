package com.leh.methodref;

/**
 * @Auther: leh
 * @Date: 2019/9/20 14:32
 * @Description: 函数式接口--方法引用
 */

interface DemoInterface1<P, R> {
    R convert(P p);//转换的方法
}

interface DemoInterface2<R> {
    R low();
}

interface DemoInterface3<P> {
    String conc(P param1, P param2);
}

class Person{
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

interface DemoInterface4<R> {

    R construct(String name, int age);

}

public class MethodRefDemo {

    public static void main(String[] args) {

        //引用静态方法  将String.valueOf(int i) 的引用 交给 DemoInterface1接口中的 convert方法
        DemoInterface1<Integer, String> interface1 = String :: valueOf;
        String ret = interface1.convert(2018);
        System.out.println(ret.replace("8", "9"));

        //引用某个对象普通方法  将String 的 toLowerCase()方法的引用 交给函数式接口DemoInterface2的 low()方法
        DemoInterface2<String> interface2 = "HHAA" :: toLowerCase;
        System.out.println(interface2.low());

        //引用特定类型的方法
        DemoInterface3<String> interface3 = String :: concat;
        System.out.println(interface3.conc("hello", " world"));

        //引用构造方法
        DemoInterface4<Person> interface4 = Person :: new;
        System.out.println(interface4.construct("leh", 17));

    }

    /*
        问题：每个操作都要定义一个函数式接口 ---》解决： 内建的函数式接口
     */
}
