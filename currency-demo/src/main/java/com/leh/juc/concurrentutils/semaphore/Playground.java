package com.leh.juc.concurrentutils.semaphore;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: leh
 * @Date: 2019/9/19 14:49
 * @Description:Semaphore经常用于限制获取某种资源的线程数量
 * emaphore是计数信号量。Semaphore管理一系列许可证。每个acquire方法阻塞，直到有一个许可证可以获得然后拿走一个许可证；
 * 每个release方法增加一个许可证，这可能会释放一个阻塞的acquire方法。
 * 然而，其实并没有实际的许可证这个对象，Semaphore只是维持了一个可获得许可证的数量。
 *
 * 比如说操场上有5个跑道，一个跑道一次只能有一个学生在上面跑步，一旦所有跑道在使用，
 * 那么后面的学生就需要等待，直到有一个学生不跑了
 *
 *
 */
public class Playground {
    private String[] tracks = {"跑道1", "跑道2", "跑道3", "跑道4", "跑道5"};//一共有5个跑道
    private volatile boolean[] used = new boolean[5];//标记跑道是否被占用

    private Semaphore semaphore = new Semaphore(5, true);

    public static void main(String[] args) {
        Executor executor = Executors.newCachedThreadPool();

        Playground playground = new Playground();
        Runnable runnable = () -> {
            try {
                String track = playground.getTrack();//获取跑道
                if (track != null) {
                    System.out.println("学生" + Thread.currentThread().getId() + "在" + track.toString() + "上跑步");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("学生" + Thread.currentThread().getId() + "释放" + track.toString());
                    playground.releaseTrack(track);//释放跑道
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        for (int i = 0; i < 100; i++) {
            executor.execute(runnable);
        }
    }

    //获取一个跑道
    public String getTrack() throws InterruptedException {
        semaphore.acquire(1);
        return getNextAvailableTrack();
    }

    //返回一个跑道
    public void releaseTrack(String track) {
        if (makeAsUnused(track)){
            semaphore.release(1);
        }
    }

    //遍历，找到一个没人用的跑道
    private String getNextAvailableTrack() {
        for (int i = 0; i < used.length; i++) {
            if (!used[i]) {
                used[i] = true;
                return tracks[i];
            }
        }
        return null;
    }

    //释放跑道，将使用标志设置为false
    private boolean makeAsUnused(String track) {
        for (int i = 0; i < used.length; i++) {
            if (tracks[i].equals(track)) {
                if (used[i]) {
                    used[i] = false;
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Semaphore是信号量，用于管理一组资源。
     * 其内部是基于AQS的共享模式，AQS的状态表示许可证的数量，在许可证数量不够时，线程将会被挂起；
     * 而一旦有一个线程释放一个资源，那么就有可能重新唤醒等待队列中的线程继续执行。
     */
}
