package com.demo.forkjoin;

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


    public <R> void run(Consumer<R> consumer){
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> taskFuture =  pool.submit(new MyForkJoinTask(1,100000000));
        try {
            Integer result = taskFuture.get();
            System.out.println("result = " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace(System.out);
        }
    }


}
