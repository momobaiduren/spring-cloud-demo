package com.springcloud.util;

import javassist.ClassPool;
import javassist.LoaderClassPath;

/**
 * @author ZhangLong on 2019/11/2  2:28 下午
 * @version V1.0 java动态编程，动态生成字节码
 */
public final class ClassPoolUtils {

    private static volatile ClassPool instance;

    private ClassPoolUtils() {
    }

    public static ClassPool getInstance() {
        if (instance == null) {
            synchronized (ClassPoolUtils.class) {
                if (instance == null) {
                    instance = ClassPool.getDefault();
                    instance.appendClassPath(new LoaderClassPath(Thread.currentThread().getContextClassLoader()));
                }
            }
        }

        return instance;
    }

}
