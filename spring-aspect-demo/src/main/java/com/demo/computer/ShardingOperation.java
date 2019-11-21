package com.demo.computer;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * created by zhanglong and since  2019/11/18  3:49 下午
 *
 * @description: 描述
 */
public final class ShardingOperation {

    /**
     * description 分片数量
     */
    private int shardingNum = Runtime.getRuntime().availableProcessors() * 5;
    /**
     * description 最小分片条件，超过之后才会归并
     */
    private Integer shardingDealMinCount = 1000;

    private ShardingOperation() {
    }

    public static ShardingOperation instance() {
        return new ShardingOperation();
    }

    private WorkTaskQueue workTaskQueue;

    public  <H extends ShardingHandler> void run(
        Consumer<Map<Class<H>, Map<Integer, Object>>> consumer,
        ShardingContext<H>... shardingContexts ) {
        Objects.requireNonNull(shardingContexts, "shardingContexts could not be null");
        int handlerNum = shardingContexts.length;
        workTaskQueue = new WorkTaskQueue();
        Map<Class<H>, Map<Integer, Object>> mergeResult = new ConcurrentHashMap<>();
        for (int i = 0; i < handlerNum; i++) {
            mergeResult.put(shardingContexts[i].getHandlerClass(), new ConcurrentHashMap<>());
            dealWith(shardingContexts[i], mergeResult);
        }
        if (Objects.nonNull(consumer)) {
            consumer.accept(mergeResult);
        }
    }

    private <H extends ShardingHandler> void dealWith( ShardingContext<H> shardingContext,
        Map<Class<H>, Map<Integer, Object>> mergeResult ) {
        int count = shardingContext.getShardingHander().count(shardingContext.getConditions());
        if (Objects.nonNull(shardingDealMinCount) && shardingDealMinCount >= count) {
            singleDealWith(shardingContext, mergeResult);
        } else {
            parallelDealWith(shardingContext, mergeResult, count);
        }
        workTaskQueue.submit(new ThreadPoolProperties(), shardingNum);
    }

    private <H extends ShardingHandler> void parallelDealWith(
        ShardingContext<H> shardingContext, Map<Class<H>, Map<Integer, Object>> mergeResult,
        int count ) {
        Map<Integer, List<Integer>> shardingDataMap = Sharding.sharding(shardingNum, count);

        shardingDataMap.forEach(( sharding, shardingData ) -> workTaskQueue.addTask(
            () -> shardingData.forEach(
                shardingNum -> shardingContext.getShardingHander().execut(shardingNum, result -> {
                    if (mergeResult.containsKey(shardingContext.getHandlerClass())) {
                        mergeResult.get(shardingContext.getHandlerClass()).put(shardingNum, result);
                    }
                }, shardingContext.getConditions()))));

    }

    private <H extends ShardingHandler> void singleDealWith( ShardingContext<H> shardingContext,
        Map<Class<H>, Map<Integer, Object>> mergeResult ) {
        workTaskQueue.addTask(() -> {
            shardingContext.getShardingHander().execut(result -> {
                if (mergeResult.containsKey(shardingContext.getHandlerClass())) {
                    mergeResult.get(shardingContext.getHandlerClass()).put(shardingNum, result);
                }
            }, shardingContext.getConditions());
        });
    }

    public void setShardingNum( int shardingNum ) {
        if(shardingNum >= 0) {
            this.shardingNum = shardingNum;
        }
    }

    public void setShardingDealMinCount( Integer shardingDealMinCount ) {
        if(Objects.nonNull(shardingDealMinCount) && shardingDealMinCount > 0) {
            this.shardingDealMinCount = shardingDealMinCount;
        }
    }
}
