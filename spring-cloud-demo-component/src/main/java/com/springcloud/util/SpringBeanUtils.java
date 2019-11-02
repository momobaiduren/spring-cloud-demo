package com.springcloud.util;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import javax.servlet.Filter;

/**
 * @author ZhangLong on 2019/11/1  4:37 下午
 * @version V1.0
 */
public final class SpringBeanUtils {

    private SpringBeanUtils(){}

    private static final String FILTER_REGISTRATION_NAME = "filterRegistrationBean";

    public static void filterRegistrationBean(Filter filter, String... urlPatterns){
        ConfigurableApplicationContext applicationContext = ContextUtils.getApplicationContext();
        FilterRegistrationBean filterRegistrationBean = applicationContext.getBean(FILTER_REGISTRATION_NAME, FilterRegistrationBean.class);
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.addUrlPatterns(urlPatterns);
    }

    public static void registerBean(String name, Class clazz, Object... args) {
        ConfigurableApplicationContext applicationContext = ContextUtils.getApplicationContext();
        checkRegisterBean(applicationContext, name, clazz);
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
        Object[] var5 = args;
        int var6 = args.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            Object arg = var5[var7];
            beanDefinitionBuilder.addConstructorArgValue(arg);
        }

        BeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
        BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry)applicationContext.getBeanFactory();
        beanFactory.registerBeanDefinition(name, beanDefinition);
    }

    public static void registerBean(String name, Class clazz, BeanDefinitionBuilder beanDefinitionBuilder) {
        ConfigurableApplicationContext applicationContext = ContextUtils.getApplicationContext();
        checkRegisterBean(applicationContext, name, clazz);
        BeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
        BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry)applicationContext.getBeanFactory();
        beanFactory.registerBeanDefinition(name, beanDefinition);
    }

    public static void unRegisterBean(String name) {
        ConfigurableApplicationContext applicationContext = ContextUtils.getApplicationContext();
        BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry)applicationContext.getBeanFactory();
        beanFactory.removeBeanDefinition(name);
    }

    private static void checkRegisterBean(ApplicationContext applicationContext, String name, Class clazz) {
        if (applicationContext.containsBean(name)) {
            Object bean = applicationContext.getBean(name);
            if (!bean.getClass().isAssignableFrom(clazz)) {
                throw new RuntimeException("BeanName 重复注册" + name);
            }
        }
    }
}
