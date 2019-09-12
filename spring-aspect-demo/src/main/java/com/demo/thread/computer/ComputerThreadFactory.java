package com.demo.thread.computer;

import java.util.Objects;
import java.util.concurrent.ThreadFactory;

public class ComputerThreadFactory implements ThreadFactory {

    private static final String DEFAULTTHREADGROUPNAME = "DEFAULT-THREAD-GROUP";

    private ThreadGroup threadGroup;

    private String threadName;

    public ComputerThreadFactory bindThreadGroup(ThreadGroup threadGroup){
        this.threadGroup = threadGroup;
        return this;
    }

    public ComputerThreadFactory bindingThreadName(String threadName){
        this.threadName = threadName;
        return this;
    }

    @Override
    public Thread newThread(Runnable target) {
        if (Objects.isNull(threadGroup)){
            threadGroup = new ThreadGroup(DEFAULTTHREADGROUPNAME);
        }
        Thread thread = new Thread(threadGroup, target, threadName);
        thread.setPriority(Thread.NORM_PRIORITY);
        thread.setUncaughtExceptionHandler(Thread.getDefaultUncaughtExceptionHandler());
        return thread;
    }
}
