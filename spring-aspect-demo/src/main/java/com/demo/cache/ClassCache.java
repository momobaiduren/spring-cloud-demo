package com.demo.cache;

/**
 * 线程非安全的
 * @author ZhangLong on 2019/10/10  3:14 下午
 * @version V1.0
 */
public interface ClassCache extends Cache{

    void put(String klassName, Class<?> klass);

    Class<?> get(String klassName);


}
