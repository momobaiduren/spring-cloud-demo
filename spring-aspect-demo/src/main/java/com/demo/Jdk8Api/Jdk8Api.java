package com.demo.Jdk8Api;

import static java.util.stream.Collectors.groupingBy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.time.DateUtils;

/**
 * @author zhanglong
 * @title: Jdk8Api
 * @projectName spring-cloud-demo
 * @description: Jdk8Api
 * @date 2019-07-3113:01
 */

public class Jdk8Api {

  public static void main(String[] args) {
    //1.8新特性 时间
    //时间到毫秒
    LocalDateTime localDateTime = LocalDateTime.now();
    System.out.println(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE));
    System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    System.out.println(localDateTime);
    //时间到日
    LocalDate today = LocalDate.now();
    System.out.println(today.atStartOfDay(ZoneId.systemDefault()));
    System.out.println(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
    System.out.println(Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant()));
    System.out.println(today);
    //时间时分秒
    LocalTime localTime = LocalTime.now();
    System.out.println(localTime);
    Date date7 = DateUtils.addDays(new Date(), 7);
    System.out.println(date7);
    System.out.println(new Date());

    //1.8新特性 stream
    System.out.println("stream ----------------------");
    List<Apple> list = new ArrayList<>();
    list.add(new Apple(2, 3, "hong3"));
    list.add(new Apple(1, 1, "hong1"));
    list.add(new Apple(2, 2, "hong2"));
    list.add(new Apple(3, 3, "hong4"));
    list.add(new Apple(3, 4, "hong5"));
    Stream.of(list).filter(apples -> apples.get(1).getAppleId() > 1);
    System.out.println(Stream.of(list).filter(apples -> apples.get(1).getAppleId() > 1).collect(
        Collectors.toList()));
    System.out.println(list);
//    reversed降序排列 从大到小
    //默认生序排列 从小到大
    //map是映射到指定的业务数据 可以随意指定
    System.out.println("---");

    Map<Integer, List<Apple>> integerListMap = list.stream().collect(groupingBy(Apple::getAppleId));
    integerListMap.forEach((key, apples) -> {
      System.out.println("---apple---start--");
      System.out.println(key);
      apples.forEach(apple -> {
        System.out.println(apple);

      });
      System.out.println("---apple--end---");
    });
    System.out.println(list.stream().filter(apple -> apple.getAppleId() > 1).sorted(
        Comparator.comparingInt(Apple::getAppleId).thenComparing(Apple::getBigNum).reversed())
        .map(Apple::getAppleName).collect(
            Collectors.toList()));

    System.out.println("---");
    System.out.println(list.stream().filter(apple -> apple.getAppleId() > 1).sorted(
        Comparator.comparingInt(Apple::getAppleId).thenComparing(Apple::getBigNum).reversed())
        .map(Jdk8Api::D).limit(2).collect(
            Collectors.toList()));
    System.out.println("------");
    System.out.println(Stream.builder().add(new ArrayList<>()));
    Map<String, Apple> result = new LinkedHashMap<>();
    Map<String, Apple> map = new HashMap<>();
    map.put("3", new Apple(2, 2, "hong3"));
    map.put("2", new Apple(3, 3, "hong3"));
    map.put("1", new Apple(4, 3, "hong3"));
    map.put("4", new Apple(5, 4, "hong3"));
    map.put("5", new Apple(6, 5, "hong3"));
    map.put("6", new Apple(7, 6, "hong3"));
    map.entrySet().stream()
        .sorted(Map.Entry.<String, Apple>comparingByKey()
            .reversed()).forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
    System.out.println(map);
    System.out.println(result);

  }

  private static Apple D(Apple apple) {
    apple.setAppleName("张龙");
    return apple;
  }



}
