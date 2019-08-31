package com.demo.validation;

import java.util.Objects;

/**
 * @author zhanglong
 * @description: 描述
 * @date 2019-08-3114:20
 */
public class ValidationContext {

    private ValidationHandler validationHandler;


    public static ValidationContext init() {
        return new ValidationContext();
    }

    public ValidationExcutor validationHandler(ValidationHandler validationHandler){
        if(Objects.isNull(validationHandler)) {
            this.validationHandler = new DefaultValidationHandler();
        }else {
            this.validationHandler = validationHandler;
        }

        return new ValidationExcutor(validationHandler);
    }
}
