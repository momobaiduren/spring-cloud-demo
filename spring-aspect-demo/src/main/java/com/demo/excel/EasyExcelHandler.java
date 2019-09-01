package com.demo.excel;


import com.demo.excel.EasyExcelExecutorContext.DataHandler;

/**
 * @author zhanglong
 * @description: 处理器接口
 * @date 2019-08-3120:52
 */
@FunctionalInterface
public interface EasyExcelHandler {

    EasyExcelHandler DEFAULTEASYEXCELHANDLER = new DefaultEasyExcelHandler();

    void handlerData( DataHandler dataHandler );
}
