package com.springcloud.cache;

import java.util.concurrent.TimeUnit;

/**
 * @author ZhangLong on 2019/10/22  9:02 下午
 * @version V1.0
 */
public interface Cache<K, V> {

    void cache(K key, V val, Long expire, TimeUnit timeUnit);

    V get(K key);

    V getIfDefault(K key, V val, Long expire, TimeUnit timeUnit);

    void remove(K key);

    void clear();

    void cache(K key, V val, Long expire);
}
