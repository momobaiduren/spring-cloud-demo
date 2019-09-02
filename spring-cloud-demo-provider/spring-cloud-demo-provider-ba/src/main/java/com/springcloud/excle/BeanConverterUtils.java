//package com.springcloud.excle;
//
//import com.springcloud.excle.EasyExcelExecutorContext.DataHandler;
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.Objects;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.poi.ss.formula.functions.T;
//import org.springframework.beans.BeanUtils;
//
//
///**
// * @author zhanglong
// * @description: 将对象转换
// * @date 2019-09-0120:25
// */
//@Slf4j
//public class BeanConverterUtils<R extends ExcelModel> {
//
//    public Map<Class<?>, Map<String, Object>> handler(R r)
//        throws IllegalAccessException {
//        Map<Class<?>, Map<String, Object>> beanFiledMap = new HashMap<>();
//        Field[] declaredFields = r.getClass().getDeclaredFields();
//        for (Field declaredField : declaredFields) {
//            declaredField.setAccessible(true);
//            if (Objects.nonNull(converterBeanClassAnno)) {
//                Class<?>[] clazzes = converterBeanClassAnno.clazz();
//                if (Objects.nonNull(clazzes) && clazzes.length > 0) {
//                    for (Class<?> clazz : clazzes) {
//                        if (beanFiledMap.containsKey(clazz)) {
//                            beanFiledMap.get(clazz)
//                                .put(declaredField.getName(), declaredField.get(r));
//                        } else {
//                            Map<String, Object> fieldNameValueMap = new HashMap<>();
//                            fieldNameValueMap.put(declaredField.getName(), declaredField.get(r));
//                            beanFiledMap.put(clazz,fieldNameValueMap);
//                        }
//                    }
//                }
//            }
//        }
//        return beanFiledMap;
//    }
//
//    public Map<Class<?>, Object> converter( R r ) throws IllegalAccessException {
//        Map<Class<?>, Object> clazzBeanMap = new HashMap<>();
//        Map<Class<?>, Map<String, Object>> handler = handler(r);
//        handler.entrySet().forEach(classMapEntry -> {
//             Class<?> clazz = classMapEntry.getKey();
//            try {
//                Object beanObj = clazz.newInstance();
//                BeanUtils.copyProperties(classMapEntry.getValue(), beanObj);
//                clazzBeanMap.put(clazz, beanObj);
//            } catch (InstantiationException e) {
//                log.error(e.getMessage());
//            } catch (IllegalAccessException e) {
//                log.error(e.getMessage());
//            }
//
//        });
//        return clazzBeanMap;
//    }
//
//    public Map<Class<?>, List> converterDataHandler( final DataHandler dataHandler
//        ) {
//        List<R> rList = dataHandler.get();
//        Map<Class<?>, List> clazzBeanMap = new HashMap<>();
//        rList.parallelStream().forEach(r -> {
//            try {
//                Map<Class<?>, Map<String, Object>> handler = handler(r);
//                for (Entry<Class<?>, Map<String, Object>> classMapEntry : handler.entrySet()) {
//                    Class<?> clazz = classMapEntry.getKey();
//                    Object beanObj = clazz.newInstance();
//                    BeanUtils.copyProperties(classMapEntry.getValue(), beanObj);
//                    if (clazzBeanMap.containsKey(clazz)) {
//                        clazzBeanMap.get(clazz).add(beanObj);
//                    } else {
//                        List<Object> beanObjs = new ArrayList<>();
//                        beanObjs.add(beanObj);
//                        clazzBeanMap.put(clazz, beanObjs);
//                    }
//                }
//            } catch (IllegalAccessException | InstantiationException e) {
//                dataHandler.errorMsgAdd(r,String.format("数据转换处理异常:%s", e.getCause()));
//                log.error(e.getMessage());
//            }
//        });
//
//        dataHandler.errorData().forEach(errorda ->{
//            if(rList.contains(errorda)) {
//                rList.remove(errorda);
//            }
//        });
//        return clazzBeanMap;
//    }
//
////    public <R> Map<Class<R>, List<R>> converterDataHandler(final DataHandler dataHandler ) {
////        List<R> rList = dataHandler.get();
////        Map<Class<?>, List<Object>> clazzBeanMap = new HashMap<>();
////        rList.parallelStream().forEach(r -> {
////            try {
////                Map<Class<?>, Map<String, Object>> handler = handler(r);
////                for (Entry<Class<?>, Map<String, Object>> classMapEntry : handler.entrySet()) {
////                    Class<?> clazz = classMapEntry.getKey();
////                    Object beanObj = clazz.newInstance();
////                    BeanUtils.copyProperties(classMapEntry.getValue(), beanObj);
////                    if (clazzBeanMap.containsKey(clazz)) {
////                        clazzBeanMap.get(clazz).add(beanObj);
////                    } else {
////                        List<Object> beanObjs = new ArrayList<>();
////                        beanObjs.add(beanObj);
////                        clazzBeanMap.put(clazz, beanObjs);
////                    }
////                }
////            } catch (IllegalAccessException | InstantiationException e) {
////                log.error(e.getMessage());
////            }
////        });
////
////        return clazzBeanMap;
////    }
//}
