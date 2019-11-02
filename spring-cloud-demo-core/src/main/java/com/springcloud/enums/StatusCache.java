package com.springcloud.enums;

import com.springcloud.cache.SoftCache;

import java.util.Objects;

/**
 * @author ZhangLong on 2019/10/29  10:37 下午
 * @version V1.0
 */
public class StatusCache extends SoftCache<String, String> {

//    @Override
//    public void dealSoftCache() {
//    }

    public void waitCacheClear(String key) {
        boolean lock = true;
        while (lock){
            lock = Objects.nonNull(this.get(key));
        }
    }


}
