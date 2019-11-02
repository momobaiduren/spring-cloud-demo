package com.springcloud.computer;

import java.util.Objects;
import java.util.concurrent.ThreadFactory;

/**
 * @Author zhanglong
 * @Version V1.0.0
 * @Date 2019-09-13
 */
public class ComputerThreadFactory implements ThreadFactory {

    public static final String DEFAULTTHREADGROUPNAME = "DEFAULT-COMPUTER-THREAD-GROUP";

    private ThreadGroup threadGroup;

    private String threadName;


    public ComputerThreadFactory bindingThreadName(ThreadGroup threadGroup, String threadName){
        this.threadGroup = threadGroup;
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
