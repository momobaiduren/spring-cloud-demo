package com.demo.designpatterns.factory;

/**
 * @author zhanglong
 * @title: Dog
 * @projectName spring-cloud-demo
 * @description: TODO
 * @date 2019-08-0517:09
 */


public class Dog extends Animal implements RunHaving {

    @Override
    public String eat() {
        return "吃骨头";
    }

    @Override
    public String run() {
        return "草坪上奔跑";
    }
}
