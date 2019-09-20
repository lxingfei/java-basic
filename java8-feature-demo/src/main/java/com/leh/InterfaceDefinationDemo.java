package com.leh;

/**
 * @Auther: leh
 * @Date: 2019/9/20 10:38
 * @Description:java8接口定义增强
 */

interface SmsMsg {

    //java8 新增
    static void staticMethod() {
        System.out.println("这是接口中的静态方法");
    }

    //抽象方法
    void send(String phone, String msg);

    //java8 新增
    default void defaultMethod() {
        System.out.println("这是接口中的普通方法");
    }
}

class SmsMsgImpl implements SmsMsg {

    @Override
    public void send(String phone, String msg) {
        System.out.println("phone = [" + phone + "], msg = [" + msg + "]");
    }
}

public class InterfaceDefinationDemo {
    public static void main(String[] args) {

        SmsMsg smsMsg = new SmsMsgImpl();
        smsMsg.send("16777776666", "测试java8接口定义增强");
        smsMsg.defaultMethod();

        SmsMsg.staticMethod();
    }


}

