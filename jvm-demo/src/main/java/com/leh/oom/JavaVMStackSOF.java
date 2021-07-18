package com.leh.oom;

/** 虚拟机栈和本地方法栈溢出
 *  使用-Xss参数减少栈内存容量
 * @Description VM Args：-Xss128k
 * @Author lveh
 * @Date 2021/7/16 23:53
 * @Version 1.0
 **/
public class JavaVMStackSOF {
    private int stackLength = 1;
    public void stackLeak() {
        stackLength++;
        stackLeak();
    }
    public static void main(String[] args) throws Throwable {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }

    /**
     * stack length:1611
     * Exception in thread "main" java.lang.StackOverflowError
     * 	at com.leh.oom.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:13)
     * 	at com.leh.oom.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:13)
     * 	at com.leh.oom.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:13)
     * 	at com.leh.oom.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:13)
     */
}
