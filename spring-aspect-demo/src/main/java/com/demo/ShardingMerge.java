package com.demo;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;

/**
 * created by zhanglong and since  2019/11/14 5:25 下午
 *
 * @description 描述
 */
@Slf4j
public class ShardingMerge {

    private static final String MERGE_COMPENSATE_GROUP_NAME = "MERGE-COMPENSATE-GROUP";
    private static final String MERGE_COMPENSATE_THREAD_NAME = "THREAD-SHARDING-MERGE-COMPENSATE";
    private static final String MERGE_THREAD_NAME_PREFIX = "THREAD-SHARDING-MERGE-";


    private ShardingMerge() {
    }

    /**
     * description 分片数量
     */
    private int shardingNum = Runtime.getRuntime().availableProcessors() * 5;

    private int shardingDealMinCount = 300;

    private ThreadPoolProperties threadPoolProperties;
    /**
     * description 分片处理类
     */
    private MergeHandler mergeHandler;

    private List<MergeHandler> mergeHandlers;

    private Map<Class<? extends MergeHandler>, List<?>> result = new ConcurrentHashMap<>();

    /**
     * create by ZhangLong on 2019/11/3 description 初始化
     */
    public static ShardingMerge instance(MergeHandler mergeHandler, MergeHandler... childMergeHandler) {
        ShardingMerge shardingMerge = new ShardingMerge();
        shardingMerge.mergeHandler = mergeHandler;
        if (Objects.nonNull(childMergeHandler)){
            shardingMerge.mergeHandlers = Arrays.asList(childMergeHandler);
        }
        return shardingMerge;
    }

    public static ShardingMerge instance(MergeHandler... mergeHandlers) {
        Objects.requireNonNull(mergeHandlers, "mergeHandler could not be null");
        return instance(null, mergeHandlers);
    }


    /**
     * create by ZhangLong on 2019/11/2 description 使用栅栏处理
     *
     * @param mergeHandler 处理器
     * @param count
     */
    private void merge(MergeHandler mergeHandler, int count) {
        if (Objects.isNull(threadPoolProperties)) {
            threadPoolProperties = new ThreadPoolProperties();
        }
        CustomThreadFactory threadFactory = new CustomThreadFactory();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                threadPoolProperties.getCorePoolSize(), threadPoolProperties.getMaximumPoolSize(),
                threadPoolProperties.getKeepAliveTime(),
                TimeUnit.SECONDS, threadPoolProperties.getWorkQuezue(), threadFactory);
        Map<Integer, List<Integer>> shardingDataMap = Sharding.sharding(shardingNum, count);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(shardingDataMap.size() + 1);
        shardingDataMap.forEach((sharding, shardingData) -> {
            Thread thread = threadFactory
                    .bindingThreadName(new ThreadGroup(threadPoolProperties.getThreadGroupName()),
                            MERGE_THREAD_NAME_PREFIX + sharding)
                    .newThread(() -> {
                        mergeHandler.execut(shardingData, result);
                        try {
                            cyclicBarrier.await();
                        } catch (InterruptedException | BrokenBarrierException e) {
                            log.error(e.getMessage());
                        }
                    });
            threadPoolExecutor.execute(thread);
        });
        Thread thread = threadFactory
                .bindingThreadName(new ThreadGroup(MERGE_COMPENSATE_GROUP_NAME), MERGE_COMPENSATE_THREAD_NAME)
                .newThread(() -> {
                    mergeHandler.compensate(result);
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        log.error(e.getMessage());
                    }
                });
        threadPoolExecutor.execute(thread);
        threadPoolExecutor.shutdown();
    }

    public void run(Consumer<Map<Class<? extends MergeHandler>, List<?>>> consumer) {
        Objects.requireNonNull(consumer, "consumer could not be null");
        if (Objects.nonNull(mergeHandler)) {
            dealWithMerge(mergeHandler);
        }
        if (CollectionUtils.isNotEmpty(mergeHandlers)) {
            mergeHandlers.forEach(this::dealWithMerge);
            consumer.accept(result);
        }
    }

    private void dealWithMerge(MergeHandler mergeHandler) {
        int count = mergeHandler.count();
        if (count > 0) {
            if (Objects.isNull(result.get(mergeHandler.getClass()))) {
                result.put(mergeHandler.getClass(), new ArrayList<>(count));
            }
            if (shardingDealMinCount >= count){
                mergeHandler.execut(null, result);
            }else {
                merge(mergeHandler, count);
            }
        }
    }

    public ShardingMerge threadPoolProperties(ThreadPoolProperties threadPoolProperties) {
        if (Objects.isNull(threadPoolProperties)) {
            this.threadPoolProperties = new ThreadPoolProperties();
        }
        return this;
    }

    public void shardingDealMinCount(int shardingDealMinCount){
        if (shardingDealMinCount <= 0){
            this.shardingDealMinCount = 300;
        }else {
            this.shardingDealMinCount = shardingDealMinCount;
        }
    }

    public ShardingMerge shardingNum(int shardingNum) {
        if (shardingNum < Runtime.getRuntime().availableProcessors() * 5) {
            shardingNum = Runtime.getRuntime().availableProcessors() * 5;
        }
        this.shardingNum = shardingNum;
        return this;
    }



}


