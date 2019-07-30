package com.demo.collection;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @title: SetStr
 * @projectName spring-cloud-demo
 * @description: Set不重复
 * @author zhanglong
 * @date 2019-07-2123:28
 */

public class SetStr {
  public static void setStr(){
    Set<String> setStr1 = new HashSet<>();
    String s1 = "sss";
    String s2 = "sss";
    String s3 = "aaa";
    String s4 = "bbb";
    setStr1.add(s1);
    setStr1.add(s2);
    setStr1.add(s3);
    setStr1.add(s4);
    System.out.println(s1.equals(s2));
    System.out.println(setStr1);
    System.out.println("-------------------------");
    Set<String> setStr2 = new HashSet<>();
    setStr2.add("sss");
    setStr2.add("sss");
    setStr2.add("aaa");
    setStr2.add("bbb");
    System.out.println(setStr2);
  }

  public static void setIntegerAndInt(){
    Set<Integer> setInteger = new HashSet<>();
    Integer i1 = 1;
    Integer i2 = 1;
    Integer i3 = 2;
    Integer i4 = 3;
    setInteger.add(i1);
    setInteger.add(i2);
    setInteger.add(i3);
    setInteger.add(i4);
    System.out.println(i1.equals(i2));
    System.out.println(setInteger);
    System.out.println("-------------------------");
    Set<Integer> setInt = new HashSet<>();
    setInt.add(1);
    setInt.add(1);
    setInt.add(2);
    setInt.add(3);
    System.out.println(setInt);
  }

  public static void taxAccount(BigDecimal tax, BigDecimal total, Map<BigDecimal, BigDecimal> totalMap){
    if(totalMap.containsKey(tax)) {
      totalMap.put(tax, totalMap.get(tax).add(total));
    }else {
      totalMap.put(tax, total);
    }
  }

//  public static void main(String[] args) {
//    Map<BigDecimal, BigDecimal> total = new HashMap<>();
//    System.out.println(total);
//    taxAccount(new BigDecimal(17),BigDecimal.valueOf(1000),total);
//    System.out.println(total);
//    taxAccount(new BigDecimal(17),BigDecimal.valueOf(1800),total);
//    System.out.println(total);
//  }
}
