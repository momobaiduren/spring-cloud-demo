package com.demo.thread.computer;

import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class UnpaymentReportComputerHandler implements ComputerHandler {

    @Override
    public void execut() {
        System.out.println("全部执行了");
    }

    @Override
    public int count() {
        return 2000000;
    }

    @Override
    public void execut( Integer sharding ) {
        System.out.println("执行sharding====>"+sharding);

    }

    @Override
    public void compensate() {
        System.out.println("进行了补偿");

    }
}
