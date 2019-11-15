package com.demo.thread;

import com.demo.thread.computer.ShardingOperation;
import com.demo.thread.computer.UnpaymentReportComputerHandler;

import java.util.concurrent.LinkedBlockingQueue;

public class ShardingDemo {
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis()/1000);
        ShardingOperation.instance().run(new UnpaymentReportComputerHandler());

        System.out.println(System.currentTimeMillis()/1000);
    }
}
