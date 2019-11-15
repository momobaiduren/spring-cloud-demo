package com.demo;

import java.util.List;
import java.util.Map;

/**
 * created by zhanglong and since  2019/11/14  5:27 下午
 *
 * @description: 描述
 */
public interface ShardingHandler {

    default void banchExecut( List<Integer> shardingData,
        Map<Class<? extends ShardingHandler>, List<?>> result ){
        shardingData.forEach(sharding -> execut(sharding, result));
    }
    /**
     * create by ZhangLong on 2019/11/14
     * description 业务处理
     * @param sharding 如果=null 就是直接一次全部处理
     * @param result 直接放入结果集
     */
    void execut( Integer sharding, Map<Class<? extends ShardingHandler>, List<?>> result );
    /**
     * create by ZhangLong on 2019/11/14
     * description 补偿数据
     */
    void compensate( Map<Class<? extends ShardingHandler>, List<?>> result );
    /**
     * create by ZhangLong on 2019/11/14
     * description 获取处理数据的总量，根据索引或者主键查询
     */
    int count();
}
