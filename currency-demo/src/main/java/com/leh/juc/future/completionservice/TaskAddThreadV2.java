package com.leh.juc.future.completionservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Description 轮循futureList获取结果
 * 采用future的方式来实现。使用一个List来保存每个任务返回的Future，
 * 然后去轮询这些Future，直到每个Future都已完成。
 * 由于需要先完成的任务需要先执行，且不希望出现因为排在前面的任务阻塞
 * 导致后面先完成的任务的结果没有及时获取的情况，
 * 所以在调用get方式时，需要将超时时间设置为 0
 *
 * @Author lveh
 * @Date 2021/3/25 14:30
 * @Version 1.0
 *
 * 细节：
 * 上述代码有两个小细节需要注意下：
 *
 * 如采用ArrayList的话futureList删除之后需要break进行下一次while循环，
 * 否则会产生我们意想不到的ConcurrentModificationException异常。
 * 具体原因可看下《ArrayList的删除姿势你都掌握了吗》这个文章，里面有详细的介绍。
 * 在捕获了InterruptedException和ExecutionException异常后记得 taskSize
 * --否则就会发生死循环。如果生产发生了死循环你懂的，cpu被你打满，程序假死等。你离被开除也不远了。
 * 上面轮询future列表非常的复杂，而且还有很多异常需要处理，还有很多细节需要考虑，
 * 还有被开除的风险。所以这种方案也被pass了。
 *
 * 思考：自定义BlockingQueue实现
 *
 **/
public class TaskAddThreadV2 extends AbstractTask{
    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10,
            60000, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));

    public static void main(String[] args) {
        int taskSize = 3;
        Future<String> mouZhuFlightPriceFuture = executor.submit(() -> getMouZhuFlightPrice());
        Future<String> mouXieFlightPriceFuture = executor.submit(() -> getMouXieFlightPrice());
        Future<String> mouTuanFlightPriceFuture = executor.submit(() -> getMouTuanFlightPrice());
        List<Future<String>> futureList = new ArrayList<>();
        futureList.add(mouZhuFlightPriceFuture);
        futureList.add(mouXieFlightPriceFuture);
        futureList.add(mouTuanFlightPriceFuture);
        // 轮询,获取完成任务的返回结果
        while (taskSize > 0) {
            for (Future<String> future : futureList) {
                String result = null;
                try {
                    result = future.get(0, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    taskSize--;
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    taskSize--;
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    // 超时异常需要忽略,因为我们设置了等待时间为0,只要任务没有完成,就会报该异常
                }
                // 任务已经完成
                if (result != null) {
                    System.out.println("result=" + result);
                    // 从future列表中删除已经完成的任务
                    futureList.remove(future);
                    taskSize--;
                    // 此处必须break，否则会抛出并发修改异常。（也可以通过将futureList声明为CopyOnWriteArrayList类型解决）
                    break; // 进行下一次while循环
                }
            }
        }
    }
}
