package com.springcloud.mapper;

import java.lang.annotation.*;

/**
 * @author zhanglong
 * @version V1.0
 * @date 2019/9/24  9:07 上午
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Inherited
public @interface MapperProperty {
    String[] value() default {};
}