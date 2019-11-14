package com.demo;


import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

    private ThreadPoolProperties threadPoolProperties;


    private ShardingComputer() {
    }

    /**
     * description 分片数量
     */
    private int shardingNum = Runtime.getRuntime().availableProcessors() * 5;
    /**
     * description 分片处理类
     */
    private ComputerHandler computerHandler;

    private int shardingDealMinCount = 300;
    /**
     * create by ZhangLong on 2019/11/3 description 初始化
     */
    public static ShardingComputer instance(ComputerHandler computerHandler) {
        Objects.requireNonNull(computerHandler, "computerHandler could not be null");
        ShardingComputer computor = new ShardingComputer();
        computor.computerHandler = computerHandler;
        return computor;
    }

    /**
     * create by ZhangLong on 2019/11/2 description 计算
     */
    public void computer() {
        int count = computerHandler.count();
        if (count <= 0) {
            log.error("handler num is Zero");
            return;
        }
        if (shardingDealMinCount >= count){
            computerHandler.execut(null);
        }
        if (Objects.isNull(threadPoolProperties)) {
            this.threadPoolProperties = new ThreadPoolProperties();
        }
        CustomThreadFactory threadFactory = new CustomThreadFactory();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(threadPoolProperties.getCorePoolSize(),
                threadPoolProperties.getMaximumPoolSize(), threadPoolProperties.getKeepAliveTime(),
                TimeUnit.SECONDS, threadPoolProperties.getWorkQuezue(), threadFactory);
        Map<Integer, List<Integer>> shardingDataMap = Sharding.sharding(shardingNum, count);
        shardingDataMap.forEach((sharding, shardingData) -> {
            Thread thread = threadFactory
                    .bindingThreadName(new ThreadGroup(threadPoolProperties.getThreadGroupName()),
                            COMPUTER_THREAD_NAME_PREFIX + sharding)
                    .newThread(() -> {
                        computerHandler.banchExecut(shardingData);
                    });
            threadPoolExecutor.execute(thread);
        });
        Thread thread = threadFactory
                .bindingThreadName(new ThreadGroup(COMPENSATE_GROUP_NAME), COMPENSATE_THREAD_NAME)
                .newThread(() -> computerHandler.compensate());
        threadPoolExecutor.execute(thread);
        threadPoolExecutor.shutdown();
    }

    public ShardingComputer threadPoolProperties(ThreadPoolProperties threadPoolProperties) {
        if (Objects.isNull(threadPoolProperties)) {
            this.threadPoolProperties = new ThreadPoolProperties();
        }
        return this;
    }

}
