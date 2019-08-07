package com.demo.designpatterns.factory;/**
 * @title: Animal
 * @projectName spring-cloud-demo
 * @description: TODO
 * @author zhanglong
 * @date 2019-08-0517:08
 */

public abstract class Animal {

  private FlyHaving flyHaving;

  private RunHaving runHaving;

  public abstract String eat();

}
