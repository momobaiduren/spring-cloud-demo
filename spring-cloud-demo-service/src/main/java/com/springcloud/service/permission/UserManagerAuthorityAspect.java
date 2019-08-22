package com.springcloud.service.permission;//package com.yh.csx.settle.service.request;
//
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Objects;
//import java.util.Set;
//import java.util.stream.Collectors;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.Signature;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RestController;
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
//@RestController
//public class UserManagerAuthorityAspect {
//
//    private static final String SEGMENTER = ",";
//
//    @Autowired
//    private AuthorityHandler authorityHandler;
//    @Value("${userManager.auth.enable}")
//    private boolean enableAuth = false;
//    /**
//     * @describ web的权限控制
//     * @author zhanglong
//     * @date 2019-08-21  18:08
//     */
//    @Pointcut("@annotation(com.yh.csx.settle.service.request.AuthorityAnnocation)")
//    public void pointcut() {
//
//    }
//
//    @Before("pointcut()")
//    public void before( JoinPoint pjp )
//        throws NoSuchMethodException, IllegalAccessException {
//        if(enableAuth) {
//            Object[] args = pjp.getArgs();
//            MethodSignature signature = (MethodSignature) pjp.getSignature();
//            Method method = signature.getMethod();
//            if (Objects.nonNull(method)) {
//                AuthorityAnnocation annotation = method
//                    .getAnnotation(AuthorityAnnocation.class);
//                if(Objects.nonNull(annotation)) {
//                    if (Objects.nonNull(args)) {
//                        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
//                        for (int i = 0; i < args.length; i++) {
//                            if (args[i] instanceof String) {
//                                Object arg = args[i];
//                                Annotation[] parameterAnnotation = parameterAnnotations[i];
//                                for (Annotation paramAuth : parameterAnnotation) {
//                                    arg = handlerStrParamAuth(arg, paramAuth);
//                                }
//                            } else {
//                                //TODO 后期做递归
//                                Field[] declaredFields = args[i].getClass().getDeclaredFields();
//                                for (Field declaredField : declaredFields) {
//                                    declaredField.setAccessible(true);
//                                    if (declaredField.getType().isAssignableFrom(String.class)) {
//                                        ParamAuth paramAuth = declaredField.getAnnotation(ParamAuth.class);
//                                        if (Objects.nonNull(paramAuth)) {
//                                            List<String> authCodes = authorityHandler
//                                                .fetchAuthorityCodeList(
//                                                    paramAuth.authorityModelName());
//                                            Object authResultCodes = declaredField.get(args[i]);
//                                            if (null == authCodes || authCodes.isEmpty()) {
//                                                declaredField.set(args[i], null);
//                                            } else {
//                                                if (paramAuth.isMustfull()) {
//                                                    //必填
//                                                    if (Objects.nonNull(authResultCodes)) {
//                                                        declaredField.set(args[i],removeDuplication(declaredField.get(args[i]),authCodes));
//                                                    } else {
//                                                        declaredField.set(args[i], null);
//                                                    }
//                                                } else {
//                                                    //非必填
//                                                    if (Objects.nonNull(authResultCodes)) {
//                                                        declaredField.set(args[i],
//                                                            removeDuplication(authResultCodes, authCodes));
//                                                    } else {
//                                                        declaredField
//                                                            .set(args[i], String.join(SEGMENTER, authCodes));
//                                                    }
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
//
//                            }
//                        }
//
//
//                    }
//                }
//            }
//        }
//    }
//
//    private Object handlerStrParamAuth( Object arg, Annotation paramAuth ) {
//        if (paramAuth instanceof ParamAuth) {
//            List<String> authCodes = authorityHandler.fetchAuthorityCodeList(
//                ((ParamAuth) paramAuth).authorityModelName());
//            if (null == authCodes || authCodes.isEmpty()) {
//                arg = null;
//            } else {
//                if (((ParamAuth) paramAuth).isMustfull()) {
//                    //必填
//                    if (Objects.nonNull(arg)) {
//                        arg = removeDuplication(arg, authCodes);
//                    } else {
//                        arg = null;
//                    }
//
//
//                } else {
//                    //非必填
//                    if (Objects.nonNull(arg)) {
//                        arg = removeDuplication(arg, authCodes);
//                    } else {
//                        arg = String.join(SEGMENTER, authCodes);
//                    }
//                }
//            }
//        }
//        return arg;
//    }
//
//    private Object removeDuplication( Object arg, List<String> authCodes ) {
//        if (arg.toString().contains(SEGMENTER)) {
//            String[] argCodes = arg.toString().split(SEGMENTER);
//            Set<String> authResultCodes = Arrays.stream(argCodes)
//                .filter(argCode -> authCodes.contains(argCode))
//                .collect(Collectors.toSet());
//            arg = String.join(SEGMENTER, authResultCodes);
//        } else {
//            if (!authCodes.contains(arg)) {
//                arg = null;
//            }
//        }
//        return arg;
//    }
//
////    private void handlerUserManager( UserManager userManager,
////        UserManagerAuthorityEnum1 userManagerAuthorityEnum1 ) {
////        List<String> authorityCodeList;
////        if (ModelEnum.companys == userManagerAuthorityEnum1.modelEnum()) {
////            authorityCodeList = userManager.getUser()
////                .fetchProperties(userManagerAuthorityEnum1.modelEnum().model());
////        } else if (ModelEnum.purchaseGroups.equals(userManagerAuthorityEnum1.modelEnum())) {
////            authorityCodeList = userManager.getPurchaseGroupList();
////        } else if (ModelEnum.purchaseOrgs.equals(userManagerAuthorityEnum1.modelEnum())) {
////            authorityCodeList = userManager.getPurchaseOrgList();
////        }
////    }
//}
