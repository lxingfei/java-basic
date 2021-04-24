package com.leh.juc.future.completionservice;

import java.util.concurrent.*;

/**
 * @Description 自定义BlockingQueue实现
 * @Author lveh
 * @Date 2021/3/26 11:40
 * @Version 1.0
 * 思考可以借用哪种数据来实现下先进先出的功能，貌似队列可以实现下这个功能。
 * 采用队列来实现的功能。
 *
 * 这次比上个版本好多了，代码也简洁多了。
 * 不过按理说这种需求应该是大家经常遇到的，
 * 应该不需要自己来实现把，JAVA这么贴心的语言应该会有api可以直接拿来用
 **/
public class TaskAddBlockingQueueV3 extends AbstractTask{
    final static ExecutorService executor = new ThreadPoolExecutor(6, 6,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Future<String> mouZhuFlightPriceFuture = executor.submit(() -> getMouZhuFlightPrice());
        Future<String> mouXieFlightPriceFuture = executor.submit(() -> getMouXieFlightPrice());
        Future<String> mouTuanFlightPriceFuture = executor.submit(() -> getMouTuanFlightPrice());

        // 创建阻塞队列
        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(3);
        executor.execute(() -> run(mouZhuFlightPriceFuture, blockingQueue));
        executor.execute(() -> run(mouXieFlightPriceFuture, blockingQueue));
        executor.execute(() -> run(mouTuanFlightPriceFuture, blockingQueue));
        // 异步保存所有机票价格
        for (int i = 0; i < 3; i++) {
            String result = blockingQueue.take();
            System.out.println(result);
            saveDb(result);
        }
    }

    private static void run(Future<String> flightPriceFuture, BlockingQueue<String> blockingQueue) {
        try {
            blockingQueue.put(flightPriceFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
