package com.springcloud.util;

import com.springcloud.common.PropertyCache;

/**
 * @author ZhangLong on 2019/11/1  5:17 下午
 * @version V1.0
 */
public class PropertyUtils {

    public static String NULL = "<?NULL?>";

    public PropertyUtils() {
    }

//    public static void eachProperty(Action3<String, String, Object> call) {
//        Iterator var1 = System.getProperties().stringPropertyNames().iterator();
//
//        while(var1.hasNext()) {
//            String key = (String)var1.next();
//            call.invoke("properties", key, System.getProperty(key));
//        }
//
//        var1 = System.getenv().entrySet().iterator();
//
//        while(var1.hasNext()) {
//            Map.Entry kv = (Map.Entry)var1.next();
//            call.invoke("env", kv.getKey(), kv.getValue());
//        }
//
//    }

    private static <T> T getProperty(String key, T defaultvalue) {
        String value = System.getProperty(key);
        if (value == null) {
            value = System.getenv(key);
            if (ContextUtils.getApplicationContext() != null){
                value = ContextUtils.getApplicationContext().getEnvironment().getProperty(key);
            }
        }
        if (value != null){
            defaultvalue = (T) ConvertUtils.convert(value, defaultvalue.getClass());
        }
        return defaultvalue;
    }

    public static Object getProperty(String key) {
        String value = System.getProperty(key);
        if (value == null) {
            value = System.getenv(key);
        }

        if (value == null && ContextUtils.getApplicationContext() != null) {
            value = ContextUtils.getApplicationContext().getEnvironment().getProperty(key);
        }

        return value;
    }

    public static <T> T getEnvProperty(String key, T defaultvalue) {
        String value = System.getenv(key);
        if ( value != null) {
            defaultvalue = (T) ConvertUtils.convert(value, defaultvalue.getClass());
        }
        return defaultvalue;
    }

    public static <T> T getSystemProperty(String key, T defaultvalue) {
        String value = System.getProperty(key);
        if ( value != null) {
            defaultvalue = (T) ConvertUtils.convert(value, defaultvalue.getClass());
        }
        return defaultvalue;

    }
//
//    public static void setDefaultInitProperty(Class cls, String module, String key, String propertyValue) {
//        setDefaultInitProperty(cls, module, key, propertyValue, "");
//    }

//    public static void setDefaultInitProperty(Class cls, String module, String key, String propertyValue, String message) {
//        if (Strings.isEmpty((CharSequence)getPropertyCache(key, "")) && !Strings.isEmpty(propertyValue)) {
//            System.setProperty(key, propertyValue);
//            PropertyCache.Default.tryUpdateCache(key, propertyValue);
////            LogUtils.info(cls, module, "设置" + key + "=" + propertyValue + " " + message);
//        }
//
//    }

    public static <T> T getPropertyCache(String key, T defaultvalue) {
        return PropertyCache.Default.get(key, defaultvalue);
    }

}
