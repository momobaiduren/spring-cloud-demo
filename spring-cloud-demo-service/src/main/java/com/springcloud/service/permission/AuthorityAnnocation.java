package com.springcloud.service.permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
public @interface AuthorityAnnocation {

//    ParamAndAuthorityEnum[] paramAndAuthority();

}
