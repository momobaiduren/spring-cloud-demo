package com.springcloud.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author  zhanglong on 2019-08-14  3:48 下午
 * @version V1.0
 */
@Slf4j
@Controller
@ControllerAdvice
public class SystemException extends Throwable {
    @ExceptionHandler(ServiceException.class)
    public String serviceException(ServiceException se) {
        RequestContextHolder.getRequestAttributes().setAttribute("exception", se, RequestAttributes.SCOPE_REQUEST);
        return "exception/service-exception";
    }
}
