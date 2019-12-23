package com.demo.validation;

import java.util.function.Function;

/**
 * @author zhanglong
 * @description: 使用 javax.validation方式校验
 * @date 2019-08-31
 */
public class ValidationManager {
    /**
     * create by ZhangLong on 2019-08-31
     */
    public static ValidationExecutor validation( Function<String, ? extends Exception> dataExpFunction){
        return new ValidationExecutor(dataExpFunction);
    }

}
