package com.demo.forkjoin;

import com.demo.thread.computer.ShardingHandler;

import java.util.concurrent.RecursiveTask;

/**
 * @author ZhangLong on 2019/11/19  9:30 下午
 * @version V1.0
 */
public class ForkJoinAction<H extends ShardingHandler,R> extends RecursiveTask<R> {

    private HandlerContext<H> handlerContext;

    private int executeMinUnit;

    public <H extends ShardingHandler> ForkJoinAction(HandlerContext<H> handlerContext, int executeMinUnit) {

    }

    @Override
    protected R compute() {
        int count = handlerContext.getShardingHander().count(handlerContext.getConditions());
        if (count > executeMinUnit){
//            count/2
        }else {

        }
        return null;
    }
}
