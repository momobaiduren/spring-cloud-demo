package com.springcloud.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhanglong
 * @title: Compensable
 * @projectName spring-cloud-demo
 * @description: 事务补偿的注解
 * @date 2019-07-1020:08
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Compensable {

  String confirmMethod() default "";

  String cancelMethod() default "";
}
