package com.demo.validation;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zhanglong
 * @description: 校验单个对象
 * @date 2019-08-3114:44
 */
public class ValidationEntityResult<T> extends ValidationResult {

    private Map<String, String> errorMsgs = new HashMap<>();
    private T data;

    public boolean hasError() {
        return !errorMsgs.isEmpty();
    }

    @Override
    public <E extends Exception> void throwErrorExp(Function<String, E> function) throws Exception {
        if (this.hasError()) {
            String errmsg = errorMsgs.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.joining(";"));
            throw function.apply(errmsg);
        }
    }

    public String errorMsgs() {
        StringBuilder errorMsg = new StringBuilder();
        errorMsgs.forEach((key, value) -> {
            errorMsg.append(value).append(";");
        });
        return errorMsg.toString();
    }

    public T get() {
        return data;
    }

    Map<String, String> getErrorMsgs() {
        return errorMsgs;
    }

    public void setData(T data) {
        this.data = data;
    }
}
