package com.demo.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.xml.bind.ValidationException;
import lombok.Data;

/**
 * @author zhanglong
 * @description: 描述
 * @date 2019-08-3113:48
 */
@Data
public class ValidationListResult<T> extends ValidationResult{
    private List<T> successData = new ArrayList<>();

    private Map<T, Map<String, String>> errorData = new HashMap<>();

    @Override
    public void isErrorThrowExp() throws ValidationException {
        if(!errorData.isEmpty()) {
            for (Entry<T, Map<String, String>> tMapEntry : errorData.entrySet()) {
                throw new ValidationException(tMapEntry.toString()+":"+ errorData.get(tMapEntry).toString());
            }

        }
    }
}
