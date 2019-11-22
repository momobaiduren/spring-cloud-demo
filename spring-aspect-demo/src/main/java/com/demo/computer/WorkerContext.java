package com.demo.computer;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * created by zhanglong and since  2019/11/17  1:07 上午
 *
 * @description: 描述
 */
@Data
@AllArgsConstructor
public class WorkerContext<H extends ComputerHandler> {
    private Class<H> handlerClass;
    private H shardingHander;
    private Object conditions;

}
