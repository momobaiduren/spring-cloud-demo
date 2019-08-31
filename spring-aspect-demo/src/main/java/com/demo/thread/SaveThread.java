package com.demo.thread;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhanglong
 * @description: 描述
 * @date 2019-08-2817:25
 */
public class SaveThread<K, D> implements Runnable {

    private Map<K, D> dataMap;
    Map<K, D> threadContext;


    public SaveThread( Map<K, D> threadContext, Map<K, D> dataMap ) {
        this.threadContext = threadContext;
        this.dataMap = dataMap;
    }

    @Override
    public void run() {
        if (Objects.nonNull(threadContext)) {
            if (Objects.nonNull(dataMap)) {
                Optional.ofNullable(threadContext).orElse(new ConcurrentHashMap<>());
                threadContext.putAll(dataMap);
                dataMap.clear();
            }
        }
    }
}
