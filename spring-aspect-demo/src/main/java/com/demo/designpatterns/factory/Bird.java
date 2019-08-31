package com.demo.designpatterns.factory;

/**
 * @author zhanglong
 * @title: Bird
 * @projectName spring-cloud-demo
 * @description: TODO
 * @date 2019-08-0517:10
 */


public class Bird extends Animal implements FlyHaving {

    @Override
    public String eat() {
        return "吃虫子";
    }

    @Override
    public String fly() {
        return "在天空飞翔";
    }
}
