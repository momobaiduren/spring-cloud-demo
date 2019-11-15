package com.demo.thread.computer;

import java.util.List;
import java.util.Map;

/**
 * @author  zhanglong on 2019-09-13  12:08 上午
 * @version V1.0
 */
interface ComputerHandler extends ShardingHandler {
    default void banchExecut( List<Integer> shardingData ){
        shardingData.forEach(this::execut);
    }
    /**
     * create by ZhangLong on 2019/11/14
     * description 写入Map参数中{@link MergeHandler#execut(Integer, Map)} 结果
     * @param sharding 分片参数
     */
    void execut( Integer sharding );

    /**
     * create by ZhangLong on 2019/11/14
     * description 补偿数据
     */
    void compensate();

}

