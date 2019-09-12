package com.demo.Jdk8Api;

public class Demo {
    public static void main( String[] args ) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Apple.Aa aa = new Apple.Aa();
        System.out.println(aa);
        Object o = Class.forName("com.demo.Jdk8Api.Apple$Aa").newInstance();
        System.out.println(o);
    }
}
