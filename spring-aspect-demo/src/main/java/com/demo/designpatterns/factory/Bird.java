package com.demo.designpatterns.factory;/**
 * @title: Bird
 * @projectName spring-cloud-demo
 * @description: TODO
 * @author zhanglong
 * @date 2019-08-0517:10
 */


public class Bird extends Animal implements FlyHaving{

  @Override
  public String eat() {
    return "吃虫子";
  }

  @Override
  public String fly() {
    return "在天空飞翔";
  }
}
