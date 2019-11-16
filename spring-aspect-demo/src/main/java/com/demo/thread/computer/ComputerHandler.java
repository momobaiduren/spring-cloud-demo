package com.demo.thread.computer;

import java.util.List;

/**
 * @author zhanglong on 2019-09-13  12:08 上午
 * @version V1.0
 */
public interface ComputerHandler<C> extends ShardingHandler {

    default void banchExecut( List<Integer> shardingData, C conditions ) {
        shardingData.forEach(sharding -> execut(sharding, conditions));
    }

    /**
     * create by ZhangLong on 2019/11/14
     * description 写入Map参数中{@link ComputerHandler#execut(Integer, Object)} 结果
     *
     * @param sharding 分片参数
     */
    void execut( Integer sharding, C conditions );

    /**
     * create by ZhangLong on 2019/11/14 description 补偿数据
     */
    void compensate( C conditions );

    void execut( C conditions );
}

