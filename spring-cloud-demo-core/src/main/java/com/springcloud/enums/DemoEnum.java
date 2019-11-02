package com.springcloud.enums;

import com.springcloud.cache.Cache;

import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author ZhangLong on 2019/10/29  10:05 下午
 * @version V1.0
 */
public enum DemoEnum {
    Enum1(1, 1),
    Enum2(2, 2),
    Enum3(3, 3),
    Enum4(4, 4),
    Enum5(5, 5),
    ;

    DemoEnum(int state, int order) {
        this.state = state;
        this.order = order;
    }
    private static Cache<String, String> statusCache = new StatusCache();
    private int state;
    private int order;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("--------------------");
        System.out.println(statusCache.get("zhanglong"));
        statusCache.cache("zhanglong","zhanglong", 3L);
        System.out.println(statusCache.get("zhanglong"));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(statusCache.get("zhanglong"));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(statusCache.get("zhanglong"));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(statusCache.get("zhanglong"));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(statusCache.get("zhanglong"));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(statusCache.get("zhanglong"));

        statusCache.cache("zhanglong","zhanglong", 2L);
        System.out.println(statusCache.get("zhanglong"));

    }
    public <T> void positive(T t, String serviceId, Function<T, Integer> stateFunction, BiConsumer<T, Integer> stateConsumer) {
        statusCache.cache(serviceId, serviceId, 10L);
        Integer stateInt = stateFunction.apply(t);
        DemoEnum demoEnum = get(stateInt);
        if (this.order >= demoEnum.order) {
            stateConsumer.accept(t, this.state);
        } else {
            throw new RuntimeException("状态不可逆");
        }
    }
    public <T> void reverse(T t, Function<T, Integer> stateFunction, BiConsumer<T, Integer> stateConsumer) {
        Integer stateInt = stateFunction.apply(t);
        DemoEnum demoEnum = get(stateInt);
        if (this.order <= demoEnum.order) {
            stateConsumer.accept(t, this.state);
        } else {
            throw new RuntimeException("逆向操作失败");
        }
    }


    public DemoEnum get(int state) {
        DemoEnum[] values = DemoEnum.values();
        for (DemoEnum value : values) {
            if (value.state == state){
                return value;
            }
        }
        throw new RuntimeException("状态不存在");
    }

}
