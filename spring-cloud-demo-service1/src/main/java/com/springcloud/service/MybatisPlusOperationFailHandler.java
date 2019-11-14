package com.springcloud.service;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author ZhangLong on 2019/11/13  7:14 下午
 * @version V1.0
 */

@Aspect
public class MybatisPlusOperationFailHandler {
    @AfterReturning(
            pointcut="mybatisMapperPointcut()",
            returning="retVal")
    public void mybatisPlusOperationFailHandler(boolean retVal) {
        if (!retVal){
            throw new RuntimeException("数据变更/插入/删除异常失败");
        }
    }


    @Pointcut("execution(boolean com.baomidou.mybatisplus.extension.service.IService.*(..))")
    public void mybatisMapperPointcut(){}
}
