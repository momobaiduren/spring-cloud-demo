package com.demo.cache;

import java.lang.ref.SoftReference;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author ZhangLong on 2019/10/22  9:02 下午
 * @version V1.0
 */
public final class SoftCache<K, V> implements Cache<K,V>{

    public SoftCache() {
        dealSoftCache();
    }

    private AtomicBoolean start = new AtomicBoolean(true);

    /**
     * description   default value=16
     */
    private final SoftReference<Map<K, CacheNode<K, V>>> softReferenceCache = new SoftReference<>(new ConcurrentHashMap<>());

    public void cache(K key, V val){
        cache(key, val, null, null);
    }
    public void cache(K key, V val, Long expire){
        cache(key, val, expire, TimeUnit.SECONDS);
    }
    @Override
    public void cache(K key, V val, Long expire, TimeUnit timeUnit){
        Objects.requireNonNull(key,"key could not be null");
        Objects.requireNonNull(val,"val could not be null");
        LocalDateTime expireTime = LocalDateTime.now();
        switch (timeUnit) {
            case SECONDS:
                expireTime = expireTime.plusSeconds(expire);
                break;
            case MINUTES:
                expireTime = expireTime.plusMinutes(expire);
                break;
            case HOURS:
                expireTime = expireTime.plusHours(expire);
                break;
            case DAYS:
                expireTime = expireTime.plusDays(expire);
                break;
        }
        CacheNode<K, V> cacheNode =  new CacheNode<>(key, val, expireTime, timeUnit);
        Objects.requireNonNull(softReferenceCache.get()).put(key, cacheNode);
    }
    @Override
    public synchronized void dealSoftCache(){
        do {
            start.set(false);
            new Thread(()->{
                while (true){
                    Map<K, CacheNode<K, V>> cacheNodes = softReferenceCache.get();
                    assert cacheNodes != null;
                    cacheNodes.forEach((key, cacheNode) -> {
                        if (Objects.isNull(cacheNode)){
                            cacheNodes.remove(key);
                        }
                        if (Objects.nonNull(cacheNode.getDeadline())){
                            if (LocalDateTime.now().isAfter(cacheNode.getDeadline())){
                                cacheNodes.remove(key);
                            }
                        }
                    });
                    try {
                        Thread.sleep(100);
                        if(cacheNodes.isEmpty()){
                            Thread.sleep(60 * 10000);
                        }
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                }
            }).start();
        }while (start.get());
    }
    @Override
    public V get(K key){
        Map<K, CacheNode<K, V>> cacheNodeMap = softReferenceCache.get();
        assert cacheNodeMap != null;
        CacheNode<K, V> cacheNode = cacheNodeMap.get(key);
        return cacheNode == null ? null : cacheNode.getVal();
    }


}
