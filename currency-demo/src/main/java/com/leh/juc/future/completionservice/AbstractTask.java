package com.leh.juc.future.completionservice;

/**
 * @Description
 * @Author lveh
 * @Date 2021/3/26 12:57
 * @Version 1.0
 **/
public class AbstractTask {
    /**
     * 模拟请求某猪网站 爬取机票信息
     *
     *
     * @return
     * @throws InterruptedException
     */
    public static String getMouZhuFlightPrice() throws InterruptedException {
        // 模拟请求某猪网站 爬取机票信息
        Thread.sleep(10000);
        return "获取到某猪网站的机票信息了";
    }

    /**
     * 模拟请求某携网站 爬取机票信息
     *
     * @return
     * @throws InterruptedException
     */
    public static String getMouXieFlightPrice() throws InterruptedException {
        // 模拟请求某携网站 爬取机票信息
        Thread.sleep(5000);
        return "获取到某携网站的机票信息了";
    }


    /**
     * 模拟请求团网站 爬取机票信息
     *
     * @return
     * @throws InterruptedException
     */
    public static String getMouTuanFlightPrice() throws InterruptedException {
        // 模拟请求某团网站 爬取机票信息
        Thread.sleep(3000);
        return "获取到某团网站的机票信息了";
    }

    /**
     * 保存DB
     *
     * @param flightPriceList
     */
    public static void saveDb(String flightPriceList) {
        // 解析字符串 进行异步入库
    }
}
