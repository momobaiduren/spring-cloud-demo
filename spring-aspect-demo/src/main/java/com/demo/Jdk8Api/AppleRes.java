package com.demo.Jdk8Api;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhanglong
 * @version V1.0
 * @date 2019/9/20  11:13 下午
 */
@Data
public class AppleRes implements Comparable<AppleRes>{
    private Integer appleId;
    private Integer bigNum;
    private String appleName;
    private BigDecimal price;
    private Aa aa;

    @Data
    public static class Aa{
        private String ss;
    }
    public AppleRes(){}

    public AppleRes( BigDecimal price ) {
        this.price = price;
    }

    public AppleRes( Integer appleId, Integer bigNum, String appleName ) {
        this.appleId = appleId;
        this.bigNum = bigNum;
        this.appleName = appleName;
    }

    @Override
    public int compareTo( AppleRes o ) {
        return 0;
    }

    public static void main(String[] args) {
        if (true == true){
            System.out.println("parma");
        }
    }
}
