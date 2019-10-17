package com.demo.singleton;

/**
 * @author zhanglong
 * @version V1.0
 * @date 2019/9/30  8:24 下午
 */
public class EnumSingleton implements Singleton {

    private EnumSingleton(){}

    enum EnumInstace{
        INSTANCE;
        private EnumSingleton enumSingleton;

        EnumInstace(){
            enumSingleton = new EnumSingleton();
        }
        public EnumSingleton instance(){
            return enumSingleton;
        }
    }
    /**
     * @Author zhanglong
     * @Date 2019/9/30
     * 枚举实现单例
     */

    public static EnumSingleton instance(){
        return EnumInstace.INSTANCE.instance();
    }
}
