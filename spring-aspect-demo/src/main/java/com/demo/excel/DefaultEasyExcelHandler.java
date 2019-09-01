package com.demo.excel;

import com.demo.excel.EasyExcelExecutorContext.DataHandler;

/**
 * @author zhanglong
 * @description: 默认导入实现处理器 （如果操作进行数据操作引入在@Handler实现上引入或配置springIOC）
 * @date 2019-08-3122:07
 */
public class DefaultEasyExcelHandler<T extends ExcelModel> implements EasyExcelHandler<T>{
    /**
     *
     * @describ  在这里直接做数据处理
     * @author zhanglong
     * @date 2019-09-01  12:44
     *
     * @param dataHandler
     */
    @Override
    public void handlerData( DataHandler dataHandler ) {
        throw new RuntimeException("导入未实现{@EasyExcelHandler}接口");
    }


}
