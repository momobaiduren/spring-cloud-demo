package com.springcloud.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author zhanglong
 * @title: SystemException
 * @projectName spring-cloud-demo
 * @description: TODO
 * @date 2019-08-1422:47
 */
@Slf4j
@Controller
@ControllerAdvice
public class SystemException extends Throwable {
    @ExceptionHandler(ServiceException.class)
    public String serviceException(ServiceException se) {
        log.error("系统发生业务异常", se);
        RequestContextHolder.getRequestAttributes().setAttribute("exception", se, RequestAttributes.SCOPE_REQUEST);
        return "exception/service-exception";
    }
}
