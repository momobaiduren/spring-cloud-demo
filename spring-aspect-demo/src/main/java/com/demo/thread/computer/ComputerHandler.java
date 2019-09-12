package com.demo.thread.computer;

import java.util.List;

public abstract class ComputerHandler {
   public abstract void execut(List<Integer> shardingData);

   public abstract int count();
}

