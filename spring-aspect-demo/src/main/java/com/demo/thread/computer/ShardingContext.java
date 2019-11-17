package com.demo.thread.computer;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * created by zhanglong and since  2019/11/17  1:07 上午
 *
 * @description: 描述
 */
@Data
@AllArgsConstructor
public class ShardingContext<H extends ShardingHandler, R, C> {
    private Class<H> handlerClass;
    private Class<R> rClass;
    private H shardingHander;
    private C conditions;

}
