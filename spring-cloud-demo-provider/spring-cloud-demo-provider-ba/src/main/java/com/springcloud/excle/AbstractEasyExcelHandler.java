package com.springcloud.excle;


import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springcloud.excle.EasyExcelExecutorContext.DataHandler;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * @author zhanglong
 * @description: 处理器抽象实现（实体类的增强转换）
 * @date 2019-08-3122:07
 */
@Slf4j
public abstract class AbstractEasyExcelHandler<M extends ExcelModel> implements
    EasyExcelHandler<M> {

    @Override
    public void handlerData( DataHandler<M> dataHandler, List<IService<?>> iServices ) {
        throw new RuntimeException(
            String.format("未实现bind @AbstractEasyExcelHandler接口重写@handlerData()方法"));
    }

    public Map<Class<?>, Map<String, Object>> handler(M m)
        throws IllegalAccessException {
        Map<Class<?>, Map<String, Object>> beanFiledMap = new HashMap<>();
        Field[] declaredFields = m.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            ConverterEntityClassAnno converterEntityClassAnno = declaredField
                .getAnnotation(ConverterEntityClassAnno.class);
            if (Objects.nonNull(converterEntityClassAnno)) {
                Class<?>[] clazzes = converterEntityClassAnno.clazz();
                if (Objects.nonNull(clazzes) && clazzes.length > 0) {
                    for (Class<?> clazz : clazzes) {
                        if (beanFiledMap.containsKey(clazz)) {
                            beanFiledMap.get(clazz)
                                .put(declaredField.getName(), declaredField.get(m));
                        } else {
                            Map<String, Object> fieldNameValueMap = new HashMap<>();
                            fieldNameValueMap.put(declaredField.getName(), declaredField.get(m));
                            beanFiledMap.put(clazz, fieldNameValueMap);
                        }
                    }
                }
            }
        }
        return beanFiledMap;
    }

    public Map<Class<?>, Object> converter( M m ) throws IllegalAccessException {
        Map<Class<?>, Object> clazzBeanMap = new HashMap<>();
        Map<Class<?>, Map<String, Object>> handler = handler(m);
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

    public Map<Class<?>, List> defaultConverterDataHandler( final DataHandler dataHandler ) {
        List<M> mList = dataHandler.get();
        Map<Class<?>, List> map = new HashMap<>();

        if (Objects.nonNull(mList) && !mList.isEmpty()) {
            ConverterEntityClassAnno annotation = mList.get(0).getClass()
                .getAnnotation(ConverterEntityClassAnno.class);
            List list = new ArrayList();
            Class<?>[] clazz = annotation.clazz();
            mList.stream().forEach(m -> {
                try {
                    Object o = clazz[0].newInstance();
                    BeanUtils.copyProperties(m, o);
                    list.add(o);
                } catch (InstantiationException | IllegalAccessException e) {
                    dataHandler.errorMsgAdd(m, String.format("数据转换处理异常:%s", e.getCause()));
                    log.error(e.getMessage());
                }

            });
        }
        dataHandler.errorData().forEach(errorda -> {
            if (mList.contains(errorda)) {
                mList.remove(errorda);
            }
        });

        return map;
    }

    /**
     * @describ TODO 未进行分组还有重复数据
     * @author zhanglong
     * @date 2019-09-02  09:55
     */
    public Map<Class<?>, List> batchConverterDataHandler( final DataHandler dataHandler
    ) {
        List<M> mList = dataHandler.get();
        Map<Class<?>, List> clazzBeanMap = new HashMap<>();
        mList.parallelStream().forEach(m -> {
            try {
                Map<Class<?>, Map<String, Object>> handler = handler(m);
                for (Entry<Class<?>, Map<String, Object>> classMapEntry : handler.entrySet()) {
                    Class<?> clazz = classMapEntry.getKey();
                    Object beanObj = clazz.newInstance();
                    BeanUtils.copyProperties(classMapEntry.getValue(), beanObj);
                    if (clazzBeanMap.containsKey(clazz)) {
                        clazzBeanMap.get(clazz).add(beanObj);
                    } else {
                        List<Object> beanObjs = new ArrayList<>();
                        beanObjs.add(beanObj);
                        clazzBeanMap.put(clazz, beanObjs);
                    }
                }
            } catch (IllegalAccessException | InstantiationException e) {
                dataHandler.errorMsgAdd(m, String.format("数据转换处理异常:%s", e.getCause()));
                log.error(e.getMessage());
            }
        });

        dataHandler.errorData().forEach(errorda -> {
            if (mList.contains(errorda)) {
                mList.remove(errorda);
            }
        });
        return clazzBeanMap;
    }

}
