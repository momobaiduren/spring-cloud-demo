package com.demo.validation;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhanglong
 * @description: 校验单个对象
 * @date 2019-08-3114:44
 */
public class ValidationEntityResult<T> extends ValidationResult {

    private Map<String, String> errorMsg = new HashMap<>();
    private T data;

    public boolean hasError() {
        return !errorMsg.isEmpty();
    }

    public String errorMsg() {
        return String.join(";", errorMsg.values());
    }

    public T get() {
        return data;
    }

    Map<String, String> getErrorMsg() {
        return errorMsg;
    }

    public void setData(T data) {
        this.data = data;
    }
}
