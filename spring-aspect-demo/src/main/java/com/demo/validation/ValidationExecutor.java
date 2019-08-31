package com.demo.validation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import org.apache.poi.ss.formula.functions.T;

/**
 * @author zhanglong
 * @description: 描述
 * @date 2019-08-3112:33
 */
public class ValidationExecutor {

    private ValidationHandler validationHandler;

    public ValidationExecutor(ValidationHandler validationHandler){
        this.validationHandler = validationHandler;
    }

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public <T> ValidationListResult<T> validateList( List<T> dataList ) {
        ValidationListResult<T> result = new ValidationListResult();
        dataList.forEach(t -> {
            Set<ConstraintViolation<T>> set = validator.validate(t, Default.class);
            if (set != null && set.size() != 0) {
                Map<String, String> errorMsg = new HashMap<>();
                for (ConstraintViolation<T> cv : set) {
                    if(errorMsg.containsKey(cv.getPropertyPath().toString())) {
                        errorMsg.put(cv.getPropertyPath().toString(),
                            errorMsg.get(cv.getPropertyPath().toString())+";"+cv.getMessage());
                    }else {
                        errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());
                    }
                }
                if(errorMsg.isEmpty()) {
                    result.getSuccessData().add(t);
                }else {
                    result.getErrorData().put(t, errorMsg);
                }
            }
        });
        validationHandler.resultHandler(result);
        return result;
    }

    public <T> ValidationEntityResult<T> validateEntity( T data ) {
        ValidationEntityResult<T> validationEntityResult = new ValidationEntityResult();
        validationEntityResult.setData(data);
        Set<ConstraintViolation<T>> constraintViolationSet = validator.validate(data, Default.class);
        Optional.ofNullable(constraintViolationSet).ifPresent(constraintViolations -> constraintViolations.forEach(constraintViolation ->{
            if(validationEntityResult.getErrorMsgs().containsKey(constraintViolation.getPropertyPath().toString())) {
                validationEntityResult.getErrorMsgs().put(constraintViolation.getPropertyPath().toString(),
                    validationEntityResult.getErrorMsgs().get(constraintViolation.getPropertyPath().toString())+";"+constraintViolation.getMessage());
            }else {
                validationEntityResult.getErrorMsgs().put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
            }
        }));
        validationHandler.resultHandler(validationEntityResult);
        return validationEntityResult;
    }

}
