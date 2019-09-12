package com.demo.thread;

import com.demo.thread.computer.ShardingComputer;
import com.demo.thread.computer.UnpaymentReportComputerHandler;

import java.util.concurrent.SynchronousQueue;

public class ShardingDemo {
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis()/1000);
        ShardingComputer
                .instance(new UnpaymentReportComputerHandler())
                .keepAliveTime(2)
                .corePoolSize(0)
                .maximumPoolSize(10)
                .shardingNum(5)
                .workQuezue(new SynchronousQueue<>())
                .computer();
        System.out.println(System.currentTimeMillis()/1000);
    }
}
