package com.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by zhanglong and since  2019/11/14  5:52 下午
 *
 * @description: 描述
 */
public final class Sharding {
    private Sharding(){}
    /**
     * description 分片数从1开始执行，如果索引是0的要加1
     */
    public static Map<Integer, List<Integer>> sharding(int shardingNum, int count) {
        Map<Integer, List<Integer>> shardingDataMap = new HashMap<>(shardingNum);
        for (int i = 1; i <= count; i++) {
            if (shardingDataMap.containsKey(i % shardingNum)) {
                shardingDataMap.get(i % shardingNum).add(i);
            } else {
                List<Integer> shardingData = new ArrayList<>(count / shardingNum + 1);
                shardingData.add(i);
                shardingDataMap.put(i % shardingNum, shardingData);
            }
        }
        return shardingDataMap;
    }
}
