package com.demo.validation;

import java.util.function.Consumer;

/**
 * @author zhanglong
 * @description: 描述
 * @date 2019-08-3114:57
 */
public class ValidationManager {
    /**
     * create by ZhangLong on 2019/10/17
     * @param consumer 如果是null的话 不额外处理校验结果，如果需要额外处理校验结果需要 {@Link Consumer}
     */
    public static ValidationExecutor validation(Consumer<ValidationResult> consumer){
        return new ValidationExecutor(consumer);
    }

}
