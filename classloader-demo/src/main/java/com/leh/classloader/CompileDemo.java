package com.leh.classloader;

/**
 * @Auther: leh
 * @Date: 2019/9/3 16:59
 * @Description: 一个程序是怎么运行的？
 *
 *
 * dos窗口 cmd
 * javac CompileDemo.java --> 编译成 CompileDemo.class --> 虚拟机真正运行的class文件
 * javap -c CompileDemo.class --> 对代码进行反汇编 将代码转换成一行一行的指令集  --> 搜索jvm指令集
 * 输出到txt文件
 * javap -c CompileDemo.class > ComplileDemo.txt
 */
public class CompileDemo {

    public int math() {
        int m = 1;
        int n = 2;
        int k = (m + n) * 10;

        return k;

    }


    public static void main(String[] args) {
        CompileDemo demo = new CompileDemo();

        demo.math();
    }
}
