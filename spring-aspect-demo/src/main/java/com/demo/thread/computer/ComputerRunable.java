package com.demo.thread.computer;

import java.util.List;

public class ComputerRunable implements Runnable {

    private ComputerHandler computerHandler;
    private List<Integer> shardingData;

    public ComputerRunable(List<Integer> shardingData, ComputerHandler computerHandler) {
        this.computerHandler = computerHandler;
        this.shardingData = shardingData;
    }

    @Override
    public void run() {
        computerHandler.execut(shardingData);
    }
}
