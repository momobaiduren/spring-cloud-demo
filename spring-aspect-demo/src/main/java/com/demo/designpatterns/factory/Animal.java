package com.demo.designpatterns.factory;

/**
 * @author zhanglong
 * @title: Animal
 * @projectName spring-cloud-demo
 * @description: TODO
 * @date 2019-08-0517:08
 */

public abstract class Animal {

    private FlyHaving flyHaving;

    private RunHaving runHaving;

    public abstract String eat();

}
