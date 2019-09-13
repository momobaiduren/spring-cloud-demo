package com.demo.thread.computer;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;
/**
 * 多线程数据报表批处理
 * @Author zhanglong
 * @Version V1.0.0
 * @Date 2019-09-12
 */
@Slf4j
public class ShardingComputer {

    private ShardingComputer() {}

    private int corePoolSize = 0;

    private int maximumPoolSize = 10;

    private long keepAliveTime = 30;

    private BlockingQueue<Runnable> workQuezue = new SynchronousQueue<>();

    private int count;

    private int shardingNum = Runtime.getRuntime().availableProcessors() * 3;

    private String threadGroupName;

    private ComputerThreadFactory threadFactory;

    private ThreadPoolExecutor threadPoolExecutor;

    private ComputerHandler computerHandler;

    public ShardingComputer threadGroupName(String threadGroupName) {
        this.threadGroupName = threadGroupName;
        return this;
    }

    public static ShardingComputer instance(ComputerHandler computerHandler) {
        ShardingComputer computor = new ShardingComputer();
        computor.computerHandler = computerHandler;
        computor.count = computerHandler.count();
        computor.init();
        return computor;
    }

    private Map<Integer, List<Integer>> sharding() {
        Map<Integer, List<Integer>> shardingDataMap = new ConcurrentHashMap<>();
        for (int i = 0; i < count; i++) {
            if (shardingDataMap.containsKey(i % shardingNum)) {
                shardingDataMap.get(i % shardingNum).add(i);
            } else {
                List<Integer> shardingData = new ArrayList<>();
                shardingData.add(i);
                shardingDataMap.put(i % shardingNum, shardingData);
            }
        }
        return shardingDataMap;
    }


    public void computer() {
        if (Objects.isNull(count)) {
            throw new NullPointerException("handler num could not be null");
        }
        if (count <= 0) {
            log.warn("handler num is Zero");
            return;
        }
        Map<Integer, List<Integer>> shardingDataMap = sharding();
        shardingDataMap.forEach((sharding, shardingData) -> {
            threadFactory.bindingThreadName("thread-sharding-" + sharding);
            Thread thread = threadFactory.newThread(new ComputerRunable(shardingData, computerHandler));
            threadPoolExecutor.execute(thread);
        });
        threadPoolExecutor.shutdown();
    }

    private void init() {
        threadFactory = new ComputerThreadFactory().bindThreadGroup(new ThreadGroup(threadGroupName));
        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQuezue);
    }

    public ShardingComputer corePoolSize(int corePoolSize) {
        if (Objects.nonNull(corePoolSize)) {
            this.corePoolSize = corePoolSize;
        }
        return this;
    }

    public ShardingComputer maximumPoolSize(int maximumPoolSize) {
        if (Objects.nonNull(maximumPoolSize)) {
            this.maximumPoolSize = maximumPoolSize;
        }
        return this;
    }

    public ShardingComputer keepAliveTime(int keepAliveTime) {
        if (Objects.nonNull(keepAliveTime)) {
            this.keepAliveTime = keepAliveTime;
        }
        return this;
    }

    public ShardingComputer workQuezue(BlockingQueue<Runnable> workQuezue) {
        if (Objects.nonNull(workQuezue)) {
            this.workQuezue = workQuezue;
        }
        return this;
    }

    public ShardingComputer shardingNum(int shardingNum) {
        if (Objects.nonNull(shardingNum)) {
            this.shardingNum = shardingNum;
        }
        return this;
    }

}
