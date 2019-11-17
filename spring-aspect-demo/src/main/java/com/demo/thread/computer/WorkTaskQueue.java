package com.demo.thread.computer;

import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * created by zhanglong and since  2019/11/17  11:09 上午
 *
 * @description: 描述
 */
public final class WorkTaskQueue {

    private Queue<Runnable> workTaskQueue = new ConcurrentLinkedQueue<>();

    private AtomicInteger taskCount = new AtomicInteger(0);
//    private AtomicInteger workCount = new AtomicInteger(0);
    private static final String THREAD_GROUP_NAME = "default-sharding-group";
    private static final String THREAD_NAME = "default-sharding-thread";

    public int taskCount() {
        return taskCount.get();
    }

    void addTask( Runnable task ) {
        taskCount.incrementAndGet();
        workTaskQueue.offer(task);
    }


    void submit( ThreadPoolProperties threadPoolProperties, int executeThreadNum ) {
        CustomThreadFactory threadFactory = new CustomThreadFactory();
        threadFactory.bindingThreadName(new ThreadGroup(THREAD_GROUP_NAME), THREAD_NAME);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(

            threadPoolProperties.getCorePoolSize(),
            threadPoolProperties.getMaximumPoolSize(),
            threadPoolProperties.getKeepAliveTime(),
            TimeUnit.SECONDS, threadPoolProperties.getWorkQuezue(), threadFactory);
        if (taskCount.get() < executeThreadNum) {
            for (int i = 0; i < taskCount.get(); i++) {
                threadPoolExecutor.execute(threadFactory.newThread(workTaskQueue.poll()));
            }
        } else {
            AtomicInteger workingTask = new AtomicInteger(0);
            AtomicInteger workThreadNum = new AtomicInteger(0);
            while (!taskCount.compareAndSet(workingTask.get(), taskCount.get())) {
                if(executeThreadNum >= workThreadNum.get()) {
                    Runnable worker = workTaskQueue.poll();
                    if(Objects.nonNull(worker)) {
                        final FutureTask<Object> futureTask = new FutureTask<>(worker, null);
                        threadPoolExecutor.execute(threadFactory.newThread(futureTask));
                        workThreadNum.incrementAndGet();
                        workingTask.incrementAndGet();
                        if(futureTask.isDone()){
                            workThreadNum.decrementAndGet();
                            Sharding.releaseCpuSource(2000L);
                        }

                    }
                }else {
                    Sharding.releaseCpuSource(1000L);
                }
            }
        }
        threadPoolExecutor.shutdown();
    }

    public static void main( String[] args ) {
        AtomicInteger integer = new AtomicInteger(10);
        System.out.println(integer.compareAndSet(10, 0));
        do {
            System.out.println("111111111");
        }while (true);

    }
}
