package com.demo.singleton;

/**
 * @author zhanglong
 * @version V1.0
 * @date 2019/9/30  4:44 下午
 */
public class InnerLazySingleton implements Singleton {

    private static final class InnerSingleton{
        private static final InnerLazySingleton innerLazySingleton = new InnerLazySingleton();
    }

    private InnerLazySingleton(){}

    public static InnerLazySingleton instance(){
        return InnerSingleton.innerLazySingleton;
    }
}
