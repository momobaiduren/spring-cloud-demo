package com.demo;

import java.util.List;

/**
 * created by zhanglong and since  2019/11/14  5:27 下午
 *
 * @description: 描述
 */
public interface MergeHandler<R> {
    List<R> execut( List<Integer> shardingData );

    void compensate();

    int count();
}
