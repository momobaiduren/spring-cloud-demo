package com.demo.callback;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.function.Function;

/**
 * @author zhanglong
 * @version V1.0
 * @date 2019/9/21  9:01 下午
 */
@Slf4j
public class RetryUtils {
    public static <R, T> T retry(int maxRetryTimes, R r, Function<R, T> function, Long... intervals) {
        if (maxRetryTimes <= 0) {
            maxRetryTimes = 3;
        }
        T t = null;
        ExecutorService executor = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < maxRetryTimes; i++) {
                log.info("重试次数: 第 {} 次", i);
                try {
                    FutureTask<T> ft = new FutureTask<>(new Callable<T>() {
                        @Override
                        public T call() throws Exception {
                            return function.apply(r);
                        }
                    });
                    executor.submit(ft);
                    while (!ft.isDone()){
                        t = ft.get();
                        if (Objects.nonNull(t)){
                            return t;
                        }
                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                    Long interval = 10000L;
                    if (i < intervals.length){
                        if (Objects.nonNull(intervals[i]) && intervals[i] > 0) {
                            interval = intervals[i];
                        }
                    }
                    log.error("异常失败！重试次数: 第 {} 次; 重试时间间隔: {} ms", i, interval);
                    try {
                        Thread.currentThread().sleep(interval);
                    } catch (InterruptedException ex) {
                        log.error(ex.getMessage());
                    }
                }
            }
        } finally {
            executor.shutdown();
        }
        return t;
    }

    public static void main(String[] args) {
        String retry = retry(10,"lisi", (r) -> {
            return "你好！"+r;
        }, new Long[]{3l, 5l});
        System.out.println(retry);
    }

}
