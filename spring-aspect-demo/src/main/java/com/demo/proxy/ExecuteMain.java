package com.demo.proxy;

import com.demo.proxy.impl.LogManagerImpl;
import com.demo.proxy.interceptor.CglibProxy;
import java.util.Date;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @title: ExecuteMain
 * @projectName spring-cloud-demo
 * @description: TODO
 * @author zhanglong
 * @date 2019-07-1911:17
 */

public class ExecuteMain {

  public static void main(String[] args) {
//    LogManager logManager = new JdkProxyHandler<LogManager>().newProxyInstance(new LogManagerImpl());
//    logManager.loginfo("zhanglong",new Date());

    CglibProxy cglibProxy = new CglibProxy();
    LogManagerImpl logManager = cglibProxy.getInstance(LogManagerImpl.class);
    logManager.loginfo("zhanglong", new Date());
  }
}
