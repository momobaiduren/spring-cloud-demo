package com.demo.controller;

import com.demo.excel.ExcleData;
import org.springframework.stereotype.Component;

/**
 * @author zhanglong
 * @description: 描述
 * @date 2019-08-3122:23
 */
@Component
public class DemoEasyExcelHandler implements EasyExcelHandler {
    @Override
    public void handlerData(ExcleData dataHandler) {
        System.out.println(dataHandler.get());
        System.out.println("---------------------------");
        System.out.println(dataHandler.errorData());
    }
}
