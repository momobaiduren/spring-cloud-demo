package com.demo.Jdk8Api;

import lombok.Data;

/**
 * @title: Apple
 * @projectName spring-cloud-demo
 * @description: TODO
 * @author zhanglong
 * @date 2019-07-3122:30
 */

@Data
public class Apple implements Comparable<Apple> {
  private Integer appleId;
  private Integer bigNum;
  private String appleName;

  public Apple() {
  }

  public Apple(Integer appleId, Integer bigNum, String appleName) {
    this.appleId = appleId;
    this.bigNum = bigNum;
    this.appleName = appleName;
  }

  @Override
  public int compareTo(Apple o) {
    return 0;
  }
}
