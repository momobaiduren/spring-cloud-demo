package com.demo.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author ZhangLong on 2019/11/19  9:01 下午
 * @version V1.0
 */
public class ExecuteMain {
    public static void main(String[] args) {
        long x = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> taskFuture =  pool.submit(new MyForkJoinTask(1,100000000));
        try {
            Integer result = taskFuture.get();
            System.out.println("result = " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace(System.out);
        }
        System.out.println(System.currentTimeMillis() - x);

    }

}
