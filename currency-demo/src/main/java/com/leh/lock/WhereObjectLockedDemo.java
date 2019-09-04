package com.leh.lock;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Auther: leh
 * @Date: 2019/9/4 17:26
 * @Description:
 */
public class WhereObjectLockedDemo {

    static MyLock lock = new MyLock();

    public static void main(String[] args) {
        System.out.println("start");

        //1 什么样子的锁？

        /**
         * 列举java当中的锁
         */

        //打印对象的布局
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());

        synchronized (lock) {//sync 锁住的是对象，不是代码块
            System.out.println("lock ing");
        }

        System.out.println("end");
    }

    /*
        start
        com.leh.lock.MyLock object internals:
         OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
              0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
              4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
              8     4        (object header)                           43 c1 00 f8 (01000011 11000001 00000000 11111000) (-134168253)
             12     4        (loss due to the next object alignment)
        Instance size: 16 bytes
        Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

        lock ing
        end

       
     */


}
