package com.demo.controller.controllerAdvice;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhanglong
 * @description: 描述
 * @date 2019-09-0111:45
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ControllerException.class)
    @ResponseBody
    String handleException(){
        return "未知异常！";
    }
}
