//package com.springcloud.datasource.aspect;
//
//import java.lang.reflect.Method;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
///**
// * @title: ChoiceDBAspect
// * @projectName spring-cloud-demo
// * @description:
// * @author zhanglong
// * @date 2019-07-0721:51
// */
//@Component
//@Aspect
//@Order(-1)
//public class ChoiceDBAspect {
//
//  @Pointcut("@annotation(com.springcloud.datasource.annotation.ReadDS)")
//  protected void ChoiceDBPointcut() {
//  }
//  @Before("ChoiceDBPointcut()")
//  public void ChoiceDBBefore(JoinPoint joinPoint){
//    String className = joinPoint.getTarget().getClass().getName();
//    String methodName = joinPoint.getSignature().getName();
//    Class<?> targetClass = null;
//    try {
//      targetClass = Class.forName(className);
//    } catch (ClassNotFoundException e) {
//      e.printStackTrace();
//    }
//    Method[] methods = targetClass.getMethods();
//    for (Method method : methods) {
//      String menthodName = method.getName();
//      if(menthodName.startsWith("select")) {
//        //获取读的数据源
//      }else {
//        //获取写的数据源
//      }
//    }
//  }
//}
