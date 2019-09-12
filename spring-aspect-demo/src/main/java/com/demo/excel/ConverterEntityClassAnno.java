package com.demo.excel;

import org.springframework.core.annotation.AliasFor;

import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

/**
 * @author zhanglong
 * @description: 描述
 * @date 2019-09-0120:34
 */
@Inherited
@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConverterEntityClassAnno {
    @NotNull
    Class<?>[] clazz();

    @NotNull
    @AliasFor("clazz")
    Class<?>[] value();
 }
