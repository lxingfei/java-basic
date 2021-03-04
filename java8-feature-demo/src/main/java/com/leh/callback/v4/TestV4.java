package com.leh.callback.v4;

/**
 * @Description
 * @Author lveh
 * @Date 2021/3/4 11:21
 * @Version 1.0
 **/
public class TestV4 {
    public static void main(String[] args)
    {
        int a = 56;
        int b = 31;
        int c = 26497;
        int d = 11256;
        StudentV4 s1 = new StudentV4("小明");
        SellerV4 s2 = new SellerV4("老婆婆");

        s1.callHelp(a, b);
        s2.callHelp(c, d);
    }

    /**
     * output:
     * 小明求助小红计算:56 + 31 = 87
     * 老婆婆求助小红算账:26497 + 11256 = 37753元
     */
}
