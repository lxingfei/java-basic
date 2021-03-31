package com.leh.juc.future;

/**
 * @Description
 * 缺点：顺序执行
 * 某猪这个网站耗时是拿到结果需要10s，其他的耗时都比它短，却等在后面
 * ==》 可以采用多线程来优化下 有结果的我们可以先处理的，不需要等到大家都返回了再来处理的
 * @Author lveh
 * @Date 2021/3/25 14:28
 * @Version 1.0
 **/
public class TaskNormalV1 extends AbstractTask{
    public static void main(String[] args) throws InterruptedException {
        String mouZhuFlightPrice = getMouZhuFlightPrice();
        String mouXieFlightPrice = getMouXieFlightPrice();
        String mouTuanFlightPrice = getMouTuanFlightPrice();
        saveDb(mouZhuFlightPrice);
        saveDb(mouXieFlightPrice);
        saveDb(mouTuanFlightPrice);
    }
}
