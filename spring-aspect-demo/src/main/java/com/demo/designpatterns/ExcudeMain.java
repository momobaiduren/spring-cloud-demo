package com.demo.designpatterns;

import com.demo.designpatterns.factory.Animal;
import com.demo.designpatterns.factory.Bird;
import com.demo.designpatterns.factory.Dog;

/**
 * @title: ExcudeMain
 * @projectName spring-cloud-demo
 * @description: TODO
 * @author zhanglong
 * @date 2019-08-0517:14
 */

public class ExcudeMain {

  public static void main(String[] args) {
    Animal dog = new Dog();
    Animal bird = new Bird();
  }
}
