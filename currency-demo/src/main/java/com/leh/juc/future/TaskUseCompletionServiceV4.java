package com.leh.juc.future;

import java.util.concurrent.*;

/**
 * CompletionService实现
 * @Description 代码的简洁性
 * @Author lveh
 * @Date 2021/3/26 17:25
 * @Version 1.0
 * 《Java并发编程实战》一书6.3.5节CompletionService：Executor和BlockingQueue，有这样一段话：
 * 如果向Executor提交了一组计算任务，并且希望在计算完成后获得结果，
 * 那么可以保留与每个任务关联的Future，然后反复使用get方法，同时将参数timeout指定为0，
 * 从而通过轮询来判断任务是否完成。这种方法虽然可行，但却有些繁琐。
 * 幸运的是，还有一种更好的方法：完成服务CompletionService。
 *
 * CompletionService
 * 实现了生产者提交任务和消费者获取结果的解耦，生产者和消费者都不用关心任务的完成顺序，
 * 由CompletionService来保证，消费者一定是按照任务完成的先后顺序来获取执行结果
 **/
public class TaskUseCompletionServiceV4 extends AbstractTask{
    final static ExecutorService executor = new ThreadPoolExecutor(6, 6,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletionService completionService = new ExecutorCompletionService(executor);
        completionService.submit(() -> getMouZhuFlightPrice());
        completionService.submit(() -> getMouXieFlightPrice());
        completionService.submit(() -> getMouTuanFlightPrice());
        for (int i = 0; i < 3; i++) {
            String result = (String)completionService.take().get();
            System.out.println(result);
            saveDb(result);
        }
    }

    /**
     * 当我们使用了CompletionService不用遍历future列表，也不需要去自定义队列了，
     * 代码变得简洁了。
     **/

}
