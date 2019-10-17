package com.demo.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ZhangLong on 2019/10/10  5:21 下午
 * @version V1.0
 */
public abstract class AbstractCache<T> {
    Map<String, Class<T>> NAMEKLASSCACHE = new ConcurrentHashMap<>();

    Map<String, T> DATACACHE = new ConcurrentHashMap<>();
}
