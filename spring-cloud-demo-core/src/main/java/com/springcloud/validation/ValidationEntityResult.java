package com.springcloud.validation;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.ValidationException;

/**
 * @author zhanglong
 * @description: 描述
 * @date 2019-08-3114:44
 */
public class ValidationEntityResult<T> extends ValidationResult{
    public boolean hasError(){
        if(errorMsgs.isEmpty()) {
            return false;
        }
        return true;
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
        StringBuffer errorMsg = new StringBuffer();
        for (String filedName : errorMsgs.keySet()) {
            errorMsg.append(filedName).append(errorMsgs.get(filedName)).append(";");
        }
        return errorMsg.toString();
    }

    public T get(){
        return data;
    }

    public Map<String, String> getErrorMsgs() {
        return errorMsgs;
    }

    public void setData( T data ) {
        this.data = data;
    }
}
