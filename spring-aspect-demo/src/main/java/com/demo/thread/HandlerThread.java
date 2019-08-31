package com.demo.thread;

import java.util.Map;
import java.util.Objects;

/**
 * @author zhanglong
 * @description: 描述
 * @date 2019-08-2817:26
 */
public class HandlerThread<K, D> implements Runnable {

    private Map<K, D> dataMap;
    private Map<K, D> threadContext;

    public HandlerThread( Map<K, D> threadContext, Map<K, D> datamap ) {
        this.threadContext = threadContext;
        this.dataMap = datamap;
    }

    @Override
    public void run() {
        while (Objects.isNull(dataMap) || dataMap.isEmpty()) {
            if (Objects.nonNull(threadContext)) {
                Map<K, D> map = threadContext;
                for (K k : map.keySet()) {
                    System.out.println("k:" + map.get(k));
                }
                threadContext.clear();
            }
        }
    }
}
