package com.demo.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @author ZhangLong on 2019/11/19  8:51 &#x4e0b;&#x5348;
 * @version V1.0
 */
class MyForkJoinTask extends RecursiveTask<Integer> {

    private Integer executeMinUnit = 30;
    // 子任务开始计算的值
    private Integer startValue;

    // 子任务结束计算的值
    private Integer endValue;

    public MyForkJoinTask(Integer startValue , Integer endValue) {
        this.startValue = startValue;
        this.endValue = endValue;
    }

    @Override
    protected Integer compute() {
        // 如果条件成立，说明这个任务所需要计算的数值分为足够小了
        // 可以正式进行累加计算了
        if(endValue - startValue < executeMinUnit) {
            System.out.println("开始计算的部分：startValue = " + startValue + ";endValue = " + endValue);
            Integer totalValue = 0;
            for(int index = this.startValue ; index <= this.endValue  ; index++) {
                totalValue += index;
            }
            return totalValue;
        }
        // 否则再进行任务拆分，拆分成两个任务
        else {
            MyForkJoinTask subTask1 = new MyForkJoinTask(startValue, (startValue + endValue) / 2);
            subTask1.fork();
            MyForkJoinTask subTask2 = new MyForkJoinTask((startValue + endValue) / 2 + 1 , endValue);
            subTask2.fork();
            return subTask1.join() + subTask2.join();
        }
    }
}
