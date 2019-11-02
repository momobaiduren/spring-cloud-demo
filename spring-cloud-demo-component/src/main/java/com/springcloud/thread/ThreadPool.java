package com.springcloud.thread;

import java.util.Objects;
import java.util.concurrent.*;

/**
 * @author ZhangLong on 2019/11/2  3:40 下午
 * @version V1.0
 */
public class ThreadPool {

    private int corePoolSize = Runtime.getRuntime().availableProcessors() * 10;

    private int maximumPoolSize = Runtime.getRuntime().availableProcessors() * 100;

    private long keepAliveTime = 60;

    private BlockingQueue<Runnable> workQuezue = new SynchronousQueue<>();

    private static final String THREAD_GROUP_NAME = "SYSTEM-THREAD-GROUP";

    private ThreadFactory threadFactory;

    private ThreadPoolExecutor threadPoolExecutor;

    private void init() {
        threadFactory = Executors.defaultThreadFactory();
        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQuezue);
    }

    public static ThreadPool instance(int corePoolSize, int maximumPoolSize,
                                      int keepAliveTime, BlockingQueue<Runnable> workQuezue) {
        ThreadPool threadPool = new ThreadPool();
        threadPool
                .corePoolSize(corePoolSize)
                .maximumPoolSize(maximumPoolSize)
                .keepAliveTime(keepAliveTime)
                .workQuezue(workQuezue)
                .init();
        return threadPool;
    }

    public void excute(Runnable runnable) {
        Thread thread = threadFactory.newThread(new Thread(new ThreadGroup(THREAD_GROUP_NAME), runnable));
        threadPoolExecutor.execute(thread);
    }

    public void excute(Runnable runnable, String threadName) {
        Thread thread = threadFactory.newThread(new Thread(new ThreadGroup(THREAD_GROUP_NAME), runnable, threadName));
        threadPoolExecutor.execute(thread);
    }

    public void excute(Thread thread) {
        thread = threadFactory.newThread(thread);
        threadPoolExecutor.execute(thread);
    }

    private ThreadPool maximumPoolSize(int maximumPoolSize) {
        if (maximumPoolSize >= 0) {
            if (maximumPoolSize >= corePoolSize) {
                this.maximumPoolSize = maximumPoolSize;
            }
        }
        return this;
    }

    private ThreadPool corePoolSize(int corePoolSize) {
        if (corePoolSize < 0) {
            corePoolSize = 0;
        }
        this.corePoolSize = corePoolSize;
        return this;
    }


    private ThreadPool keepAliveTime(int keepAliveTime) {
        if (keepAliveTime < 60) {
            keepAliveTime = 60;
        }
        this.keepAliveTime = keepAliveTime;
        return this;
    }

    private ThreadPool workQuezue(BlockingQueue<Runnable> workQuezue) {
        if (Objects.nonNull(workQuezue)) {
            this.workQuezue = workQuezue;
        }
        return this;
    }

}
