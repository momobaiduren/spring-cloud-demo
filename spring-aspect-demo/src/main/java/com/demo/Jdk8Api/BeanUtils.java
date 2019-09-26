package com.demo.Jdk8Api;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author zhanglong
 * @version V1.0
 * @date 2019/9/20  1:34 下午
 */
@Slf4j
public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {
    private static Map<Class, Boolean> complexClasses = new ConcurrentHashMap();

    public BeanUtils() {
    }

    private static void setObjectValue(Object dest, String name, Object value, boolean isSkipException) {
        Assert.notNull(dest, "dest is not null");
        try {
            Assert.notNull(dest, "name is not null");
            PropertyDescriptor descriptor = PropertyUtils.getPropertyDescriptor(dest, name);
            if (descriptor == null) {
                return;
            }
            Method writeMethod = descriptor.getWriteMethod();
            if (writeMethod == null) {
                return;
            }
            writeMethod.invoke(dest, value);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            if (isSkipException) {
                log.error("name: {}; value: {} 值设置失败", name, value);
            } else {
                throw new IllegalArgumentException(String.format("name: %s; value: %s 值设置失败", name, value));
            }
        }
    }

    public static void setProperties(Object dest, Object orig, boolean ignoreNull, boolean isSkipException) {
        try {
            Assert.notNull(dest, "dest is not null");
            Assert.notNull(orig, "source is not null");
            if (orig instanceof Map) {
                Map<String, Object> map = (Map) orig;
                map.forEach((namex, valuex) -> {
                    if (!ignoreNull || valuex != null) {
                        setObjectValue(dest, namex, valuex, isSkipException);
                    }
                });
            } else {
                PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(orig.getClass());
                for (int i = 0; i < descriptors.length; ++i) {
                    PropertyDescriptor descriptor = descriptors[i];
                    Method method = descriptor.getReadMethod();
                    if (method != null && method.getDeclaringClass() != Object.class) {
                        String name = descriptor.getName();
                        Object value = method.invoke(orig);
                        if (!ignoreNull || value != null) {
                            setObjectValue(dest, name, value, isSkipException);
                        }

                    }
                }
            }
        } catch (Exception e) {
            if (isSkipException) {
                log.error("dest: {}; orig: {} 值设置失败", dest, orig);
            } else {
                throw new IllegalArgumentException(String.format("dest: %s; orig: %s 值设置失败", dest, orig));
            }
        }
    }

    public static <R, T> List<R> convertList(Class<R> rClass, List<T> tList, boolean isSkipException) {
        Assert.notNull(rClass, "result class is not null");
        Assert.notEmpty(tList, "target list is not empty");
        return tList.stream().map(t -> {
            return converter(rClass, t, isSkipException);
        }).collect(Collectors.toList());
    }


    public static <R, T> R converter(Class<R> rClass, T t) {
        return converter(rClass, t, false);
    }

    public static <R, T> R converter(Class<R> rClass, T t, boolean isSkipException) {
        return converter(rClass, t, isSkipException, null);
    }

    public static <R, T> R converter(Class<R> rClass, T t, boolean isSkipException, Consumer<R> consumer) {
        if (Objects.isNull(t)) {
            return null;
        }
        R r = null;
        try {
            r = JSON.parseObject(JSON.toJSONString(t), rClass);
        } catch (Exception e) {
            if (isSkipException) {
                log.error(e.getMessage());
            } else {
                throw new IllegalArgumentException("converter fail exception");
            }
        }
        if (Objects.nonNull(consumer)) {
            consumer.accept(r);
        }
        return r;
    }

    public static <R, T> List<R> convertList(Class<R> rClass, List<T> tList, boolean isSkipException, BiConsumer<R, T> biConsumer) {
        Assert.notNull(rClass, "target class is not null");
        Assert.notEmpty(tList, "result list is not empty");
        return tList.stream().map(t -> {
            return converter(rClass, t, isSkipException, r -> {
               biConsumer.accept(r, t);
           });
        }).collect(Collectors.toList());
    }

    public static <R, T> List<R> convertList(Class<R> rClass, List<T> tList, boolean isSkipException, Consumer<R> consumer) {
        Assert.notNull(rClass, "target class is not null");
        Assert.notEmpty(tList, "result list is not empty");
        return tList.stream().map(t -> {
            return converter(rClass, t, isSkipException, consumer);
        }).collect(Collectors.toList());
    }



    public static <R, T> R convert(Class<R> clazz, T obj, Consumer<R> consumer) throws Exception {
        if (obj == null) {
            return null;
        } else {
            Boolean complex = (Boolean) complexClasses.get(clazz);
            if (complex == null) {
                PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(clazz);
                if (descriptors != null && descriptors.length > 0) {
                    PropertyDescriptor[] var5 = descriptors;
                    int var6 = descriptors.length;

                    for (int var7 = 0; var7 < var6; ++var7) {
                        PropertyDescriptor descriptor = var5[var7];
                        Class<?> type = descriptor.getPropertyType();
                        if (Map.class.isAssignableFrom(type) || Collection.class.isAssignableFrom(type) || type.isArray()) {
                            complex = true;
                            break;
                        }
                    }
                }

                if (complex == null) {
                    complex = false;
                }

                complexClasses.put(clazz, complex);
            }

            Object dest;
            if (complex) {
                dest = JSON.parseObject(JSON.toJSONString(obj), clazz);
            } else {
                try {
                    dest = clazz.newInstance();
                } catch (Exception var10) {
                    throw new Exception("create instance failed: " + var10.getMessage());
                }

                setProperties(dest, obj, true, false);
            }

            if (consumer != null) {
                consumer.accept((R) dest);
            }

            return (R) dest;
        }
    }

    public static <R, T> R convert(Class<R> clazz, T obj) throws Exception {
        return convert(clazz, obj, (Consumer<R>) null);
    }

    public static void main(String[] args) throws Exception {
        // converter comsumer
        List<Apple> apples = new ArrayList<>();
        Apple apple = new Apple(BigDecimal.ONE);
        Apple.Aa aa = new Apple.Aa();
        aa.setSs("ss");
        apple.setAa(aa);
        AppleRes convert = converter(AppleRes.class, apple);
        System.out.println(convert);
        AppleRes convert1 = converter(AppleRes.class, apple, false);
        System.out.println(convert1);
        AppleRes convert2 = converter(AppleRes.class, apple, false, appleRes -> {
            appleRes.setAppleName("hot");
        });
        System.out.println(convert2);
        Apple apple1 = new Apple(BigDecimal.TEN);
        Apple.Aa aa1 = new Apple.Aa();
        aa1.setSs("ss1");
        apple1.setAa(aa1);
        apples.add(apple);
        apples.add(apple1);
        List<AppleRes> appleRes = convertList(AppleRes.class, apples, false);
        System.out.println(appleRes);
        System.out.println("----------------");
        List<AppleRes> appleRes2 = convertList(AppleRes.class, apples, false, (appleRes1, apple2) -> {
            appleRes1.setAppleName("res11111");
            System.out.println(appleRes1);
            System.out.println(apple2);
        });
        System.out.println(appleRes2);

    }

}

