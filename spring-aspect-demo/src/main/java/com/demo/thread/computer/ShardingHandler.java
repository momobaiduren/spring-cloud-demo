package com.demo.thread.computer;

/**
 * created by zhanglong and since  2019/11/14  5:27 下午
 *
 * @description: 描述
 */
public interface ShardingHandler {

    /**
     * create by ZhangLong on 2019/11/14
     * description 获取处理数据的总量，根据索引或者主键查询
     */
    <C> int count( C conditions );


}
