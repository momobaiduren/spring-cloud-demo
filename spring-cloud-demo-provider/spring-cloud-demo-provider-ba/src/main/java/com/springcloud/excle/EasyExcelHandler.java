package com.springcloud.excle;


import com.springcloud.excle.EasyExcelExecutorContext.DataHandler;

/**
 * @author zhanglong
 * @description: 处理器接口
 * @date 2019-08-3120:52
 */
@FunctionalInterface
public interface EasyExcelHandler<M extends ExcelModel> {

    EasyExcelHandler DEFAULTEASYEXCELHANDLER = new DefaultEasyExcelHandler();

    void handlerData( DataHandler<M> dataHandler);

}
