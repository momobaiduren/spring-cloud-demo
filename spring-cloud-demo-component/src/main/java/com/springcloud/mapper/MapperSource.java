package com.springcloud.mapper;

import java.lang.annotation.*;

/**
 * @author zhanglong
 * @version V1.0
 * @date 2019/9/24  9:06 上午
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface MapperSource {
    Class<?> value();
}