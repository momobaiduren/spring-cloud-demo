package com.demo.singleton;

/**
 * @author zhanglong
 * @version V1.0
 * @date 2019/9/30  4:44 下午
 */
public class LazySingleton implements Singleton {
    private static LazySingleton lazySingleton;
    private LazySingleton(){
        int i = Singleton.i;
        i++;
        System.out.println(i);
    }

    public static LazySingleton instance(){
        if (lazySingleton == null) {
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }
}
