package com.demo.thread.computer;

import com.demo.Jdk8Api.Apple;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class MergeHandlerImpl implements MergeHandler<List<Apple>, Object> {

    @Override
    public void execute( Integer shardingNum, BiConsumer<Integer, List<Apple>> consumerResult, Object conditions ) {
        List<Apple> apples = new ArrayList<>(5000);
        for (int i = 0; i < 5000; i++) {
            Apple apple = new Apple(BigDecimal.ONE);
            apples.add(apple);
        }
        consumerResult.accept(shardingNum, apples);
    }

    @Override
    public void execute( Consumer<List<Apple>> consumer, Object conditions ) {
        List<Apple> apples = new ArrayList<>(10000000);
        for (int i = 0; i < 10000000; i++) {
            Apple apple = new Apple(BigDecimal.ONE);
            apples.add(apple);
        }
        consumer.accept(apples);
    }

    @Override
    public void compensate( Consumer<List<Apple>> consumer, Object conditions ) {
        System.out.println("补偿成功");
    }

    @Override
    public int count( Object conditions ) {
        return 20000;
    }
}
