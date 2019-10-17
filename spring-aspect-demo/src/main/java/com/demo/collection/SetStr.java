package com.demo.collection;

import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhanglong
 * @title: SetStr
 * @projectName spring-cloud-demo
 * @description: Set不重复
 * @date 2019-07-2123:28
 */

public class SetStr {

    public static void setStr() {
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

    public static void setIntegerAndInt() {
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

    @Data
    public static class StatementAccount {

        Double num;

        StatementAccount( Double num ) {
            this.num = num;
        }
    }

    public static void main( String[] args ) {
        List<StatementAccount> statementAccounts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            statementAccounts.add(new StatementAccount(100d));
        }
        DoubleSummaryStatistics doubleSummaryStatistics = statementAccounts.stream()
            .collect(Collectors.summarizingDouble(StatementAccount::getNum));
        System.out.println(doubleSummaryStatistics);
//
//    Set<String> statementNos = new HashSet<>();
//    System.out.println(statementNos);

//    if (new BigDecimal(-100).compareTo(BigDecimal.ZERO) < 0) {
//      System.out.println("小于零");
//    }else {
//
//      System.out.println("大于零");
//    }
    }

//  public static void taxAccount(BigDecimal tax, BigDecimal total, Map<BigDecimal, BigDecimal> totalMap){
//    if(totalMap.containsKey(tax)) {
//      totalMap.put(tax, totalMap.get(tax).add(total));
//    }else {
//      totalMap.put(tax, total);
//    }
//  }

//  public static void main(String[] args) {
//    Map<BigDecimal, BigDecimal> total = new HashMap<>();
//    System.out.println(total);
//    taxAccount(new BigDecimal(17),BigDecimal.valueOf(1000),total);
//    System.out.println(total);
//    taxAccount(new BigDecimal(17),BigDecimal.valueOf(1800),total);
//    System.out.println(total);
//  }
}
