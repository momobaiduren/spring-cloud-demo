package com.springcloud.service.permission;

import java.lang.annotation.*;

/**
 * @author zhanglong
 * @title: Authority
 * @projectName csx-b2b-settle
 * @description: 请求实体权限
 * @date 2019-08-1711:30
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface PermissionAuthority {

    PermissionAuthorityEnum[] authorityPropertyNames() default {};

}
