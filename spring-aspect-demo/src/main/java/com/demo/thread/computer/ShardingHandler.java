package com.demo.thread.computer;

/**
 * created by zhanglong and since  2019/11/14  5:27 下午
 *
 * @description: 描述
 */
public interface ShardingHandler {

    /**
     * created by zhanglong and since  2019/11/15 4:17 下午
     * 该方法是不进行分片处理的，而是直接处理
     */
    void execut();
    /**
     * create by ZhangLong on 2019/11/14
     * description 获取处理数据的总量，根据索引或者主键查询
     */
    int count();


}
