package com.leh.oom;

/** 创建线程导致内存溢出异常
 * @Description * VM Args：-Xss2M
 * @Author lveh
 * @Date 2021/7/17 0:09
 * @Version 1.0
 **/
public class JavaVMStackOOM {
    private void dontStop() {
        while (true) {
        }
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
