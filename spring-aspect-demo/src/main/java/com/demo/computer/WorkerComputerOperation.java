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
public final class WorkerComputerOperation {

    /**
     * description 分片数量
     */
    private int shardingNum = Runtime.getRuntime().availableProcessors() * 5;
    /**
     * description 最小分片条件，超过之后才会归并
     */
    private Integer shardingDealMinCount = 1000;

    private WorkerComputerOperation() {
    }

    public static WorkerComputerOperation instance() {
        return new WorkerComputerOperation();
    }

    private WorkTaskQueue workTaskQueue;

    @SafeVarargs
    public final <H extends ComputerHandler> void run(
        Consumer<Map<Class<H>, Map<Integer, Object>>> consumer,
        WorkerContext<H>... workerContexts ) {
        Objects.requireNonNull(workerContexts, "workerContexts could not be null");
        workTaskQueue = new WorkTaskQueue();
        Map<Class<H>, Map<Integer, Object>> mergeResult = new ConcurrentHashMap<>();
        for (WorkerContext<H> shardingContext : workerContexts) {
            mergeResult.put(shardingContext.getHandlerClass(), new ConcurrentHashMap<>());
            dealWith(shardingContext, mergeResult);
        }
        workTaskQueue.submit(new ThreadPoolProperties(), shardingNum);
        if (Objects.nonNull(consumer)) {
            consumer.accept(mergeResult);
        }
    }

    private <H extends ComputerHandler> void dealWith( WorkerContext<H> workerContext,
        Map<Class<H>, Map<Integer, Object>> joinResult ) {
        int count = workerContext.getShardingHander().count(workerContext.getConditions());
        if (Objects.nonNull(shardingDealMinCount) && shardingDealMinCount >= count) {
            singleDealWith(workerContext, joinResult);
        } else {
            parallelDealWith(workerContext, joinResult, count);
        }
    }

    private <H extends ComputerHandler> void parallelDealWith(
        WorkerContext<H> workerContext, Map<Class<H>, Map<Integer, Object>> joinResult,
        int count ) {
        Map<Integer, List<Integer>> shardingDataMap = ResolutionUtils.sharding(shardingNum, count);

        shardingDataMap.forEach(( sharding, shardingData ) -> workTaskQueue.addTask(
            () -> shardingData.forEach(
                shardingNum -> workerContext.getShardingHander().execut(shardingNum, result -> {
                    if (joinResult.containsKey(workerContext.getHandlerClass())) {
                        joinResult.get(workerContext.getHandlerClass()).put(shardingNum, result);
                    }
                }, workerContext.getConditions()))));

    }

    private <H extends ComputerHandler> void singleDealWith( WorkerContext<H> shardingContext,
        Map<Class<H>, Map<Integer, Object>> joinResult ) {
        workTaskQueue.addTask(() -> {
            shardingContext.getShardingHander().execut(result -> {
                if (joinResult.containsKey(shardingContext.getHandlerClass())) {
                    joinResult.get(shardingContext.getHandlerClass()).put(shardingNum, result);
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
