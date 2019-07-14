package com.demo;

import static org.junit.Assert.*;

import com.demo.around.AroundDemo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zhanglong
 * @title: AroundAspectTest
 * @projectName spring-cloud-demo
 * @description: TODO
 * @date 2019-07-1322:22
 */
@SpringBootTest
public class AroundAspectTest {
  @Autowired
  private AroundDemo aroundDemo;

  @Test
  public void testAroud(){
    aroundDemo.sayHello();
  }
}