package com.demo.forkjoin;

import com.demo.thread.computer.ShardingHandler;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * created by zhanglong and since  2019/11/17  1:07 上午
 *
 * @description: 描述
 */
@Data
@AllArgsConstructor
public class HandlerContext<H extends ShardingHandler> {
    private Class<H> handlerClass;
    private H shardingHander;
    private Object conditions;

}
