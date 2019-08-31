package com.demo.validation;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.ValidationException;
import lombok.Data;

/**
 * @author zhanglong
 * @description: 描述
 * @date 2019-08-3114:44
 */
@Data
public class ValidationEntityResult<T> extends ValidationResult{
    boolean hasError(){
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

    public T get(){
        return data;
    }
}
