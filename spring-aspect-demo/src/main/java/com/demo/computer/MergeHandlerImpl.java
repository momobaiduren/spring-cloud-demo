package com.demo.computer;

import com.demo.Jdk8Api.Apple;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MergeHandlerImpl implements ComputerHandler{

//    @Override
//    public void execute( Integer shardingNum, BiConsumer<Integer, List<Apple>> consumerResult, Integer conditions ) {
//        List<Apple> apples = new ArrayList<>(conditions);
//        for (int i = 0; i < conditions; i++) {
//            Apple apple = new Apple(BigDecimal.ONE);
//            apples.add(apple);
//        }
//        consumerResult.accept(shardingNum, apples);
//    }
//
//    @Override
//    public void execute( Consumer<List<Apple>> consumer, Integer conditions ) {
//        List<Apple> apples = new ArrayList<>(conditions);
//        for (int i = 0; i < conditions; i++) {
//            Apple apple = new Apple(BigDecimal.ONE);
//            apples.add(apple);
//        }
//        consumer.accept(apples);
//    }
//
//    @Override
//    public void compensate( Consumer<List<Apple>> consumer, Integer conditions ) {
//        System.out.println("补偿成功");
//    }

    @Override
    public void execut( Integer shardingNum, Consumer<Object> resultConsumer, Object conditions ) {
        List<Apple> apples = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Apple apple = new Apple(BigDecimal.ONE);
            apples.add(apple);
        }
        resultConsumer.accept(apples);
    }

    @Override
    public void execut( Consumer<Object> resultConsumer, Object conditions ) {

    }

    @Override
    public int count( Object conditions ) {
        return 20000;
    }
}
