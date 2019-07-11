package com.springcloud.factory;
/**
 * @title: BeanFactoryAdapter
 * @projectName spring-cloud-demo
 * @description: TODO
 * @author zhanglong
 * @date 2019-07-1122:47
 */

public class BeanFactoryAdapter {
  private static BeanFactory beanFactory;

  public static Object getBean(Class<?> aClass) {
    return beanFactory.getBean(aClass);
  }

  public static void setBeanFactory(BeanFactory beanFactory) {
    BeanFactoryAdapter.beanFactory = beanFactory;
  }
}
