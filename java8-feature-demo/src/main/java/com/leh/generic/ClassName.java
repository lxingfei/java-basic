package com.leh.generic;

/**
 * @Auther: leh
 * @Date: 2019/9/24 15:02
 * @Description:在java中的泛型并不是代表着真正的新的类型，只是一个语法糖，便于类型检查和编译器优化的手段而已
 * 泛型种类：
 * Class 原始类型
 * ParameterizedType 参数化类型
 * GenericArrayType 数组类型
 * TypeVariable 类型变量
 * WildcardType 通配符类型
 *
 * extends与super

    <? extends T> : 是指上界通配符(Upper Bounds Wildcards)
    <? super T>:    是指下界通配符(Lower Bounds Wildcards)
    子类转换为父类(小转大)是隐式的，而父类转换成子类，需要显示手动强制转换。
    PECS(Producer Extends Consumer Super)原则

 *
 *
 * public <T> Test<String,T> setCacheObject(String key,T value){
        return null;
   }

    -- 前面的T的声明，跟类后面的 <T> 没有关系。
    -- 方法前面的<T>是给这个方法级别指定泛型。

 */

class Fruit {

    @Override
    public String toString() {
        return "Fruit";
    }
}

class Banana extends Fruit {
    @Override
    public String toString() {
        return "Banana";
    }
}

class Person {
    @Override
    public String toString() {
        return "Person";
    }
}

public class ClassName<T> {

    void show_1(T t) {
        System.out.println("show_1  " + t.toString());
    }

    <E> void show_2(E e) {
        System.out.println("show_2  " + e.toString());
    }

    <T> void show_3(T t) {
        System.out.println("show_3  " + t.toString());
    }

    public static void main(String[] args) {
        ClassName<Fruit> o = new ClassName<Fruit>();
        Fruit f = new Fruit();
        Banana a = new Banana();
        Person p = new Person();
        System.out.println("show_1 演示________________________");
        o.show_1(f);
        o.show_1(a);
        //o.show_1(p);  //是不能编译通过的。因为在 ClassName<Fruit>中已经限定了全局的T为Fruit，所以不能再加入Person;

        System.out.println("show_2 演示________________________");
        o.show_2(f);
        o.show_2(a);
        o.<Person>show_2(p);

        System.out.println("show_3 演示________________________");
        o.show_3(f);
        o.show_3(a);
        o.show_3(p);
    }


    /*
        output:
            show_1 演示________________________
            show_1  Fruit
            show_1  Banana

            show_2 演示________________________
            show_2  Fruit
            show_2  Banana
            show_2  Person

            show_3 演示________________________
            show_3  Fruit
            show_3  Banana
            show_3  Person


        show_2 和 show_3 方法其实是完完全全等效的。意思就是说ClassName中一旦T被指定为Fruit后，
        那么 show_1 没有前缀<T>的话，该方法中只能是show_1 (Fruit对象)。

        而你要是有前缀<T>或<E>的话，那么你就是告诉编译器对它说：这是我新指定的一个类型,
        跟ClassName<T>类对象中的T没有半毛钱的关系。也就是说这个show_3中的T和show_2中的E是一个效果，
        也就是你可以把show_3同等程度地理解为<E> void show_3(E e){~~~~~}。

     */
}
