package com.demo.controller;

import com.demo.excel.EasyExcelExecutorContext;
import com.demo.excel.EasyExcelHandler;
import com.demo.excel.ExcelModel;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhanglong
 * @description: 描述
 * @date 2019-08-3122:23
 */
@Component
public class DemoEasyExcelHandler implements EasyExcelHandler {

    @Override
    public void readHandlerData( EasyExcelExecutorContext.DataHandler dataHandler ) {

        System.out.println(dataHandler.get());
        System.out.println(dataHandler.errorData());
    }

    @Override
    public <S, M extends ExcelModel> List<M> writeHandlerData(List<S> source, Class<M> mClass) {
        return null;
    }
}
