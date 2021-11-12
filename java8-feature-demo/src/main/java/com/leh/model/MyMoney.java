package com.leh.model;

import java.util.function.Function;

/**
 * @PackageName:com.leh.model
 * @ClassName:MyMoney
 * @Description 最常用的是Function接口，它能为我们省去定义一些不必要的函数接口，减少接口的数量
 * @Author lveh
 * @create: 2021-11-12 13:52
 * @Version 1.0
 **/
public class MyMoney {
    public int money = 0;

    public MyMoney(int money) {
        this.money = money;
    }

    public void printMoney(Function<Integer, String> moneyFormat){
        System.out.println("输入的money" + moneyFormat.apply(this.money));
    }

}
