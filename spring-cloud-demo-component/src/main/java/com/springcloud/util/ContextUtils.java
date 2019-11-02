package com.springcloud.util;

import org.springframework.boot.web.context.ConfigurableWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

/**
 * @author ZhangLong on 2019/11/1  4:38 下午
 * @version V1.0
 */
public class ContextUtils {

    private static Class MainClass;
    private static ConfigurableApplicationContext applicationContext;

    public ContextUtils() {
    }

    public static void setApplicationContext(ConfigurableApplicationContext applicationContext) {
        ContextUtils.applicationContext = applicationContext;
    }

    public static ConfigurableApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBean(Class<T> type, boolean required) {
        if (type != null && applicationContext != null) {
            if (required) {
                return applicationContext.getBean(type);
            }

            if (applicationContext.getBeansOfType(type).size() > 0) {
                return applicationContext.getBean(type);
            }
        }

        return null;
    }

    public static Object getBean(String type, boolean required) {
        if (type != null && applicationContext != null) {
            if (required) {
                return applicationContext.getBean(type);
            }

            if (applicationContext.containsBean(type)) {
                return applicationContext.getBean(type);
            }
        }

        return null;
    }

    public static ConfigurableWebServerApplicationContext getConfigurableWebServerApplicationContext() {
        ApplicationContext context = getApplicationContext();
        return context != null && context instanceof ConfigurableWebServerApplicationContext ? (ConfigurableWebServerApplicationContext)context : null;
    }

    public static boolean isWeb() {
        return getConfigurableWebServerApplicationContext() != null;
    }

    public static String getBeanDefinitionText() {
        String[] beans = getApplicationContext().getBeanDefinitionNames();
        Arrays.sort(beans);
        StringBuilder sb = new StringBuilder();
        String[] var2 = beans;
        int var3 = beans.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String bean = var2[var4];
            sb.append(bean + " -> " + getApplicationContext().getBean(bean).getClass());
        }

        return sb.toString();
    }

    public static Class getMainClass() {
        return MainClass;
    }
}
