package com.demo.validation;

import javax.xml.bind.ValidationException;

/**
 * @author zhanglong
 * @description: 描述
 * @date 2019-08-3113:04
 */
public abstract class ValidationResult {

   public abstract void isErrorThrowExp() throws ValidationException;

}
