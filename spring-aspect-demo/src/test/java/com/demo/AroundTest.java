package com.demo;

import com.demo.around.AroundDemo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @title: AroundTest
 * @projectName spring-cloud-demo
 * @description: TODO
 * @author zhanglong
 * @date 2019-07-1322:12
 */

@SpringBootTest
public class AroundTest {

  @Autowired
  private AroundDemo aroundDemo;

  @Test
  public void testAroud(){
    aroundDemo.sayHello();
  }
}
