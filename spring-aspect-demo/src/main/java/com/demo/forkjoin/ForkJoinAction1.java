package com.demo.forkjoin;


import java.util.concurrent.RecursiveAction;

/**
 * @author ZhangLong on 2019/11/19  9:30 下午
 * @version V1.0
 */
public class ForkJoinAction1 extends RecursiveAction {

    private ForkJoinHandler forkJoinHandler;

    private int executeMinUnit = 2;

    private int start = 0;
    private int end;

    public ForkJoinAction1(int start, int end,ForkJoinHandler forkJoinHandler) {
        this.start = start;
        this.end = end;
        this.forkJoinHandler = forkJoinHandler;
    }

    @Override
    protected void compute() {
        if (end - start <= executeMinUnit) {
            forkJoinHandler.execute(start, end);
        } else {
            int middle = (start + end) / 2;
            ForkJoinAction1 leftTask = new ForkJoinAction1(start, middle,forkJoinHandler);
            ForkJoinAction1 rightTask = new ForkJoinAction1(middle + 1, end,forkJoinHandler);
            invokeAll(leftTask, rightTask);
        }
    }
}
