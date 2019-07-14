package com.demo.around;

import com.demo.annotation.Compensable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhanglong
 * @title: DemoController
 * @projectName spring-cloud-demo
 * @description: TODO
 * @date 2019-07-1321:55
 */
@RestController
public class AroundDemo {
  @RequestMapping("/around")
  @Compensable(confirmMethod = "recordConfirm", cancelMethod = "recordCancel")
  public void sayHello() {
    System.out.println("Hello, this is @Around!------2");
  }

  public void recordConfirm() {
    System.out.println("zhanglong recordConfirm------1");

  }

  public void recordCancel() {
    System.out.println("zhanglong recordCancel-------3");

  }
}
