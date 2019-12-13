package com.demo.validation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

/**
 * @author zhanglong
 * @description:
 * @date 2019-08-3112:33
 */
public class ValidationExecutor {

    private Consumer<ValidationResult> validateResultConsumer;

    private Function<String, ? extends Exception> dataExpFunction;

    ValidationExecutor( Consumer<ValidationResult> validateResultConsumer,
        Function<String, ? extends Exception> dataExpFunction ) {
        this.validateResultConsumer = validateResultConsumer;
        this.dataExpFunction = dataExpFunction;
    }

    public <T> ValidationListResult<T> validateList( List<T> dataList ) throws Exception {
        return validateList(dataList, null);
    }

    public <T> ValidationListResult<T> validateList( List<T> dataList, Validator validator )
        throws Exception {
        if (Objects.isNull(validator)) {
            validator = Validation.buildDefaultValidatorFactory().getValidator();
        }
        ValidationListResult<T> result = new ValidationListResult<>();
        Validator finalValidator = validator;
        for (T t : dataList) {
            Set<ConstraintViolation<T>> set = finalValidator.validate(t, Default.class);
            if (set != null && set.size() != 0) {
                Map<String, String> errorMsg = new HashMap<>();
                for (ConstraintViolation<T> cv : set) {
                    if (errorMsg.containsKey(cv.getPropertyPath().toString())) {
                        errorMsg.put(cv.getPropertyPath().toString(),
                            errorMsg.get(cv.getPropertyPath().toString()) + ";" + cv.getMessage());
                    } else {
                        errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());
                    }
                }
                if (errorMsg.isEmpty()) {
                    result.getSuccessData().add(t);
                } else {
                    if (null != dataExpFunction) {
                        String errMsg = String.join(";", errorMsg.values());
                        throw dataExpFunction.apply(errMsg);
                    }
                    result.getErrorData().put(t, errorMsg);
                }
            }
        }
        if (Objects.nonNull(validateResultConsumer)) {
            validateResultConsumer.accept(result);
        }
        return result;
    }

    public <T> ValidationEntityResult<T> validateEntity( T data ) throws Exception {
        return validateEntity(data, null);
    }

    public <T> ValidationEntityResult<T> validateEntity( T data, Validator validator )
        throws Exception {
        if (Objects.isNull(validator)) {
            validator = Validation.buildDefaultValidatorFactory().getValidator();
        }
        ValidationEntityResult<T> validationEntityResult = new ValidationEntityResult<>();
        validationEntityResult.setData(data);
        Set<ConstraintViolation<T>> constraintViolationSet = validator
            .validate(data, Default.class);
        Optional.ofNullable(constraintViolationSet)
            .ifPresent(constraintViolations -> constraintViolations.forEach(constraintViolation -> {
                if (validationEntityResult.getErrorMsgs()
                    .containsKey(constraintViolation.getPropertyPath().toString())) {
                    validationEntityResult.getErrorMsgs()
                        .put(constraintViolation.getPropertyPath().toString(),
                            validationEntityResult.getErrorMsgs()
                                .get(constraintViolation.getPropertyPath().toString()) + ";"
                                + constraintViolation.getMessage());
                } else {
                    validationEntityResult.getErrorMsgs()
                        .put(constraintViolation.getPropertyPath().toString(),
                            constraintViolation.getMessage());
                }
            }));
        if (null != dataExpFunction) {
            String errMsg = String.join(";", validationEntityResult.getErrorMsgs().values());
            throw dataExpFunction.apply(errMsg);
        }
        if (Objects.nonNull(validateResultConsumer)) {
            validateResultConsumer.accept(validationEntityResult);
        }
        return validationEntityResult;
    }

}
