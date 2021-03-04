package com.leh.classloader;

/**
 * @Description TODO
 * @Author lveh
 * @Date 2020/3/27 19:34
 * @Version 1.0
 **/
public class ClassNotDef {
    static boolean isNeverAsync = System.getenv().get("asyncc_exclude_redundancy").equals("yes");
    static boolean isNeverAsync2;

    public static void main(String[] args) {
        System.out.println(isNeverAsync2);
        System.out.println(isNeverAsync);
    }

}
