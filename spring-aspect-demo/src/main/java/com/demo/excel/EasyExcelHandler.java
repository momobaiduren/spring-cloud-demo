package com.demo.excel;


import java.util.List;

/**
 * @author zhanglong
 * @description: 处理器接口
 * @date 2019-08-3120:52
 */
public interface EasyExcelHandler {

    EasyExcelHandler DEFAULTEASYEXCELHANDLER = new DefaultEasyExcelHandler();

    <M extends ReadModel> void readHandlerData(EasyExcelExecutorContext.DataHandler<M> dataHandler);

    <S, M extends ExcelModel> List<M> writeHandlerData(List<S> source, Class<M> mClass);
}
