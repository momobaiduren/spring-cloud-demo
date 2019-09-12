package com.demo.excel;



/**
 * @author zhanglong
 * @description: 处理器接口
 * @date 2019-08-3120:52
 */
@FunctionalInterface
public interface EasyExcelHandler<M extends ReadModel> {

    EasyExcelHandler DEFAULTEASYEXCELHANDLER = new DefaultEasyExcelHandler();

    void handlerData(EasyExcelExecutorContext.DataHandler<M> dataHandler);

}
