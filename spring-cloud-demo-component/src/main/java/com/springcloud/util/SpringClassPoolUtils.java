package com.springcloud.util;

import javassist.ClassPool;
import javassist.LoaderClassPath;

/**
 * @author ZhangLong on 2019/11/2  2:28 下午
 * @version V1.0
 */
public final class SpringClassPoolUtils {

    private static volatile ClassPool instance;

    private SpringClassPoolUtils() {
    }

    public static ClassPool getInstance() {
        if (instance == null) {
            synchronized (SpringClassPoolUtils.class) {
                if (instance == null) {
                    instance = ClassPool.getDefault();
                    instance.appendClassPath(new LoaderClassPath(Thread.currentThread().getContextClassLoader()));
                }
            }
        }

        return instance;
    }

}
