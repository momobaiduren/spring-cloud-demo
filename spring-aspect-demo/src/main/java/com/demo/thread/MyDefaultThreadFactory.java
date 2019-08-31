package com.demo.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zhanglong
 * @description: 描述
 * @date 2019-08-2216:50
 */
public class MyDefaultThreadFactory implements ThreadFactory {

    AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public Thread newThread( Runnable r ) {
        atomicInteger.incrementAndGet();
        return null;
    }
}
