package com.demo.thread.computer;


import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;

/**
 * created by zhanglong and since  2019/11/14 5:25 下午
 *
 * @description 分为有结果集操作 {@link ComputerHandler} 数据写出操作 无结果集操作处理 {@link MergeHandler} 数据写入操作
 */
@Slf4j
public class ShardingWorker {

    private static final String COMPENSATE_THREAD_NAME = "THREAD-SHARDING-COMPENSATE";
    private static final String THREAD_NAME_PREFIX = "THREAD-SHARDING-";


    private ShardingWorker() {
    }

    /**
     * description 分片数量
     */
    private int shardingNum = Runtime.getRuntime().availableProcessors() * 5;
    /**
     * description 最小分片条件，超过之后才会归并
     */
    private Integer shardingDealMinCount = 1000;
    /**
     * description 线程池参数配置
     */
    private ThreadPoolProperties threadPoolProperties;
    /**
     * description 运行结果记录数
     */
    private CountDownLatch countDownLatch;
    /**
     * description 建议执行线程数
     */
    private int executeThreadNum = Runtime.getRuntime().availableProcessors() * 5;
    /**
     * description 自定义线程工厂用于描述线程组和线程
     */
    private CustomThreadFactory threadFactory;
    /**
     * created by zhanglong and since  2019/11/17 11:00 上午
     *
     * @description 工作任务
     */
    private WorkTaskQueue workTaskQueue;

    /**
     * created by zhanglong and since  2019/11/15 2:04 下午
     *
     * @description 进行自旋，直到结果集完全执行完成
     */
    private <H extends ShardingHandler, R> Map<Class<H>, Map<Integer, R>> confirmMergeResult(
        Map<Class<H>, Map<Integer, R>> mergeResult ) {
        do {
            Sharding.releaseCpuSource(500);
        } while (Objects.nonNull(countDownLatch) && countDownLatch.getCount() > 0);
        return mergeResult;
    }

    private boolean isCompensate;

    /**
     * create by ZhangLong on 2019/11/3 description 初始化
     */
    public static ShardingWorker instance() {
        return instance(false);
    }

    public static ShardingWorker instance( boolean isCompensate ) {
        ShardingWorker shardingOperation = new ShardingWorker();
        shardingOperation.isCompensate = isCompensate;
        if (Objects.isNull(shardingOperation.threadPoolProperties)) {
            shardingOperation.threadPoolProperties = new ThreadPoolProperties();
        }
        return shardingOperation;
    }

    public <H extends ShardingHandler, R, C> void run(
        Consumer<Map<Class<H>, Map<Integer, R>>> mergeResultConsumer,
        ShardingContext<H, R, C> ... shardingContexts ) {
        Objects
            .requireNonNull(shardingContexts, "mergeContext could be At least one initialization");
        int shardingHandlerNum = shardingContexts.length;
        Map<Class<H>, Map<Integer, R>> mergeResult = new ConcurrentHashMap<>(
            shardingHandlerNum);
        workTaskQueue = new WorkTaskQueue();
        for (int i = 0; i < shardingHandlerNum; i++) {
            if (ContextValidation.validate(shardingContexts[i])) {
                dealWith(shardingContexts[i], mergeResult);
            }
        }
        confirmMergeResult(mergeResult);
        mergeResultConsumer.accept(mergeResult);
    }

    private <H extends ShardingHandler, R, C> void dealWith(
        ShardingContext<H, R, C> shardingContext, Map<Class<H>, Map<Integer, R>> mergeResult ) {
        int count = shardingContext.getShardingHander().count(shardingContext.getConditions());
        countDownLatch = new CountDownLatch(workTaskQueue.taskCount());
        if (Objects.nonNull(shardingDealMinCount) && shardingDealMinCount >= count) {
            singleDealWith(shardingContext, mergeResult);
        } else {
            parallelDealWith(shardingContext, mergeResult, count);
        }
        workTaskQueue.submit(threadPoolProperties, executeThreadNum);
    }

    private <H extends ShardingHandler, R, C> void parallelDealWith(
        ShardingContext<H, R, C> shardingContext, Map<Class<H>, Map<Integer, R>> mergeResult,
        int count ) {
        Map<Integer, List<Integer>> shardingDataMap = Sharding.sharding(shardingNum, count);
        shardingDataMap.forEach(( sharding, shardingData ) -> {
            if (MergeHandler.class.isAssignableFrom(shardingContext.getHandlerClass())) {
                Map<Integer, R> handlerReusltMap = new ConcurrentHashMap<>(shardingDataMap.size());
                mergeResult.put(shardingContext.getHandlerClass(), handlerReusltMap);
                MergeHandler<R, C> mergeHandler = (MergeHandler<R, C>) shardingContext
                    .getShardingHander();
                workTaskQueue.addTask(() -> {
                    mergeHandler.banchExecute(shardingData, (shardingNum,result) -> {
                        if (Objects.nonNull(result)) {
                            handlerReusltMap.put(shardingNum, result);
                        }

                    }, shardingContext.getConditions());
                    countDownLatch.countDown();
                });
                if (isCompensate) {
                    workTaskQueue.addTask(() -> {
                        mergeHandler.compensate(result -> {
                            if (Objects.nonNull(result)) {
                                handlerReusltMap.put(-1, result);
                            }
                        }, shardingContext.getConditions());
                        countDownLatch.countDown();
                    });
                }
            }
            if (ComputerHandler.class.isAssignableFrom(shardingContext.getHandlerClass())) {
                ComputerHandler computerHandler = (ComputerHandler) shardingContext
                    .getShardingHander();
                workTaskQueue.addTask(() -> computerHandler
                    .banchExecut(shardingData, shardingContext.getConditions()));
            }
        });

//                Thread thread = threadFactory
//                    .bindingThreadName(new ThreadGroup(threadPoolProperties.getThreadGroupName()),
//                        THREAD_NAME_PREFIX + sharding)
//                    .newThread(
//                        () -> ((Consumer<List<Integer>>) shardingData -> {
//                            mergeHandler.banchExecute(shardingData, ( sharding, result ) -> {
//                                if (Objects.nonNull(result)) {
//                                    handlerReusltMap.put(sharding, result);
//                                }
//
//                            }, shardingContext.getConditions());
//                            countDownLatch.countDown();
//                        }).accept(shardingData));
//                threadPoolExecutor.execute(thread);
//            });

//            Thread thread = threadFactory
//                .bindingThreadName(new ThreadGroup(threadPoolProperties.getThreadGroupName()),
//                    COMPENSATE_THREAD_NAME)
//                .newThread(() -> {
//                    mergeHandler.compensate(result -> {
//                        if (Objects.nonNull(result)) {
//                            handlerReusltMap.put(-1, result);
//                        }
//                    }, shardingContext.getConditions());
//                    countDownLatch.countDown();
//                });
//            threadPoolExecutor.execute(thread);
//        }

//        if (ComputerHandler.class.isAssignableFrom(shardingContext.getHandlerClass())) {
//            ComputerHandler computerHandler = (ComputerHandler) shardingContext
//                .getShardingHander();
//            shardingDataMap.forEach(( sharding, shardingData ) -> {
//                Thread thread = threadFactory
//                    .bindingThreadName(
//                        new ThreadGroup(threadPoolProperties.getThreadGroupName()),
//                        THREAD_NAME_PREFIX + sharding)
//                    .newThread(() -> computerHandler
//                        .banchExecut(shardingData, shardingContext.getConditions()));
//                threadPoolExecutor.execute(thread);
//            });
//
//            Thread thread = threadFactory
//                .bindingThreadName(
//                    new ThreadGroup(threadPoolProperties.getThreadGroupName()),
//                    COMPENSATE_THREAD_NAME)
//                .newThread(
//                    () -> computerHandler.compensate(shardingContext.getConditions()));
//            threadPoolExecutor.execute(thread);
//        }
    }


    private <H extends ShardingHandler, R, C> void singleDealWith(
        ShardingContext<H, R, C> shardingContext, Map<Class<H>, Map<Integer, R
        >> mergeResult ) {
        workTaskQueue.addTask(() -> {
            if (MergeHandler.class.isAssignableFrom(shardingContext.getHandlerClass())) {
                MergeHandler<R, C> mergeHandler = (MergeHandler<R, C>) shardingContext
                    .getShardingHander();
                //多线程内处理
                mergeHandler.execute(result -> {
                    if (Objects.nonNull(result)) {
                        Map<Integer, R> handlerReusltMap = new ConcurrentHashMap<>();
                        mergeResult.put(shardingContext.getHandlerClass(), handlerReusltMap);
                        handlerReusltMap.put(-1, result);
                        countDownLatch.countDown();
                    }
                }, shardingContext.getConditions());
            }
            if (ComputerHandler.class.isAssignableFrom(shardingContext.getHandlerClass())) {
                ComputerHandler computerHandler = (ComputerHandler) shardingContext
                    .getShardingHander();
                computerHandler.execut(shardingContext.getConditions());
            }
        });
    }


    public ShardingWorker threadPoolProperties( ThreadPoolProperties threadPoolProperties ) {
        if (Objects.isNull(threadPoolProperties)) {
            this.threadPoolProperties = new ThreadPoolProperties();
        }
        return this;
    }

    public ShardingWorker shardingDealMinCount( int shardingDealMinCount ) {
        if (shardingDealMinCount <= 0) {
            this.shardingDealMinCount = 300;
        } else {
            this.shardingDealMinCount = shardingDealMinCount;
        }
        return this;
    }

    public ShardingWorker shardingNum( int shardingNum ) {
        if (shardingNum < Runtime.getRuntime().availableProcessors()) {
            shardingNum = Runtime.getRuntime().availableProcessors() * 5;
        }
        this.shardingNum = shardingNum;
        return this;
    }

}


