package com.springcloud.service.permission;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.constraints.NotBlank;
import org.springframework.core.annotation.AliasFor;

/**
 * @author zhanglong
 * @description: 描述
 * @date 2019-08-2120:16
 */

@Target({ElementType.PARAMETER,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamAuth {
    @NotBlank
    ModelEnum authorityModelName();

    boolean isMustfull() default true;

    @AliasFor("isMustfull")
    boolean value() default false;
}
