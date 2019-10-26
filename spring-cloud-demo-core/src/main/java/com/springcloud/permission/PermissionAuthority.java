package com.springcloud.service.permission;

import com.springcloud.permission.PermissionAuthorityEnum;

import java.lang.annotation.*;

/**
 * @author  zhanglong on 2019-08-17 12:26 下午
 * @version V1.0
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface PermissionAuthority {

    PermissionAuthorityEnum[] authorityPropertyNames() default {};

}
