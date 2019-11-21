package com.demo.computer;

import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
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

    private Queue<Runnable> workTaskQueue =  new ConcurrentLinkedQueue<>();

    private AtomicInteger taskCount = new AtomicInteger(0);
    private static final String THREAD_GROUP_NAME = "default-sharding-group";
    private static final String THREAD_NAME = "default-sharding-thread";

    void addTask( Runnable task ) {
        taskCount.incrementAndGet();
        workTaskQueue.offer(task);
    }


    void submit( ThreadPoolProperties threadPoolProperties, int executeThreadNum ) {
        CustomThreadFactory threadFactory = new CustomThreadFactory();
        threadFactory.bindingThreadGroup(new ThreadGroup(THREAD_GROUP_NAME), THREAD_NAME);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            threadPoolProperties.getCorePoolSize(),
            threadPoolProperties.getMaximumPoolSize(),
            threadPoolProperties.getKeepAliveTime(),
            TimeUnit.SECONDS, threadPoolProperties.getWorkQuezue(), threadFactory);
        CountDownLatch countDownLatch = new CountDownLatch(taskCount.get());
        Queue<FutureTask<Object>> workingTaskQueue = new ArrayBlockingQueue<>(executeThreadNum);
        int awaitTaskNum = taskCount.get();
        int index = Math.min(awaitTaskNum, executeThreadNum);
        for (int i = 0; i < index; i++) {
            Runnable worker = workTaskQueue.poll();
            if (Objects.nonNull(worker)) {
                FutureTask<Object> futureTask = new FutureTask<>(worker, null);
                threadPoolExecutor.execute(threadFactory.newThread(futureTask));
                awaitTaskNum --;
                workingTaskQueue.offer(futureTask);
            }
        }
        //监控线程执行任务
        while (countDownLatch.getCount() != 0) {
            FutureTask<Object> worktask = workingTaskQueue.poll();
            if (worktask.isDone()) {
                countDownLatch.countDown();
                if (awaitTaskNum > 0) {
                    Runnable worker = workTaskQueue.poll();
                    if (Objects.nonNull(worker)) {
                        FutureTask<Object> futureTask = new FutureTask<>(worker, null);
                        threadPoolExecutor.execute(threadFactory.newThread(futureTask));
                        workingTaskQueue.offer(futureTask);
                    }
                }
            } else {
                workingTaskQueue.offer(worktask);
            }
        }
        threadPoolExecutor.shutdown();
    }

    public static void main( String[] args ) {
        AtomicInteger integer = new AtomicInteger(10);
        System.out.println(integer.compareAndSet(10, 0));
        do {
            System.out.println("111111111");
        } while (true);

    }
}
