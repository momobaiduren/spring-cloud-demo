package com.demo.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.constraints.NotNull;
import org.springframework.core.annotation.AliasFor;

/**
 * @author zhanglong
 * @description: 描述
 * @date 2019-09-0120:34
 */
@Inherited
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConverterEntityClazzAnno {
    @NotNull
    Class<?>[] clazz();

    @NotNull
    @AliasFor("clazz")
    Class<?>[] value();
 }
