package com.demo.classLoader;/**
 * @title: ClassLoaderTest
 * @projectName spring-cloud-demo
 * @description: TODO
 * @author zhanglong
 * @date 2019-08-0523:16
 */

public class ClassLoaderTest {

  public static void main(String[] args) {
//    ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
    System.out.println(ClassLoader.getSystemResource("/"));

    ;
  }
}
