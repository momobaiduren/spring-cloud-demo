package com.demo.thread.computer;

import com.alibaba.fastjson.JSON;

import com.demo.Jdk8Api.Apple;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class UnpaymentReportComputerHandler implements ComputerHandler {

    @Override
    public void execut(Object conditions) {
        System.out.println("全部执行了");
    }

    @Override
    public int count(Object conditions) {
        return 0;
    }

    @Override
    public void execut( Integer sharding, Object conditions) {
        List<Apple> apples = new ArrayList<>(1000000);
        for (int i = 0; i < 1000000; i++) {
            apples.add(new Apple(BigDecimal.TEN));
        }
        final BigDecimal reduce = apples.stream().map(Apple::getPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
//        System.out.println("sharding:"+sharding+">>>>>>>>"+reduce);


    }

    @Override
    public void compensate(Object conditions) {
        System.out.println("进行了补偿");

    }
}
