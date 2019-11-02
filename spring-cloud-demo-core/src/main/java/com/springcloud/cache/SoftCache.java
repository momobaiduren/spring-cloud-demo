package com.springcloud.cache;

import lombok.Synchronized;
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
public abstract class SoftCache<K, V> implements Cache<K,V>{

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

    public void cache(K key, V val, long expire){
        cache(key, val, expire, TimeUnit.SECONDS);
    }

    public void remove(K key){
        Map<K, CacheNode<K, V>> cacheNodes = Objects.requireNonNull(softReferenceCache.get());
        Iterator<Map.Entry<K, CacheNode<K, V>>> iterator = cacheNodes.entrySet().iterator();
        if (iterator.hasNext()){
            Map.Entry<K, CacheNode<K, V>> next = iterator.next();
            if (key.equals(next.getKey())){
                iterator.remove();
            }
        }
    }

    @Override
    public void cache(K key, V val, long expire, TimeUnit timeUnit){
        Objects.requireNonNull(key, "key could not be null");
        Objects.requireNonNull(val, "val could not be null");
        LocalDateTime expireTime = LocalDateTime.now();
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
//        Objects.requireNonNull(softReferenceCache.get()).putIfAbsent(key, new CacheNode<>(key, val, expireTime, timeUnit));
        CacheNode<K, V> kvCacheNode = Objects.requireNonNull(softReferenceCache.get()).get(key);
        if (Objects.nonNull(kvCacheNode)){
            kvCacheNode.setTimeUnit(timeUnit);
            kvCacheNode.setVal(val);
            kvCacheNode.setDeadline(expireTime);
            Objects.requireNonNull(softReferenceCache.get()).put(key,kvCacheNode);
        }else {
            CacheNode<K, V> cacheNode =  new CacheNode<>(key, val, expireTime, timeUnit);
            Objects.requireNonNull(softReferenceCache.get()).put(key, cacheNode);
        }
    }

    @Synchronized
    public void dealSoftCache(){
        do {
            switchMonitor.set(false);
            new Thread(()->{
                while (true){
                    Map<K, CacheNode<K, V>> cacheNodes = Objects.requireNonNull(softReferenceCache.get());
                    Iterator<Map.Entry<K, CacheNode<K, V>>> iterator = cacheNodes.entrySet().iterator();
                    if (iterator.hasNext()){
                        Map.Entry<K, CacheNode<K, V>> next = iterator.next();
                        if (Objects.isNull(next.getValue())){
                            iterator.remove();
                        }else {
                           if (Objects.nonNull(next.getValue().getDeadline())
                                   && LocalDateTime.now().isAfter(next.getValue().getDeadline())){
                                iterator.remove();
                           }
                        }
                    }
                    try {
                        Thread.sleep(1000);
                        if(cacheNodes.isEmpty()){
                            Thread.sleep(10000);
                        }
                    } catch (InterruptedException e) {
                        log.error(e.getMessage());
                    }
                }
            }).start();
        }while (switchMonitor.get());
    }


    @Override
    public V get(K key){
        Map<K, CacheNode<K, V>> cacheNodeMap = new HashMap<>(Objects.requireNonNull(softReferenceCache.get()));
        CacheNode<K, V> cacheNode = cacheNodeMap.get(key);
        return cacheNode == null ? null : cacheNode.getVal();
    }



    @Override
    public void clear() {
        Objects.requireNonNull(softReferenceCache.get()).clear();
    }


}
