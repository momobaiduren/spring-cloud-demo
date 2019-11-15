package com.demo.thread.computer;

import java.util.List;
import java.util.Map;

/**
 * created by zhanglong and since  2019/11/14  5:27 下午
 * @description: 描述
 */
interface MergeHandler extends ShardingHandler {
    default void banchExecut( List<Integer> shardingData,
        Map<Class<? extends ShardingHandler>, List<?>> result ){
        shardingData.forEach(sharding -> execut(sharding, result));
    }

    /**
     * create by ZhangLong on 2019/11/14
     * description 写入Map参数中{@link MergeHandler#execut(Integer, Map)} 结果
     * @param sharding 如果=null 就是直接一次全部处理
     * @param result 直接写入结果集，不需要初始化
     */
    void execut( Integer sharding, Map<Class<? extends ShardingHandler>, List<?>> result );

    /**
     * create by ZhangLong on 2019/11/14
     * description 补偿数据
     * @param result
     */
    void compensate( Map<Class<? extends ShardingHandler>, List<?>> result );
}
