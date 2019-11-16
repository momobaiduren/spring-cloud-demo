package com.demo.thread.computer;

import com.demo.Jdk8Api.Apple;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.apache.commons.collections4.CollectionUtils;

public class MergeHandlerImpl implements MergeHandler<Apple, ListResult<Apple>, Object> {

    @Override
    public void execute( Integer sharding, Consumer<ListResult<Apple>> consumer,
        Object conditions ) {
        List<Apple> apples = new ArrayList<>(10000);
        for (int i = 0; i < 10000; i++) {
            Apple apple = new Apple(BigDecimal.ONE);
            apples.add(apple);
        }
        consumer.accept(new ListResult<>(apples));
//        System.out
//            .println(apples.stream().map(Apple::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    @Override
    public void execute( Consumer<ListResult<Apple>> consumer, Object conditions ) {
        List<Apple> apples = new ArrayList<>(10000000);
        for (int i = 0; i < 10000000; i++) {
            Apple apple = new Apple(BigDecimal.ONE);
            apples.add(apple);
        }
        consumer.accept(new ListResult<>(apples));
    }

    @Override
    public void compensate( Consumer<ListResult<Apple>> consumer, Object conditions ) {
        System.out.println("补偿成功");
    }

    @Override
    public int count( Object conditions ) {
        return 1000;
    }
}
