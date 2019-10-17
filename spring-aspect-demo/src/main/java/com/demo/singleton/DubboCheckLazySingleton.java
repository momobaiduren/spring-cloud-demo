package com.demo.singleton;

/**
 * @author zhanglong
 * @version V1.0
 * @date 2019/9/30  8:07 下午
 */
public class DubboCheckLazySingleton implements Singleton {
    private static volatile DubboCheckLazySingleton syncLazySingleton;

    private DubboCheckLazySingleton() {
    }

    /**
     * @Author zhanglong
     * @Date 2019/9/30
     * 双重检验锁，保证了性能
     * 对象初始化分三步：1分配内存空间2：构造完成初始化3：使instance指向分配内存空间的引用
     */
    public static synchronized DubboCheckLazySingleton instance() {
        if (syncLazySingleton == null) {
            synchronized (DubboCheckLazySingleton.class) {
                if (syncLazySingleton == null) {
                    syncLazySingleton = new DubboCheckLazySingleton();
                }
            }
        }
        return syncLazySingleton;
    }
}
