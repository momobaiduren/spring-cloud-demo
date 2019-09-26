package com.demo.Jdk8Api;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author zhanglong
 * @version V1.0
 * @date 2019/7/20  11:32 下午
 */
@Slf4j
public class ConverterUtils {

    public static <R, T> R converter(Class<R> rClass, T t) {
        return converter(rClass, t, false, false);
    }

    public static <R, T> R converter(Class<R> rClass, T t, boolean isSkipException, boolean isIgnoreNullFiled) {
        return converter(rClass, t, isSkipException,isIgnoreNullFiled, null);
    }

    public static <R, T> R converter(Class<R> rClass, T t, boolean isSkipException, boolean isIgnoreNullFiled, Consumer<R> consumer) {
        if (Objects.isNull(t)) {
            return null;
        }
        R r = null;
        try {
            if (isIgnoreNullFiled){
                R r1 = JSON.parseObject(JSON.toJSONString(t), rClass);
                PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(rClass);
                for (int i = 0; i < propertyDescriptors.length; i++) {
                    Method readMethod = propertyDescriptors[i].getReadMethod();
                    if (Objects.nonNull(readMethod) && readMethod.getDeclaringClass() != Object.class){
                        Object o = readMethod.invoke(r1);
                        if (Objects.isNull(o)){
                            continue;
                        }else {
                            r = rClass.newInstance();
                            propertyDescriptors[i].getWriteMethod().invoke(r, o);
                        }
                    }
                }
            }else {
                r = JSON.parseObject(JSON.toJSONString(t), rClass);
            }
        } catch (Exception e) {
            if (isSkipException) {
                log.error(e.getMessage());
            } else {
//                throw new Exception("converter fail exception");
            }
        }
        //这个只是用来对 R 进行其他处理
        if (Objects.nonNull(consumer)) {
            consumer.accept(r);
        }
        return r;
    }

    public static <R, T> List<R> convertList(Class<R> rClass, List<T> tList, boolean isSkipException, boolean isIgnoreNullFiled) {
        Objects.requireNonNull(rClass, "result class is not null");
        Objects.requireNonNull(tList, "target list is not null");
        return tList.stream().map(t -> {
            return converter(rClass, t, isSkipException,isIgnoreNullFiled);
        }).collect(Collectors.toList());
    }

    public static <R, T> List<R> convertList(Class<R> rClass, List<T> tList, boolean isSkipException, boolean isIgnoreNullFiled, BiConsumer<R, T> biConsumer) {
        Objects.requireNonNull(rClass, "result class is not null");
        Objects.requireNonNull(tList, "target list is not null");
        return tList.stream().map(t -> {
            return converter(rClass, t, isSkipException, isIgnoreNullFiled, r -> {
                biConsumer.accept(r, t);
            });
        }).collect(Collectors.toList());
    }

    public static <R, T> List<R> convertList(Class<R> rClass, List<T> tList, boolean isSkipException, boolean isIgnoreNullFiled, Consumer<R> consumer) {
        Objects.requireNonNull(rClass, "result class is not null");
        Objects.requireNonNull(tList, "target list is not null");
        return tList.stream().map(t -> {
            return converter(rClass, t, isSkipException, isIgnoreNullFiled, consumer);
        }).collect(Collectors.toList());
    }

}
