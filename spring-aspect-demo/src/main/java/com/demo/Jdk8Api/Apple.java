package com.demo.Jdk8Api;

import java.math.BigDecimal;
import java.util.Objects;
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

    public Apple() {
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


    public static void main( String[] args ) {
        String aa = "aa";
        Object bb = aa;
        if (bb.getClass().isAssignableFrom(aa.getClass())) {
            System.out.println("sss");
        }
    }
}
