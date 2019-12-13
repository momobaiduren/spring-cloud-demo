package com.springcloud.wokeFlow;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author ZhangLong on 2019/11/24  10:38 上午
 * @version V1.0
 */
@Aspect
public class BusAspect {
    @Pointcut("@annotation(BusAnnocation)")
    public void checkWorkFlow(){}
    @After("checkWorkFlow()")
    public void excuteWorkFlow(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();//此处joinPoint的实现类是MethodInvocationProceedingJoinPoint
        MethodSignature methodSignature = (MethodSignature) signature;//获取参数名
        BusAnnocation annotation = methodSignature.getMethod().getAnnotation(BusAnnocation.class);
        String presentStation = annotation.presentStation();
        Class<BusWorkFlowStationContext> busWorkFlowStationContextClass = annotation.busWorkFlowStations();
    }
}
