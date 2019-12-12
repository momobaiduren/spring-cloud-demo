package com.demo.validation;

import java.util.function.Function;

/**
 * @author zhanglong
 * @description: 这个类可以是抽象类、也可以是接口（用于表达一个结果集类型的基类）
 * @date 2019-08-3113:04
 */
public abstract class ValidationResult {

   public abstract  <E extends Exception> void throwErrorExp(Function<String, E> function) throws Exception;

}
