package com.demo.thread;

import com.demo.Jdk8Api.Apple;
import com.demo.thread.computer.MergeHandlerImpl;
import com.demo.thread.computer.ShardingContext;
import com.demo.thread.computer.ShardingWorker;
import java.util.ArrayList;
import java.util.List;

public class ShardingDemo {

    public static void main( String[] args ) {
        System.out.println(System.currentTimeMillis());
        ShardingContext shardingContext =
            new ShardingContext<>(
                MergeHandlerImpl.class, List.class, new MergeHandlerImpl(),50000);
        List<Integer> sizeList = new ArrayList<>();
        ShardingWorker.instance().shardingNum(40).run(result -> {
            System.out.println(result.size());
            result.get(shardingContext.getHandlerClass()).forEach((key,va)->{
                List<Apple> apples = (List<Apple>) va;
                sizeList.add(apples.size());

            });

        }, shardingContext);
        System.out.println(sizeList.stream().reduce((size1, size2) ->size1 + size2).get());
        System.out.println(System.currentTimeMillis());

//        List<Apple> apples = new ArrayList<>(10000000);
//        for (int i = 0; i < 2500000; i++) {
//            Apple apple = new Apple(BigDecimal.ONE);
//            apples.add(apple);
//        }
//        for (int i = 0; i < 2500000; i++) {
//            Apple apple = new Apple(BigDecimal.ONE);
//            apples.add(apple);
//        }
//        for (int i = 0; i < 2500000; i++) {
//            Apple apple = new Apple(BigDecimal.ONE);
//            apples.add(apple);
//        }
//        for (int i = 0; i < 2500000; i++) {
//            Apple apple = new Apple(BigDecimal.ONE);
//            apples.add(apple);
//        }
//        System.out.println(apples.stream().map(Apple::getPrice).reduce(BigDecimal.ZERO,BigDecimal::add));
        System.out.println(System.currentTimeMillis());
    }

}
