package com.leh.javautilfunction;

import com.leh.model.MyMoney;

import java.text.DecimalFormat;
import java.util.function.Function;

/**
 * @PackageName:com.leh.javautilfunction
 * @ClassName:FunctionMoneyDemo
 * @Description
 * @Author lveh
 * @create: 2021-11-12 13:57
 * @Version 1.0
 **/
public class FunctionMoneyDemo {
    public static void main(String[] args) {
        MyMoney me = new MyMoney(999999999);
        Function<Integer, String> moenyFormat = i -> new DecimalFormat("#,###").format(i);
        // 函数接口支持链式操组，如增加一个字符串
        me.printMoney(moenyFormat);
        me.printMoney(moenyFormat.andThen(s -> "人民币" + s));
    }
}
