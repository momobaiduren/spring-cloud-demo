package com.demo.Jdk8Api;

import java.math.BigDecimal;
import lombok.Data;

/**
 * @author zhanglong
 * @title: Apple
 * @projectName spring-cloud-demo
 * @description: TODO
 * @date 2019-07-3122:30
 */

@Data
public class Apple implements Comparable<Apple> {

    private Integer appleId;
    private Integer bigNum;
    private String appleName;
    private BigDecimal price;
    private Aa aa;


    public Apple(){}

    @Data
    public static class Aa{
        private String ss;
    }


    public Apple( BigDecimal price ) {
        this.price = price;
    }

    public Apple( Integer appleId, Integer bigNum, String appleName ) {
        this.appleId = appleId;
        this.bigNum = bigNum;
        this.appleName = appleName;
    }

    @Override
    public int compareTo( Apple o ) {
        return 0;
    }



}
