package com.springcloud.cache;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangLong on 2019/10/22  8:55 下午
 * @version V1.0 缓存数据
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CacheNode<K, V> {
    /**
     * description 缓存的key
     */
    private K key;
   /**
    * description 缓存的值
    */
   private V val;
    /**
     * description 缓存版本问题，防止版本不一致
     */
    private long version;
    /**
     * description 缓存的截止日期
     */
    private LocalDateTime deadline;

    /**
     * description 缓存时间单位 默认是秒
     */
    private TimeUnit timeUnit = TimeUnit.SECONDS;
}
