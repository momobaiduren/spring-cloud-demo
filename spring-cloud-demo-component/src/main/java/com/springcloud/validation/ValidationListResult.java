package com.springcloud.validation;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author zhanglong
 * @description: 描述
 * @date 2019-08-3113:48
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ValidationListResult<T> extends ValidationResult{
    private List<T> successData = new ArrayList<>();

    private Map<T, Map<String, String>> errorData = new HashMap<>();

    @Override
    public void isErrorThrowExp() throws ValidationException {
        if(!errorData.isEmpty()) {
            for (Entry<T, Map<String, String>> entry : errorData.entrySet()) {
                T key = entry.getKey();
                Map<String, String> value = entry.getValue();
                throw new ValidationException(key.toString() + ":" + value.toString());
            }
        }
    }

    public List<T> get(){
        return successData;
    }
}



