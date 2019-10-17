//package com.demo.cache;
//
//import java.util.function.Function;
//
///**
// * @author ZhangLong on 2019/10/10  5:10 下午
// * @version V1.0
// */
//public class SafeDataCache implements DataCache{
//    @Override
//    public <T> void put(String key, T data) {
//        DATACACHE.put(key, data);
//    }
//
//    @Override
//    public <T> T get(String key) {
//        return null;
//    }
//
//    @Override
//    public <T, R> R computer(Function<T, R> function) {
//        return null;
//    }
//}
