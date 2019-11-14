package com.demo;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;

/**
 * created by zhanglong and since  2019/11/14 5:25 下午
 *
 * @description 描述
 */
@Slf4j
public class ShardingMerge<R> {

    private static final String COMPENSATE_GROUP_NAME = "COMPENSATE-GROUP";
    private static final String COMPENSATE_THREAD_NAME = "THREAD-SHARDING-MERGE-COMPENSATE";
    private static final String MERGE_THREAD_NAME_PREFIX = "THREAD-SHARDING-MERGE-";


    private ShardingMerge() {
    }

    /**
     * description 分片数量
     */
    private int shardingNum = Runtime.getRuntime().availableProcessors() * 5;

    private ThreadPoolProperties threadPoolProperties;
    /**
     * description 分片处理类
     */
    private MergeHandler mergeHandler;

    private List<MergeHandler> mergeHandlers;

    private List<R> resultList;

    /**
     * create by ZhangLong on 2019/11/3 description 初始化
     */
    public static ShardingMerge instance( MergeHandler mergeHandler, MergeHandler ... childMergeHandler) {
        ShardingMerge shardingMerge = new ShardingMerge();
        shardingMerge.mergeHandler = mergeHandler;
        shardingMerge.mergeHandlers = Arrays.asList(childMergeHandler);
        return shardingMerge;
    }

    public static ShardingMerge instance(MergeHandler ... mergeHandlers) {
        Objects.requireNonNull(mergeHandlers, "mergeHandler could not be null");
        return instance(null, mergeHandlers);
    }


    /**
     * create by ZhangLong on 2019/11/2 description 使用栅栏处理
     */
    private void merge() {
        CustomThreadFactory threadFactory = new CustomThreadFactory();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            threadPoolProperties.getCorePoolSize(), threadPoolProperties.getMaximumPoolSize(),
            threadPoolProperties.getKeepAliveTime(),
            TimeUnit.SECONDS, threadPoolProperties.getWorkQuezue(), threadFactory);
        Map<Integer, List<Integer>> shardingDataMap = Sharding.sharding(shardingNum, count);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(shardingDataMap.size() + 1);
        shardingDataMap.forEach(( sharding, shardingData ) -> {
            Thread thread = threadFactory
                .bindingThreadName(new ThreadGroup(threadPoolProperties.getThreadGroupName()),
                    MERGE_THREAD_NAME_PREFIX + sharding)
                .newThread(() -> {
                    mergeHandler.execut(shardingData);
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        log.error(e.getMessage());
                    }
                });
            threadPoolExecutor.execute(thread);
        });
        Thread thread = threadFactory
            .bindingThreadName(new ThreadGroup(COMPENSATE_GROUP_NAME), COMPENSATE_THREAD_NAME)
            .newThread(() -> {
                mergeHandler.compensate();
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    log.error(e.getMessage());
                }
            });
        threadPoolExecutor.execute(thread);
        threadPoolExecutor.shutdown();
    }

    public void run( Consumer<List<R>> consumer ) {
        Objects.requireNonNull(consumer, "consumer could not be null");
        merge();
        consumer.accept(resultList);
    }

    public ShardingMerge threadPoolProperties( ThreadPoolProperties threadPoolProperties ) {
        if (Objects.isNull(threadPoolProperties)) {
            this.threadPoolProperties = new ThreadPoolProperties();
        }
        return this;
    }


    public ShardingMerge shardingNum( int shardingNum ) {
        if (shardingNum < Runtime.getRuntime().availableProcessors() * 5) {
            shardingNum = Runtime.getRuntime().availableProcessors() * 5;
        }
        this.shardingNum = shardingNum;
        return this;
    }


}
