package com.demo.excel;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;


/**
 * @author zhanglong
 * @description: 将对象转换
 * @date 2019-09-0120:25
 */
@Slf4j
public class BeanConverterUtils<R> {

    public Map<Class<?>, Map<String, Object>> handler( R r)
        throws IllegalAccessException {
        Map<Class<?>, Map<String, Object>> beanFiledMap = new HashMap<>();
        Field[] declaredFields = r.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            ConverterEntityClazzAnno converterBeanClassAnno = declaredField
                .getAnnotation(ConverterEntityClazzAnno.class);
            if (Objects.nonNull(converterBeanClassAnno)) {
                Class<?>[] clazzes = converterBeanClassAnno.clazz();
                if (Objects.nonNull(clazzes) && clazzes.length > 0) {
                    for (Class<?> clazz : clazzes) {
                        if (beanFiledMap.containsKey(clazz)) {
                            beanFiledMap.get(clazz)
                                .put(declaredField.getName(), declaredField.get(r));
                        } else {
                            Map<String, Object> fieldNameValueMap = new HashMap<>();
                            fieldNameValueMap.put(declaredField.getName(), declaredField.get(r));
                            beanFiledMap.put(clazz,fieldNameValueMap);
                        }
                    }
                }
            }
        }
        return beanFiledMap;
    }

    public Map<Class<?>, Object> converter(R r) throws IllegalAccessException {
        Map<Class<?>, Object> clazzBeanMap = new HashMap<>();
        Map<Class<?>, Map<String, Object>> handler = handler(r);
        handler.entrySet().forEach(classMapEntry -> {
             Class<?> clazz = classMapEntry.getKey();
            try {
                Object beanObj = clazz.newInstance();
                BeanUtils.copyProperties(classMapEntry.getValue(), beanObj);
                clazzBeanMap.put(clazz, beanObj);
            } catch (InstantiationException e) {
                log.error(e.getMessage());
            } catch (IllegalAccessException e) {
                log.error(e.getMessage());
            }

        });
        return clazzBeanMap;
    }

}
