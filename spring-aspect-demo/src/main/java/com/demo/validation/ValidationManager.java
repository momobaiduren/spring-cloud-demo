package com.demo.validation;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author zhanglong
 * @description: 使用 javax.validation方式校验
 * @date 2019-08-31
 */
public class ValidationManager {
    /**
     * create by ZhangLong on 2019-08-31
     * @param validateResultConsumer 如果是null的话 不额外处理校验结果，如果需要额外处理校验结果需要 {@Link Consumer}
     */
    public static ValidationExecutor validation(Consumer<ValidationResult> validateResultConsumer, Function<String, ? extends Exception> dataExpFunction){
        return new ValidationExecutor(validateResultConsumer, dataExpFunction);
    }

}
