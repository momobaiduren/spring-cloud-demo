package com.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: BusTestController
 * @projectName spring-cloud-demo
 * @description: TODO
 * @author zhanglong
 * @date 2019-07-2115:55
 */
@RefreshScope
@RestController
@RequestMapping("/bus")
public class BusTestController {
  @Value("${bus}")
  private String bus;
  @GetMapping("test")
  public String busTest(){
    return "我是bus测试"+bus;
  }
}
