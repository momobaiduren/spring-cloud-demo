package com.demo.thread.computer;

import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class UnpaymentReportComputerHandler extends ComputerHandler {
    AtomicInteger atomicInteger = new AtomicInteger(1);
    @Override
    public void execut(List<Integer> shardingData) {
        System.out.println("--------"+atomicInteger.getAndIncrement());
        System.out.println(JSON.toJSONString(shardingData));
    }

    @Override
    public int count() {
        return 2000000;
    }
}
