//package com.springcloud.permission;//package com.yh.csx.settle.service.request;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Method;
//import java.util.Map;
//import java.util.Objects;
//import java.util.Set;
//
///**
// * @author zhanglong
// * @title: UserManagerAuthorityAspect
// * @projectName csx-b2b-settle
// * @description: 用户权限
// * @date 2019-08-1716:17
// */
//
//@Aspect
//@Component
//public class PermissionAuthorityAspect {
//
//    @Value("${permission.auth.enable}")
//    private boolean enableAuth = false;
//    /**
//     * @author  zhanglong on 2019-08-21   8:05 下午
//     * description 加载权限数据的切面
//     */
//    @Pointcut("@annotation(com.springcloud.service.permission.PermissionAuthority)")
//    public void authorityDataContext() {}
//
//    /**
//     * create by ZhangLong on 2019/10/22
//     * description  mapper执行查询参数处理
//     */
//    @Pointcut("execution(* com.baomidou.mybatisplus.core.mapper.BaseMapper.select*(..))")
//    public void mybatisMapperExcutor() {}
//
//    /**
//     * create by ZhangLong on 2019/10/26
//     * description
//     */
//    @Before("authorityDataContext()")
//    public void before( JoinPoint pjp ) {
//        if(enableAuth) {
//            Object[] args = pjp.getArgs();
//            MethodSignature signature = (MethodSignature) pjp.getSignature();
//            Method method = signature.getMethod();
//            if (Objects.nonNull(method)) {
//                com.springcloud.service.permission.PermissionAuthority permissionAuthority = method.getAnnotation(com.springcloud.service.permission.PermissionAuthority.class);
//                if(Objects.nonNull(permissionAuthority)) {
//                    PermissionAuthorityEnum[] authorityPropertyNames = permissionAuthority.authorityPropertyNames();
//                    if (authorityPropertyNames.length > 0) {
//                        for (int i = 0; i <authorityPropertyNames.length ; i++) {
//                            PermissionAuthorityContext.set(authorityPropertyNames[i]);
//                        }
//                    }
//
//                }
//            }
//        }
//    }
//    @Around("mybatisMapperExcutor()")
//    public void around(JoinPoint pjp){
//        Object[] args = pjp.getArgs();
//        for (int i = 0; i < args.length; i++) {
//            if (args[i] instanceof QueryWrapper){
//                Map<PermissionAuthorityEnum, Set<String>> currentPermissionAuthorities = PermissionAuthorityContext.getCurrentPermissionAuthorities();
//                int finalI = i;
//                currentPermissionAuthorities.forEach((permissionAuthorityEnum, permissions) -> {
////                    QueryWrapper wrapper = (QueryWrapper) args[finalI];
////                    wrapper.in(permissionAuthorityEnum);
//                    //
//                });
//            }
//        }
//    }
//
//}
