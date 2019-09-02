package com.springcloud.validation;

import java.util.Map;
import lombok.Data;

/**
 * @author zhanglong
 * @description: 描述
 * @date 2019-08-3115:50
 */
@Data
public class ValidationParamResult {
    boolean hasError(){
        return false;
    }

    private Map<Object, String> errorMsgs;
}
