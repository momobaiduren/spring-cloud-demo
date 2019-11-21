package com.demo.Jdk8Api;

import static java.util.stream.Collectors.joining;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

/**
 * created by zhanglong and since  2019/11/18  6:03 下午
 *
 * @description: 描述
 */
public class CollecteJoining {

    public static void main( String[] args ) {
        final List<Apple> aa = new ArrayList<>();
        for (int i = 0; i < 10 ; i++) {
            aa.add(new Apple(BigDecimal.valueOf(i)));
        }

        final List<Apple> bb = new ArrayList<>();
        for (int i = 10; i < 20 ; i++) {
            bb.add(new Apple(BigDecimal.valueOf(i)));
        }
        aa.stream().filter(apple -> StringUtils.isBlank(apple.getAppleName())).map(apple -> {
//            bb.stream().map(Apple::getPrice).collect(joining());
            return apple.getPrice();
        }).collect(Collectors.toList());
    }
}
