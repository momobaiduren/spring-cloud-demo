package com.demo.proxy.impl;

import com.demo.proxy.LogManager;
import java.util.Date;

/**
 * @title: LogManagerImpl
 * @projectName spring-cloud-demo
 * @description: TODO
 * @author zhanglong
 * @date 2019-07-1909:18
 */


public class LogManagerImpl {

  public String loginfo(String createName, Date createTime) {
    System.out.println("zhanglong:"+new Date());
    return "zhanglong:"+new Date();
  }

  public String classMethodNotImplInterface(){
    return "该方法没有在接口中实现";
  }
}
