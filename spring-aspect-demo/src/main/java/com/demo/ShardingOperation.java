package com.demo;


import java.util.concurrent.atomic.AtomicBoolean;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;

/**
 * created by zhanglong and since  2019/11/14 5:25 下午
 *
 * @description 分为有结果集操作 {@link com.demo.ComputerHandler}
 *              无结果集操作处理 {@link com.demo.MergeHandler}
 */
@Slf4j
public class ShardingOperation {

    private static final String COMPENSATE_THREAD_NAME = "THREAD-SHARDING-COMPENSATE";
    private static final String THREAD_NAME_PREFIX = "THREAD-SHARDING-";


    private ShardingOperation() {
    }

    /**
     * description 分片数量
     */
    private int shardingNum = Runtime.getRuntime().availableProcessors() * 5;

    private int shardingDealMinCount = 300;

    private ThreadPoolProperties threadPoolProperties;
    /**
     * description 合并结束标示
     */
    private AtomicBoolean endOfRun = new AtomicBoolean(false);
    /**
     * description 运行结果记录数
     */
    private CountDownLatch countDownLatch;
    /**
     * description 结果集容器当
     */
    private Map<Class<? extends ShardingHandler>, List<?>> result = new ConcurrentHashMap<>();

    /**
     * created by zhanglong and since  2019/11/15 2:04 下午
     *
     * @description 进行自旋，直到结果集完全执行完成
     */
    public Map<Class<? extends ShardingHandler>, List<?>> result() {
        while (!endOfRun.compareAndSet(true, false)) {
            try {
                //释放50毫秒cpu资源
                TimeUnit.MILLISECONDS.sleep(50L);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
            endOfRun.set(Objects.isNull(countDownLatch) || countDownLatch.getCount() == 0);
        }
        return result;
    }

    public static void main( String[] args ) {
        ShardingOperation.instance().result();
    }

    /**
     * create by ZhangLong on 2019/11/3 description 初始化
     */
    public static ShardingOperation instance() {
        return new ShardingOperation();
    }


    public void run( Consumer<Map<Class<? extends ShardingHandler>, List<?>>> consumer,
        MergeHandler mergeHandler ) {
        Objects.requireNonNull(consumer, "consumer could not be null");
        if (Objects.nonNull(mergeHandler)) {
            dealWithMerge(mergeHandler, consumer);
            consumer.accept(result);
        }
    }

    public void run( Consumer<Map<Class<? extends ShardingHandler>, List<?>>> consumer,
        MergeHandler... mergeHandlers ) {
        Objects.requireNonNull(consumer, "consumer could not be null");
        Objects.requireNonNull(mergeHandlers, "mergeHandlers could not be null");
        for (MergeHandler mergeHandler : mergeHandlers) {
            dealWithMerge(mergeHandler, consumer);
        }
    }

    public void run(ComputerHandler computerHandler) {
        Objects.requireNonNull(computerHandler, "computerHandler could not be null");
        parallelDealWith(computerHandler);
    }

    private void dealWithMerge( MergeHandler mergeHandler,
        Consumer<Map<Class<? extends ShardingHandler>, List<?>>> consumer ) {
        if (parallelDealWith(mergeHandler)) {
            return;
        }
        Map<Class<? extends ShardingHandler>, List<?>> result = result();
        consumer.accept(result);
    }

    private boolean parallelDealWith( ShardingHandler shardingHandler ) {
        int count = shardingHandler.count();
        if (count <= 0) {
            log.error("handler num is Zero");
            return true;
        }

        Map<Integer, List<Integer>> shardingDataMap = Sharding.sharding(shardingNum, count);
        final boolean isMergeHandler = shardingHandler instanceof MergeHandler;
        if (isMergeHandler) {
            countDownLatch = new CountDownLatch(shardingDataMap.size() + 1);
            if (Objects.isNull(result.get(shardingHandler.getClass()))) {
                result.put(shardingHandler.getClass(), new ArrayList<>(count));
            }
        }
        if (shardingDealMinCount >= count) {
            shardingHandler.execut(null, isMergeHandler ? result : null);
        } else {
            if (Objects.isNull(threadPoolProperties)) {
                threadPoolProperties = new ThreadPoolProperties();
            }
            CustomThreadFactory threadFactory = new CustomThreadFactory();
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                threadPoolProperties.getCorePoolSize(), threadPoolProperties.getMaximumPoolSize(),
                threadPoolProperties.getKeepAliveTime(),
                TimeUnit.SECONDS, threadPoolProperties.getWorkQuezue(), threadFactory);

            computer(shardingDataMap, shardingHandler, threadFactory,
                threadPoolExecutor, isMergeHandler);
        }
        return false;
    }

    private void computer( Map<Integer, List<Integer>> shardingDataMap,
        ShardingHandler shardingHandler, CustomThreadFactory threadFactory,
        ThreadPoolExecutor threadPoolExecutor, boolean isMergeHandler ) {
        shardingDataMap.forEach(( sharding, shardingData ) -> {
            Thread thread = threadFactory
                .bindingThreadName(new ThreadGroup(threadPoolProperties.getThreadGroupName()),
                    THREAD_NAME_PREFIX + sharding)
                .newThread(() -> {
                    if(isMergeHandler){
                        shardingHandler.banchExecut(shardingData, result);
                        countDownLatch.countDown();
                    }else {
                        shardingHandler.banchExecut(shardingData, null);
                    }
                });
            threadPoolExecutor.execute(thread);
        });
        Thread thread = threadFactory
            .bindingThreadName(new ThreadGroup(threadPoolProperties.getThreadGroupName()),
                COMPENSATE_THREAD_NAME)
            .newThread(() -> {
                if(isMergeHandler){
                    shardingHandler.compensate(result);
                    countDownLatch.countDown();
                }else {
                    shardingHandler.compensate(null);
                }
            });
        threadPoolExecutor.execute(thread);
        threadPoolExecutor.shutdown();
    }

    public ShardingOperation threadPoolProperties( ThreadPoolProperties threadPoolProperties ) {
        if (Objects.isNull(threadPoolProperties)) {
            this.threadPoolProperties = new ThreadPoolProperties();
        }
        return this;
    }

    public void shardingDealMinCount( int shardingDealMinCount ) {
        if (shardingDealMinCount <= 0) {
            this.shardingDealMinCount = 300;
        } else {
            this.shardingDealMinCount = shardingDealMinCount;
        }
    }

    public ShardingOperation shardingNum( int shardingNum ) {
        if (shardingNum < Runtime.getRuntime().availableProcessors() * 5) {
            shardingNum = Runtime.getRuntime().availableProcessors() * 5;
        }
        this.shardingNum = shardingNum;
        return this;
    }


}


