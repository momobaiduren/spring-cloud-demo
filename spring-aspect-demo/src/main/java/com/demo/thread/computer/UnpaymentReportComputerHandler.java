package com.demo.thread.computer;

import java.util.List;

public class UnpaymentReportComputerHandler extends ComputerHandler{
    @Override
    public void execut(List<Integer> shardingData) {
        System.out.println(shardingData);
    }

    @Override
    public int count() {
        return 211;
    }
}
