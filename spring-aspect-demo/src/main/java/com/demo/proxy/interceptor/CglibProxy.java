package com.demo.proxy.interceptor;

import com.demo.proxy.impl.LogManagerImpl;
import java.lang.reflect.Method;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * @title: CglibProxy
 * @projectName spring-cloud-demo
 * @description: TODO
 * @author zhanglong
 * @date 2019-07-1915:59
 */

/**
 * cglib动态代理：是通过ASM字节码处理框架（内部是处理class字节码文件生成对应代理子类集成代理的class，通过反射获得实例构造初始化，执行指定的方法）
 */

public class CglibProxy implements MethodInterceptor {

  @Override
  public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy)
      throws Throwable {
    System.out.println("方法执行前");
    Object returnResult = methodProxy.invokeSuper(object, args);
    System.out.println("方法执行后");
    return returnResult;
  }

  public <T> T getInstance(Class<T> tClass){
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(LogManagerImpl.class);
    enhancer.setCallback(this);
    return (T) enhancer.create();
  }
}
