package com.springcloud.common;

import com.springcloud.util.ConvertUtils;
import com.springcloud.util.PropertyUtils;
import org.springframework.core.annotation.Order;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ZhangLong on 2019/11/1  5:19 下午
 * @version V1.0
 */
@Order(200000000)
public class PropertyCache {

    public static PropertyCache Default = new PropertyCache();
    private Map<String, Object> cache = new ConcurrentHashMap<>();
    private boolean isStart = false;

    public PropertyCache() {
    }

    public void run(String... args) throws Exception {
        this.clear();
        this.isStart = true;
    }

    public <T> T get(String key, T defaultValue) {
        Object value;
        if (!this.isStart) {
            value = PropertyUtils.getProperty(key);
            if (Objects.nonNull(value)){
                return (T) ConvertUtils.convert(value, defaultValue.getClass());
            }
            return null;
        } else {
            value = this.cache.get(key);
            if (value == null) {
                Object v = PropertyUtils.getProperty(key);
                if (v != null) {
                    this.cache.put(key, v);
                } else {
                    this.cache.put(key, PropertyUtils.NULL);
                }
            }

            value = this.cache.get(key);
            if (!PropertyUtils.NULL.equals(value)){
                return (T) ConvertUtils.convert(value, defaultValue.getClass());
            }
        }
        return null;
    }

//    public void tryUpdateCache(final String key, final Object value) {
//        if (this.isStart) {
//            if (this.cache.containsKey(key)) {
//                this.cache.put(key, value);
//            }
//
//            Pubsub.Default.pub(BsfEventEnum.PropertyCacheUpdateEvent.toString(), new HashMap(1) {
//                {
//                    this.put(key, value);
//                }
//            });
//        }
//    }
//
//    public void listenUpdateCache(String name, Action1<HashMap<String, Object>> action) {
//        Pubsub.Default.sub(BsfEventEnum.PropertyCacheUpdateEvent, new Sub(name, action));
//    }

    public void clear() {
        this.cache.clear();
    }
}
