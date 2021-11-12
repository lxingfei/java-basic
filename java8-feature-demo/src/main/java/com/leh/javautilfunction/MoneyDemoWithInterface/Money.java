package com.leh.javautilfunction.MoneyDemoWithInterface;

import java.text.DecimalFormat;

/**
 * @PackageName:com.leh.javautilfunction.MoneyDemoWithInterface
 * @ClassName:MyMoney
 * @Description 在这个例子中不使用Function接口的话，则需要自行定义一个函数接口，并且不支持链式操作，如下示例：
 * @Author lveh
 * @create: 2021-11-12 14:05
 * @Version 1.0
 **/
public class Money {
    // 自定义一个函数接口
    @FunctionalInterface
    interface IMoneyFormat {
        String format(int i);
    }

    private int money;

    public Money(int money) {
        this.money = money;
    }

    public void printMoney(IMoneyFormat moneyFormat) {
        System.out.println("输入的money" + moneyFormat.format(this.money));
    }

    public static void main(String[] args) {
        Money me = new Money(999999999);
        IMoneyFormat moneyFormat = i -> new DecimalFormat("#, ###").format(i);
        me.printMoney(moneyFormat);
    }
}
