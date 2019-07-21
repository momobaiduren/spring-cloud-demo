package com.demo.around.aspect;

import com.demo.around.annotation.Compensable;
import java.lang.reflect.Method;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @title: AroundAspect
 * @projectName spring-cloud-demo
 * @description: TODO
 * @author zhanglong
 * @date 2019-07-1321:56
 */
@Aspect
@Component
public class AroundAspect {
  //声明切点
  @Pointcut("@annotation(com.demo.around.annotation.Compensable)")
  public void pointcut() {
  }
  //定义环绕通知
  @Around("pointcut()")
  public void around(ProceedingJoinPoint pjp){
    int count = 0;
    try {
      String className = pjp.getTarget().getClass().getName();
      Class<?> targetClass = null;
      try {
        targetClass = Class.forName(className);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
      Method[] methods = targetClass.getMethods();

      for (Method method : methods) {
        if(method.getAnnotation(Compensable.class) != null) {
          String confirmMethodName = method.getAnnotation(Compensable.class).confirmMethod();
          String cancelMethodName = method.getAnnotation(Compensable.class).cancelMethod();
          Method confirmMethod = targetClass.getMethod(confirmMethodName);
          confirmMethod.invoke( targetClass.newInstance());
          pjp.proceed();
          Method cancelMethod = targetClass.getMethod(cancelMethodName);
          cancelMethod.invoke( targetClass.newInstance());
        }

      }
    } catch (Throwable throwable) { // 相当于@AfterThrowing
      throwable.printStackTrace();
    }
  }
}
