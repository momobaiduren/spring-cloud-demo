package com.demo.forkjoin;

import com.demo.thread.computer.ShardingHandler;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.Consumer;

/**
 * @author ZhangLong on 2019/11/19  9:14 下午
 * @version V1.0
 */
public class ForkJoinOperation {

    private int executeMinUnit = 2;

    private ForkJoinOperation(){}

    public static ForkJoinOperation instance(int executeMinUnit){
        ForkJoinOperation forkJoinOperation = new ForkJoinOperation();
        if (executeMinUnit > 1){
            forkJoinOperation.executeMinUnit = executeMinUnit;
        }
        return forkJoinOperation;
    }


    public <R, H extends ShardingHandler> void forkjoin(Consumer<R> consumer, HandlerContext<H> handlerContext){
        Objects.requireNonNull(handlerContext, "");
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<List<R>> taskFuture =  pool.submit(new ForkJoinAction<>(handlerContext, executeMinUnit));
        try {
            List<R> rs = taskFuture.get();
            System.out.println("result = " + rs);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace(System.out);
        }
    }


}
