package com.demo.thread.computer;

import com.demo.Jdk8Api.Apple;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * created by zhanglong and since  2019/11/14  5:27 下午 <M extends MergeModel,R extends
 * MergeResult<M>>
 *
 * @description: 描述
 */
public interface MergeHandler<R, C> extends
    ShardingHandler {

    default void banchExecute( List<Integer> shardingData, BiConsumer<Integer, R> consumerResult, C conditions ) {
        shardingData.forEach(sharding -> execute(sharding, consumerResult, conditions));
    }

    /**
     * create by ZhangLong on 2019/11/14 description 写入Map参数中{@link MergeHandler#execute(Integer,
     * BiConsumer, C)} 结果
     *
     * @param sharding 如果=null 就是直接一次全部处理
     * @param consumerResult 直接写入结果集，不需要初始化
     */
    default void execute( Integer sharding, BiConsumer<Integer, R> consumerResult, C conditions ) {
        throw new UnsupportedOperationException(
            "cloud not impliment execute(Integer, Map, Object) method");
    }

    default void execute( Consumer<R>  consumerResult, C conditions ) {
        throw new UnsupportedOperationException("cloud not impliment execute(Map, Object) method");
    }

    /**
     * create by ZhangLong on 2019/11/14 description 补偿数据
     */
    default void compensate( Consumer<R>  consumerResult, C conditions ) {
        throw new UnsupportedOperationException(
            "cloud not impliment compensate(Map, Object) method");
    }
}
