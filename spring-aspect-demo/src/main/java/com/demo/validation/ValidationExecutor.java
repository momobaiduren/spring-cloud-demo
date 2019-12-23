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
import org.apache.commons.collections4.CollectionUtils;

/**
 * @author zhanglong
 * @description:
 * @date 2019-08-3112:33
 */
public class ValidationExecutor {

    private Function<String, ? extends Exception> dataExpFunction;

    ValidationExecutor( Function<String, ? extends Exception> dataExpFunction ) {
        this.dataExpFunction = dataExpFunction;
    }

    public <T> ValidationListResultConsumer<T> validateList( List<T> dataList ) throws Exception {
        return validateList(dataList, null);
    }

    public <T> ValidationListResultConsumer<T> validateList( List<T> dataList, Validator validator )
        throws Exception {
        if (Objects.isNull(validator)) {
            validator = Validation.buildDefaultValidatorFactory().getValidator();
        }
        ValidationListResult<T> result = new ValidationListResult<>();
        Validator finalValidator = validator;
        for (T t : dataList) {
            Set<ConstraintViolation<T>> set = finalValidator.validate(t, Default.class);
            if (CollectionUtils.isNotEmpty(set)) {
                Map<String, String> errorMsg = new HashMap<>(dataList.size());
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
        return new ValidationListResultConsumer<>(result);
    }


    public <Data> ValidationEntityResultConsumer<Data> validateEntity( Data data ) throws Exception {
        return validateEntity(data, null);
    }

    public <T> ValidationEntityResultConsumer<T> validateEntity( T data, Validator validator )
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
                if (validationEntityResult.getErrorMsg()
                    .containsKey(constraintViolation.getPropertyPath().toString())) {
                    validationEntityResult.getErrorMsg()
                        .put(constraintViolation.getPropertyPath().toString(),
                            validationEntityResult.getErrorMsg()
                                .get(constraintViolation.getPropertyPath().toString()) + ";"
                                + constraintViolation.getMessage());
                } else {
                    validationEntityResult.getErrorMsg()
                        .put(constraintViolation.getPropertyPath().toString(),
                            constraintViolation.getMessage());
                }
            }));
        if (null != dataExpFunction) {
            String errMsg = String.join(";", validationEntityResult.getErrorMsg().values());
            throw dataExpFunction.apply(errMsg);
        }
        return new ValidationEntityResultConsumer<>(validationEntityResult);
    }
    /**
     * description 如果是null的话 不额外处理校验结果，如果需要额外处理校验结果需要 {@link Consumer}
     */
    public static class ValidationEntityResultConsumer<T> {

        private ValidationEntityResult<T> validationEntityResult;

        ValidationEntityResultConsumer( ValidationEntityResult<T> validationEntityResult ) {
            this.validationEntityResult = validationEntityResult;
        }

        public void consumer( Consumer<ValidationEntityResult<T>> validateResultConsumer ) {
            if (Objects.nonNull(validateResultConsumer) && Objects
                .nonNull(validationEntityResult)) {
                validateResultConsumer.accept(validationEntityResult);
            }
        }
    }
    /**
     * description 如果是null的话 不额外处理校验结果，如果需要额外处理校验结果需要 {@link Consumer}
     */
    public static class ValidationListResultConsumer<T> {

        private ValidationListResult<T> validationListResult;

        ValidationListResultConsumer( ValidationListResult<T> validationListResult ) {
            this.validationListResult = validationListResult;
        }

        public void consumer( Consumer<ValidationListResult<T>> validateResultConsumer ) {
            if (Objects.nonNull(validateResultConsumer) && Objects.nonNull(validationListResult)) {
                validateResultConsumer.accept(validationListResult);
            }
        }
    }

}
