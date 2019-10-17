package com.demo.singleton;

/**
 * @author zhanglong
 * @version V1.0
 * @date 2019/9/30  8:07 下午
 */
public class SyncLazySingleton implements Singleton{
    private static SyncLazySingleton syncLazySingleton;
    private SyncLazySingleton(){}

    /**
     * @Author zhanglong
     * @Date 2019/9/30
     * synchronized关键字 保证线程安全，但是性能不高
     */

    public static synchronized SyncLazySingleton instance(){
        if (syncLazySingleton == null) {
            syncLazySingleton = new SyncLazySingleton();
        }
        return syncLazySingleton;
    }
}
