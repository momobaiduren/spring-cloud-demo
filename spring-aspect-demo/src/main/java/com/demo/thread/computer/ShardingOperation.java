package com.demo.thread.computer;


import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;

/**
 * created by zhanglong and since  2019/11/14 5:25 下午
 *
 * @description 分为有结果集操作 {@link ComputerHandler} 数据写出操作 无结果集操作处理 {@link MergeHandler} 数据写入操作
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
    /**
     * description 最小分片条件，超过之后才会归并
     */
    private Integer shardingDealMinCount = 300;
    /**
     * description 线程池参数配置
     */
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
     * description 管理线程执行器
     */
    private ThreadPoolExecutor threadPoolExecutor;
    /**
     * description 自定义线程工厂用于描述线程组和线程
     */
    private CustomThreadFactory threadFactory;
    /**
     * description 条件
     */
    private Object conditions;

    /**
     * created by zhanglong and since  2019/11/15 2:04 下午
     *
     * @description 进行自旋，直到结果集完全执行完成
     */
    public <M extends MergeModel,R extends MergeResult<M>> R result( R result ) {
        while (!endOfRun.compareAndSet(true, false)) {
            releaseCpuSource();
            endOfRun.set(Objects.isNull(countDownLatch) || countDownLatch.getCount() == 0);
        }
        return result;
    }

    /**
     * create by ZhangLong on 2019/11/3 description 初始化
     */
    public static ShardingOperation instance() {
        final ShardingOperation shardingOperation = new ShardingOperation();
        shardingOperation.threadFactory = new CustomThreadFactory();
        if (Objects.isNull(shardingOperation.threadPoolProperties)) {
            shardingOperation.threadPoolProperties = new ThreadPoolProperties();
        }
        shardingOperation.threadPoolExecutor = new ThreadPoolExecutor(
            shardingOperation.threadPoolProperties.getCorePoolSize(),
            shardingOperation.threadPoolProperties.getMaximumPoolSize(),
            shardingOperation.threadPoolProperties.getKeepAliveTime(),
            TimeUnit.SECONDS, shardingOperation.threadPoolProperties.getWorkQuezue(),
            shardingOperation.threadFactory);
        return shardingOperation;
    }

    /**
     * created by zhanglong and since  2019/11/16 12:20 下午
     *
     * @param result 初始化结果集
     * @param consumer 结果集消费
     * @param conditions 条件输入
     * @param mergeHandler 执行器
     * @description 描述
     */
    public <M extends MergeModel,R extends MergeResult<M>>  void run(Class<M> eClass, R result, Consumer<MergeResult<M>> consumer,
        Object conditions, MergeHandler mergeHandler ) {
        Objects.requireNonNull(mergeHandler, "mergeHandler could not be null");
        Objects.requireNonNull(consumer, "consumer could not be null");
        this.conditions = conditions;
        dealWithMergeResult(eClass, result, consumer, mergeHandler);
        threadPoolExecutor.shutdown();
    }

    /**
     * created by zhanglong and since  2019/11/16 12:20 下午
     *
     * @param result 初始化结果集
     * @param consumer 结果集消费
     * @param conditions 条件输入
     * @param mergeHandlers 执行器
     * @description 描述
     */
    public <M extends MergeModel,R extends MergeResult<M>> void  run(Class<M> eClass,  R result, Consumer<R> consumer,
        Object conditions,
        MergeHandler... mergeHandlers ) {
        Objects.requireNonNull(result, "result could not be null");
        Objects.requireNonNull(consumer, "consumer could not be null");
        Objects.requireNonNull(mergeHandlers, "mergeHandlers could not be null");
        this.conditions = conditions;
        for (MergeHandler mergeHandler : mergeHandlers) {
            dealWithMergeResult(eClass, result, consumer, mergeHandler);
        }
        threadPoolExecutor.shutdown();
    }

    /**
     * created by zhanglong and since  2019/11/16 12:20 下午
     *
     * @param computerHandler 执行器
     * @param conditions 条件输入
     * @description 描述
     */
    public void run( ComputerHandler computerHandler, Object conditions ) {
        Objects.requireNonNull(computerHandler, "computerHandler could not be null");
        this.conditions = conditions;
        dealWith(computerHandler, null);
    }

    private <M extends MergeModel,R extends MergeResult<M>> void dealWithMergeResult(Class<M> eClass,  R result,
        Consumer<R> consumer, MergeHandler mergeHandler ) {
        dealWith(mergeHandler, result);
        result(result);
        consumer.accept(result);
    }

    private <M extends MergeModel,R extends MergeResult<M>> void dealWith(ShardingHandler shardingHandler, R result) {
        int count = shardingHandler.count(conditions);
        if (Objects.nonNull(shardingDealMinCount) && shardingDealMinCount >= count) {
            singleDealWith(shardingHandler);
            return;
        }
        parallelDealWith(shardingHandler, result, count);
    }

    private <M extends MergeModel,R extends MergeResult<M>> void parallelDealWith( ShardingHandler shardingHandler,
        R mergeResult, int count ) {
        Map<Integer, List<Integer>> shardingDataMap = Sharding.sharding(shardingNum, count);
        if (shardingHandler instanceof MergeHandler) {
            countDownLatch = new CountDownLatch(shardingDataMap.size() + 1);
            MergeHandler mergeHandler = (MergeHandler) shardingHandler;
            computer(shardingDataMap, shardingData -> {
                mergeHandler.banchExecute(shardingData, result ->{
                    //TODO
                }, conditions);
                countDownLatch.countDown();
            }, () -> {
                mergeHandler.compensate(result -> {
                    //TODO
                }, conditions);
                countDownLatch.countDown();
            });
        }

        if (shardingHandler instanceof ComputerHandler) {
            ComputerHandler computerHandler = (ComputerHandler) shardingHandler;
            computer(shardingDataMap,
                shardingData -> computerHandler.banchExecut(shardingData, conditions),
                () -> computerHandler.compensate(conditions));
        }
    }




    private <M extends MergeModel,R extends MergeResult<M>>  void singleDealWith( ShardingHandler shardingHandler) {
        countDownLatch = new CountDownLatch(1);
        threadPoolExecutor.execute(threadFactory.bindingThreadName(
            new ThreadGroup(threadPoolProperties.getThreadGroupName()), "defualt-thread")
            .newThread(() -> {
                if (shardingHandler instanceof MergeHandler) {
                    MergeHandler mergeHandler = (MergeHandler) shardingHandler;
                    //多线程内处理
                    mergeHandler.execute(result ->{
                        //TODO 直接返回结果集
                    }, conditions);
                    countDownLatch.countDown();
                }
                if (shardingHandler instanceof ComputerHandler) {
                    ComputerHandler computerHandler = (ComputerHandler) shardingHandler;
                    computerHandler.execut(conditions);
                }
            }));
    }

    private void releaseCpuSource() {
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }

    private void computer( Map<Integer, List<Integer>> shardingDataMap,
        Consumer<List<Integer>> executeConsumer, Runnable compensateRunable ) {
        shardingDataMap.forEach(( sharding, shardingData ) -> {
            Thread thread = threadFactory
                .bindingThreadName(new ThreadGroup(threadPoolProperties.getThreadGroupName()),
                    THREAD_NAME_PREFIX + sharding)
                .newThread(() -> executeConsumer.accept(shardingData));
            threadPoolExecutor.execute(thread);
        });

        Thread thread = threadFactory
            .bindingThreadName(new ThreadGroup(threadPoolProperties.getThreadGroupName()),
                COMPENSATE_THREAD_NAME)
            .newThread(compensateRunable);
        threadPoolExecutor.execute(thread);
    }

    public ShardingOperation threadPoolProperties( ThreadPoolProperties threadPoolProperties ) {
        if (Objects.isNull(threadPoolProperties)) {
            this.threadPoolProperties = new ThreadPoolProperties();
        }
        return this;
    }

    public ShardingOperation shardingDealMinCount( int shardingDealMinCount ) {
        if (shardingDealMinCount <= 0) {
            this.shardingDealMinCount = 300;
        } else {
            this.shardingDealMinCount = shardingDealMinCount;
        }
        return this;
    }

    public ShardingOperation shardingNum( int shardingNum ) {
        if (shardingNum < Runtime.getRuntime().availableProcessors() * 5) {
            shardingNum = Runtime.getRuntime().availableProcessors() * 5;
        }
        this.shardingNum = shardingNum;
        return this;
    }


}


