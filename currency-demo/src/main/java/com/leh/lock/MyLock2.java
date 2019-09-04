package com.leh.lock;

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
 *
 */

public class MyLock2 {

    int i; //4byte  new 对象的时候在内存至少应分配 4 byte  加上sync锁的时候可能将 i 设置成 1  从而实现 对 对象 加锁
    
}
