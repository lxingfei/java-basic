package com.leh.basic;

import java.text.*;

/**
 * @Auther: leh
 * @Date: 2019/9/16 15:14
 * @Description:
 *
 * 将 数字 1 == >>> 0001
 * 将字符串 0001 ====》 1
 */
public class DecimalFormatTest {

    public static void main(String[] args) throws ParseException {
        int num = 1;

        Format f1 = new DecimalFormat("000");
        System.out.println(f1.format(num));

        Format f2 = new DecimalFormat("00");
        System.out.println(f2.format(num));

        String str1 = "03";
        String str2 = "7";
        String str3 = "12";

        Format format = new DecimalFormat("00");
        System.out.println(format.parseObject(str1));
        System.out.println(format.parseObject(str2));
        System.out.println(format.parseObject(str3));
        Integer integer1 = Integer.valueOf(format.parseObject(str1).toString());
        Integer integer2 = Integer.valueOf(format.parseObject(str2).toString());;
        System.out.println(integer1+ integer2);

    }
}
