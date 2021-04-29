package com.leh.juc.concurrentutils.exchanger;

import java.util.concurrent.Exchanger;

/**
 * @Description 必须
 * @Author lveh
 * @Date 2021/4/29 11:42
 * @Version 1.0
 * java.util.concurrent包中的Exchanger类可用于两个线程之间交换信息。
 * 可简单地将Exchanger对象理解为一个包含两个格子的容器，
 * 通过exchanger方法可以向两个格子中填充信息。当两个格子中的均被填充时，
 * 该对象会自动将两个格子的信息交换，然后返回给线程，从而实现两个线程的信息交换。
 **/
public class ExchangerDemo extends Thread {
    private Exchanger<String> exchanger;
    private String content;
    private String threadName;

    public ExchangerDemo(Exchanger<String> exchanger, String content,
                         String threadName) {
        this.exchanger = exchanger;
        this.content = content;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        try {
            System.out.println(threadName + ": " + exchanger.exchange(content));
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        ExchangerDemo demo1 = new ExchangerDemo(exchanger, "demo1",
                "thread-1");
        ExchangerDemo demo2 = new ExchangerDemo(exchanger, "demo2",
                "thread-2");

        /**
         * Exchanger类仅可用作两个线程的信息交换，当超过两个线程调用同一个exchanger对象时，得到的结果是随机的，
         * exchanger对象仅关心其包含的两个“格子”是否已被填充数据，当两个格子都填充数据完成时，
         * 该对象就认为线程之间已经配对成功，然后开始执行数据交换操作。
         */

        demo1.start();
        demo2.start();

        /**
         * 此程序创建了两个线程，线程在执行过程中，调用同一个exchanger对象的exchange方法，进行信息通信，
         * 当两个线程均已将信息放入到exchanger对象中时，exchanger对象会将两个线程放入的信息交换，
         * 然后返回，该程序的执行结果如下：
         * thread-1: demo2
         * thread-2: demo1
         **/

        ExchangerDemo demo3 = new ExchangerDemo(exchanger, "demo3", "thread-3");
        demo3.start();

        /**
         * 此时程序的运行结果是不确定的，多次运行可以发现，配对结果可能是demo1和demo3交换，
         * 也可能是demo2和demo3交换，而剩下的未得到配对的线程，则会被阻塞，永久等待，
         * 直到与之配对的线程到达位置，对于本程序，则只能强制将其停止。
         * thread-1: demo3
         * thread-3: demo1
         */
    }

}

