package com.springcloud.computer;


import java.util.*;
import java.util.concurrent.*;

/**
 * @author zhanglong
 * @version V1.0
 * @date 2019-09-12 14:33
 */
public class ShardingComputer {

    private ShardingComputer() {
    }

    private int corePoolSize = 0;

    private int maximumPoolSize = Runtime.getRuntime().availableProcessors() * 10;

    private long keepAliveTime = 1800;

    private BlockingQueue<Runnable> workQuezue = new SynchronousQueue<>();

    private int count;

    private int shardingNum = Runtime.getRuntime().availableProcessors() * 5;

    private String threadGroupName;

    private ComputerThreadFactory threadFactory;

    private ThreadPoolExecutor threadPoolExecutor;

    private ComputerHandler computerHandler;

    public ShardingComputer threadGroupName(String threadGroupName) {
        this.threadGroupName = threadGroupName;
        return this;
    }

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


    public void computer() {
        if (count <= 0) {
            System.err.println("handler num is Zero");
            return;
        }
        if (Objects.isNull(threadFactory) || Objects.isNull(threadPoolExecutor)) {
            init();
        }
        Map<Integer, List<Integer>> shardingDataMap = sharding();
        shardingDataMap.forEach((sharding, shardingData) -> {
            Thread thread = threadFactory
                    .bindingThreadName("THREAD-SHARDING-COMPUTER-" + sharding)
                    .newThread(() -> {
                        computerHandler.execut(shardingData);
                    });
            threadPoolExecutor.execute(thread);
        });
        Thread thread =  threadFactory
                .bindingThreadName("THREAD-SHARDING-COMPUTER-COMPENSATE")
                .newThread(() -> {
                    computerHandler.compensate();
                });
        threadPoolExecutor.execute(thread);
        threadPoolExecutor.shutdown();
    }

    private void init() {
        threadFactory = new ComputerThreadFactory().bindThreadGroup(new ThreadGroup(threadGroupName));
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
        return this;
    }

}
