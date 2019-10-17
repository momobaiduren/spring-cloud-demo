package com.demo.validation;

import javax.xml.bind.ValidationException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhanglong
 * @description: 描述
 * @date 2019-08-3114:44
 */
public class ValidationEntityResult<T> extends ValidationResult{
    public boolean hasError(){
        return !errorMsgs.isEmpty();
    }
    private Map<String, String> errorMsgs = new HashMap<>();

    private T data;

    @Override
    public void isErrorThrowExp() throws ValidationException {
        if(this.hasError()) {
            throw new ValidationException(errorMsgs.toString());
        }
    }
    public String errorMsgs(){
        StringBuilder errorMsg = new StringBuilder();
        for (String filedName : errorMsgs.keySet()) {
            errorMsg.append(filedName).append(errorMsgs.get(filedName)).append(";");
        }
        return errorMsg.toString();
    }

    public T get(){
        return data;
    }

    Map<String, String> getErrorMsgs() {
        return errorMsgs;
    }

    public void setData( T data ) {
        this.data = data;
    }
}
