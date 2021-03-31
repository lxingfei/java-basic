package com.leh.character;

import com.google.common.base.CharMatcher;

import java.util.Arrays;

/**
 * @Description  CharMatcher，将字符的匹配和处理解耦，并提供丰富的方法供你使用
 * @Author lveh
 * @Date 2021/3/31 17:48
 * @Version 1.0
 **/
public class CharMatcherDemo {

    public static final CharMatcher charMatcherDigit = CharMatcher.DIGIT;
    public static final CharMatcher charMatcherAny = CharMatcher.ANY;

    public static void main(String[] args) {
        // 只保留匹配的字符 其他移除
        System.out.println(charMatcherDigit.retainFrom("123bkhljlj1213"));
        // 移除匹配的字符
        System.out.println(charMatcherDigit.removeFrom("123bkhljlj1213"));

        System.out.println(CharMatcher
                .inRange('a', 'f')
                .or(CharMatcher.is('n'))
                .replaceFrom("abdljljlbnkyunl", "*"));


    }


}
