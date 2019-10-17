package com.demo.singleton;

/**
 * @author zhanglong
 * @version V1.0
 * @date 2019/9/30  4:34 下午
 *
 * 饿汉式：优点简单
 * 缺点：在没有用到 {@link HungrySingleton#instance()} 方法同样会做初始化操作
 *
 */
public class HungrySingleton implements Singleton{
    private static final  HungrySingleton INSTANCE = new HungrySingleton();
    private HungrySingleton(){
        int i = Singleton.i;
        i++;
        System.out.println(i);
    }

    public static HungrySingleton instance(){
        return INSTANCE;
    }
}
