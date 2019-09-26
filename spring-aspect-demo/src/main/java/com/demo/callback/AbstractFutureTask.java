package com.demo.callback;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author zhanglong
 * @version V1.0
 * @date 2019/9/21  10:47 下午
 */
public abstract class AbstractFutureTask<T> extends FutureTask<T> {
    public AbstractFutureTask(Callable<T> callable) {
        super(callable);
    }

    public AbstractFutureTask(Runnable runnable, T result) {
        super(runnable, result);
    }

    public void callBack(){

    }
}
