package com.demo.excel;


import java.util.List;
import java.util.Map;

/**
 * @author zhanglong
 * @description: 处理器接口
 * @date 2019-08-3120:52
 */
public interface EasyExcelHandler<T extends ExcelModel> {

    EasyExcelHandler DEFAULTEASYEXCELHANDLER = new DefaultEasyExcelHandler();

    void handlerData( List<T> data, Map<T, String> errorMsg,
        EasyExcelExecutorContext easyExcelExecutorContext );
}
