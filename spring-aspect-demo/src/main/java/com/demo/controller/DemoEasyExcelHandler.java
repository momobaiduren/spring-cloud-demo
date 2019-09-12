package com.demo.controller;

import com.demo.excel.EasyExcelExecutorContext;
import com.demo.excel.EasyExcelHandler;
import org.springframework.stereotype.Component;

/**
 * @author zhanglong
 * @description: 描述
 * @date 2019-08-3122:23
 */
@Component
public class DemoEasyExcelHandler implements EasyExcelHandler {

    @Override
    public void handlerData( EasyExcelExecutorContext.DataHandler dataHandler ) {

        System.out.println(dataHandler.get());
        System.out.println(dataHandler.errorData());
    }

}
