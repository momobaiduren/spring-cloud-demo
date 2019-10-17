package com.demo.design;

/**
 * @author ZhangLong on 2019/10/12  11:35 上午
 * @version V1.0
 * 静态工厂(常用工具类只用被初始化一次)
 */
public class StaticFactory {

    private StaticFactory() {
    }

    private static final StaticFactory staticFactory = new StaticFactory();

    public static StaticFactory getInstance(){
        return staticFactory;
    }

    public static void utilMethod(){
        System.out.println("工具类方法类不应被初始化");
    }

}
