package com.springcloud.computer;


import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.*;

/**
 * @author zhanglong
 * @version V1.0
 * @date 2019-09-12 14:33
 */
@Slf4j
public class ShardingComputer {

    private static final String COMPENSATE_GROUP_NAME = "COMPENSATE-GROUP";
    private static final String COMPENSATE_THREAD_NAME = "THREAD-SHARDING-COMPUTER-COMPENSATE";
    private static final String COMPUTER_THREAD_NAME_PREFIX = "THREAD-SHARDING-COMPUTER-";


    private ShardingComputer() {
    }

    /**
     * description 核心线程数，太大会导致线程上下文切换的消耗
     */
    private int corePoolSize = 0;
    /**
     * description 最大线程数
     */
    private int maximumPoolSize = Runtime.getRuntime().availableProcessors() * 10;
    /**
     * description 空闲线程存活时间
     */
    private long keepAliveTime = 60;
    /**
     * description 线程队列类型 初始化构造不设置队列长度默认65535
     */
    private BlockingQueue<Runnable> workQuezue = new SynchronousQueue<>();
    /**
     * description 数据总量
     */
    private int count;
    /**
     * description 分片数量
     */
    private int shardingNum = Runtime.getRuntime().availableProcessors() * 5;
    /**
     * description 自定义线程分组名称
     */
    private String threadGroupName;
    /**
     * description 自定义线程工厂，用来描述线程相关信息
     */
    private ComputerThreadFactory threadFactory;
    /**
     * description 线程池执行器
     */
    private ThreadPoolExecutor threadPoolExecutor;
    /**
     * description 分片处理类
     */
    private ComputerHandler computerHandler;
    /**
     * description 用于线程内处理异常结果记录
     */
    private Map<Integer, List<Integer>> dealResult;

    /**
     * create by ZhangLong on 2019/11/3
     * description 初始化
     */
    public static ShardingComputer instance(ComputerHandler computerHandler) {
        Objects.requireNonNull(computerHandler, "computerHandler could not be null");
        ShardingComputer computor = new ShardingComputer();
        computor.computerHandler = computerHandler;
        computor.count = computerHandler.count();
        return computor;
    }

    /**
     * @desc 分片数从1开始执行，如果索引是0的要加1
     */
    private Map<Integer, List<Integer>> sharding() {
        Map<Integer, List<Integer>> shardingDataMap = new HashMap<>(shardingNum);
        dealResult = new ConcurrentHashMap<>(shardingNum);
        for (int i = 1; i <= count; i++) {
            if (shardingDataMap.containsKey(i % shardingNum)) {
                shardingDataMap.get(i % shardingNum).add(i);
            } else {
                List<Integer> shardingData = new ArrayList<>(count / shardingNum + 1);
                shardingData.add(i);
                shardingDataMap.put(i % shardingNum, shardingData);
            }
        }
        return shardingDataMap;
    }


    /**
     * create by ZhangLong on 2019/11/2
     * description 计算
     */
    public void computer() {
        if (count <= 0) {
            log.error("handler num is Zero");
            return;
        }
        init();
        Map<Integer, List<Integer>> shardingDataMap = sharding();
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(shardingDataMap.size(), ()->{
//            if (!dealResult.isEmpty()){
//                //TODO 处理完成之后阻塞，异常数据监控处理，处理完成之后
//            }
//        });
        shardingDataMap.forEach((sharding, shardingData) -> {
            Thread thread = threadFactory
                    .bindingThreadName(new ThreadGroup(threadGroupName), COMPUTER_THREAD_NAME_PREFIX + sharding)
                    .newThread(() -> {
                        computerHandler.execut(shardingData);
//                        try {
//                            cyclicBarrier.await();
//                        } catch (InterruptedException | BrokenBarrierException e) {
//                            log.error(e.getMessage());
//                        }
                    });
            threadPoolExecutor.execute(thread);
        });
        Thread thread = threadFactory
                .bindingThreadName(new ThreadGroup(COMPENSATE_GROUP_NAME), COMPENSATE_THREAD_NAME)
                .newThread(() -> computerHandler.compensate());
        threadPoolExecutor.execute(thread);
        threadPoolExecutor.shutdown();
    }

    private void init() {
        threadFactory = new ComputerThreadFactory();
        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQuezue);
    }

    public ShardingComputer corePoolSize(int corePoolSize) {
        if (corePoolSize < 0) {
            corePoolSize = 0;
        }
        this.corePoolSize = corePoolSize;
        return this;
    }

    public ShardingComputer maximumPoolSize(int maximumPoolSize) {
        if (maximumPoolSize <= shardingNum) {
            maximumPoolSize = shardingNum + Runtime.getRuntime().availableProcessors() * 10;
        }
        this.maximumPoolSize = maximumPoolSize;
        return this;
    }

    public ShardingComputer keepAliveTime(int keepAliveTime) {
        if (keepAliveTime < 60) {
            keepAliveTime = 60;
        }
        this.keepAliveTime = keepAliveTime;
        return this;
    }

    public ShardingComputer workQuezue(BlockingQueue<Runnable> workQuezue) {
        if (Objects.nonNull(workQuezue)) {
            this.workQuezue = workQuezue;
        }
        return this;
    }

    public ShardingComputer shardingNum(int shardingNum) {
        if (shardingNum < Runtime.getRuntime().availableProcessors() * 5) {
            shardingNum = Runtime.getRuntime().availableProcessors() * 5;
        }
        this.shardingNum = shardingNum;
        sharding();
        return this;
    }

    public ShardingComputer threadGroupName(String threadGroupName) {
        this.threadGroupName = threadGroupName;
        return this;
    }

}
