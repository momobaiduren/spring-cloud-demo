//package com.springcloud.datasource.aspect;
//
//import com.baomidou.mybatisplus.core.toolkit.StringUtils;
//import com.springcloud.datasource.annotation.ChoiceDB;
//import com.springcloud.datasource.enums.DataSourceEnum;
//import com.springcloud.datasource.holder.DataSourceContextHolder;
//import java.lang.reflect.Method;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.After;
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
//  @Pointcut("@annotation(com.springcloud.datasource.annotation.ChoiceDB)")
//  protected void ChoiceDBPointcut() {
//  }
//  @Before("ChoiceDBPointcut()")
//  public void ChoiceDBBefore(JoinPoint joinPoint){
//    DataSourceEnum dataSourceEnum = DataSourceEnum.read;
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
//      if (StringUtils.isNotEmpty(methodName) && method.getName().equals(methodName) && method.getAnnotation(ChoiceDB.class) != null) {
//        dataSourceEnum = method.getAnnotation(ChoiceDB.class).value();
//      }
//    }
//    DataSourceContextHolder.setDatasourceType(dataSourceEnum);
//  }
//
//  @After(value = "ChoiceDBPointcut()")
//  public void doAfter(){
//    DataSourceContextHolder.clearDatasourceType();
//  }
//}
