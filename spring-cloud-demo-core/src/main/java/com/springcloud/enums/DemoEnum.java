package com.springcloud.enums;

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
    private static StatusCache statusCache = new StatusCache();
    private int state;
    private int order;

    public static void main(String[] args) {
        System.out.println("--------------------");
        statusCache.cache("zhanglong","zhanglong", 5);
        System.out.println(statusCache.get("zhanglong"));
        statusCache.waitCacheClear("zhanglong");

    }
    public <T> void positive(T t, String serviceId, Function<T, Integer> stateFunction, BiConsumer<T, Integer> stateConsumer) {
        statusCache.cache(serviceId, serviceId, 10);
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
