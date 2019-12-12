package com.springcloud.cache;

import lombok.extern.slf4j.Slf4j;

import java.lang.ref.SoftReference;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author ZhangLong on 2019/10/22  9:02 下午
 * @version V1.0
 */
@Slf4j
public abstract class SoftCache<K, V> implements Cache<K, V> {

    public SoftCache() {
        dealSoftCache();
    }

    /**
     * description 缓存监控开关
     */
    private AtomicBoolean switchMonitor = new AtomicBoolean(true);

    /**
     * description   default value=16
     */
    private final SoftReference<Map<K, CacheNode<K, V>>> softReferenceCache = new SoftReference<>(new ConcurrentHashMap<>());

    public void cache(K key, V val) {
        cache(key, val, null);
    }

    @Override
    public void cache(K key, V val, Long expire) {
        cache(key, val, expire, TimeUnit.SECONDS);
    }

    public void remove(K key) {
        Map<K, CacheNode<K, V>> cacheNodes = Objects.requireNonNull(softReferenceCache.get());
        Iterator<Map.Entry<K, CacheNode<K, V>>> iterator = cacheNodes.entrySet().iterator();
        if (iterator.hasNext()) {
            Map.Entry<K, CacheNode<K, V>> next = iterator.next();
            if (key.equals(next.getKey())) {
                iterator.remove();
            }
        }
    }

    @Override
    public void cache(K key, V val, Long expire, TimeUnit timeUnit) {
        Objects.requireNonNull(key, "key could not be null");
        Objects.requireNonNull(val, "val could not be null");
        LocalDateTime expireTime = plusExpireTime(expire, timeUnit);
        CacheNode<K, V> kvCacheNode = Objects.requireNonNull(softReferenceCache.get()).get(key);
        CacheNode<K, V> cacheNode = new CacheNode<>(key, val, expireTime, timeUnit);
        Objects.requireNonNull(softReferenceCache.get()).put(key, cacheNode);
    }

    private LocalDateTime plusExpireTime(Long expire, TimeUnit timeUnit) {
        LocalDateTime expireTime = null;
        if (Objects.nonNull(expire) && expire > 0) {
            switch (timeUnit) {
                case SECONDS:
                    expireTime = LocalDateTime.now().plusSeconds(expire);
                    break;
                case MINUTES:
                    expireTime = LocalDateTime.now().plusMinutes(expire);
                    break;
                case HOURS:
                    expireTime = LocalDateTime.now().plusHours(expire);
                    break;
                case DAYS:
                    expireTime = LocalDateTime.now().plusDays(expire);
                    break;
            }
        }
        return expireTime;
    }

    /**
     * create by ZhangLong on 2019/11/2
     * description 守护线程进行清除定时缓存
     */
    private void dealSoftCache() {
        do {
            switchMonitor.set(false);
            Thread thread = new Thread(() -> {
                while (true) {
                    Map<K, CacheNode<K, V>> cacheNodes = Objects.requireNonNull(softReferenceCache.get());
                    Iterator<Map.Entry<K, CacheNode<K, V>>> iterator = cacheNodes.entrySet().iterator();
                    if (iterator.hasNext()) {
                        Map.Entry<K, CacheNode<K, V>> next = iterator.next();
                        if (Objects.isNull(next.getValue())) {
                            iterator.remove();
                        } else {
                            if (Objects.nonNull(next.getValue().getDeadline())
                                    && LocalDateTime.now().isAfter(next.getValue().getDeadline())) {
                                iterator.remove();
                            }
                        }
                    }
                }
            });
            thread.setDaemon(true);
            thread.start();
        } while (switchMonitor.get());
    }


    @Override
    public V get(K key) {
        Map<K, CacheNode<K, V>> cacheNodeMap = new HashMap<>(Objects.requireNonNull(softReferenceCache.get()));
        CacheNode<K, V> cacheNode = cacheNodeMap.get(key);
        return cacheNode == null ? null : cacheNode.getVal();
    }

    public V getIfDefault(K key, V defaultValue) {
        return getIfDefault(key, defaultValue, null, null);
    }

    @Override
    public V getIfDefault(K key, V defaultValue, Long expire, TimeUnit timeUnit) {
        Objects.requireNonNull(defaultValue);
        Map<K, CacheNode<K, V>> cacheNodeMap = new HashMap<>(Objects.requireNonNull(softReferenceCache.get()));
        CacheNode<K, V> kvCacheNode = cacheNodeMap.computeIfAbsent(key,
                k -> new CacheNode<>(k, defaultValue, plusExpireTime(expire, timeUnit), timeUnit));
        return kvCacheNode.getVal();
    }


    @Override
    public void clear() {
        Objects.requireNonNull(softReferenceCache.get()).clear();
    }


}
