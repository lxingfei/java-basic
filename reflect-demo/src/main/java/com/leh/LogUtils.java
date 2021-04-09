package com.leh;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @Description  java 获取当前方法的被调用信息（被那个方法 那个类 那一行调用）
 * @Author lveh
 * @Date 2021/4/6 17:40
 * @Version 1.0
 **/
public class LogUtils {
    public static void v() {
        d();
    }

    public static void d() {
        i();
    }

    public static void i() {
        w();
    }

    public static void w() {
        e();
    }

    public static void e() {
        try {
            defaultTag();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private static String defaultTag() throws ClassNotFoundException, NoSuchMethodException {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement e : stackTrace) {
            System.out.println(e.getClassName() + "\t"
                    + e.getMethodName() + "\t" + e.getLineNumber());
        }
        StackTraceElement log = stackTrace[1];
        String tag = null;
        for (int i = 1; i < stackTrace.length; i++) {
            StackTraceElement e = stackTrace[i];
            if (!e.getClassName().equals(log.getClassName())) {
                tag = e.getClassName() + "." + e.getMethodName();
                break;
            }
        }
        if (tag == null) {
            tag = log.getClassName() + "." + log.getMethodName();

        }
        System.out.println(tag);
        return tag;
    }

    public static void main(String[] args) {
        LogUtils.v();
    }
}
