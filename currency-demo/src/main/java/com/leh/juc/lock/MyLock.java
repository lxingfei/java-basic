package com.leh.juc.lock;

/**
 * @Auther: leh
 * @Date: 2019/9/4 17:17
 * @Description: JVM中，java对象在内存中的布局分为3块区域：
 *  1.对象头
 *  2.实例数据
 *  3.对齐填充
 *
 *  肉眼看到的仅仅是对象的类的信息 还有一部分对象的信息是肉眼看不到的
 *
 *  1byte = 8bit
 *
 *  类信息里什么也没有，怎么照样也能加锁？ 锁的对象的哪里？
 *
 *  锁的是 对象头
 *
 */

public class MyLock {

}
