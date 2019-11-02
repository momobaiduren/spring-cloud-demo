package com.springcloud.util;

import org.springframework.boot.convert.ApplicationConversionService;

import java.util.Objects;

/**
 * @author ZhangLong on 2019/11/1  5:24 下午
 * @version V1.0
 */
public class ConvertUtils {

    private ConvertUtils() {
    }

    public static <T> T convert(Object value, Class<T> type) {
        if (Objects.nonNull(value)){
            return ApplicationConversionService.getSharedInstance().convert(value, type);
        }
        return null;
    }

    public static <T> T tryConvert(Object value, Class<T> type) {
        try {
            return convert(value, type);
        } catch (Exception var3) {
            return null;
        }
    }

//    public static <T> T deepClone(T obj) {
//        try {
//            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
//            Throwable var2 = null;
//
//            Object var8;
//            try {
//                ObjectOutputStream out = new ObjectOutputStream(byteOut);
//                Throwable var4 = null;
//
//                try {
//                    out.writeObject(obj);
//                    ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
//                    Throwable var6 = null;
//
//                    try {
//                        ObjectInputStream in = new ObjectInputStream(byteIn);
//                        var8 = in.readObject();
//                    } catch (Throwable var55) {
//                        var6 = var55;
//                        throw var55;
//                    } finally {
//                        if (byteIn != null) {
//                            if (var6 != null) {
//                                try {
//                                    byteIn.close();
//                                } catch (Throwable var54) {
//                                    var6.addSuppressed(var54);
//                                }
//                            } else {
//                                byteIn.close();
//                            }
//                        }
//
//                    }
//                } catch (Throwable var57) {
//                    var4 = var57;
//                    throw var57;
//                } finally {
//                    if (out != null) {
//                        if (var4 != null) {
//                            try {
//                                out.close();
//                            } catch (Throwable var53) {
//                                var4.addSuppressed(var53);
//                            }
//                        } else {
//                            out.close();
//                        }
//                    }
//
//                }
//            } catch (Throwable var59) {
//                var2 = var59;
//                throw var59;
//            } finally {
//                if (var2 != null) {
//                    try {
//                        byteOut.close();
//                    } catch (Throwable var52) {
//                        var2.addSuppressed(var52);
//                    }
//                } else {
//                    byteOut.close();
//                }
//
//            }
//
//            return (T) var8;
//        } catch (Exception var61) {
//            throw new RuntimeException(var61);
//        }
//    }

}
