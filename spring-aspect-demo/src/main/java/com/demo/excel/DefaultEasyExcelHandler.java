package com.demo.excel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zhanglong
 * @description: 默认导入实现处理器 （如果操作进行数据操作引入在@Handler实现上引入或配置springIOC）
 * @date 2019-08-31 22:07
 */
@Slf4j
@Service
public class DefaultEasyExcelHandler<M extends ReadModel> extends AbstractEasyExcelHandler<M> {
    /**
     * 默认强制实现{@link AbstractEasyExcelHandler#handlerData(EasyExcelExecutorContext.DataHandler)}
     * @Author zhanglong
     * @Version V1.0.0
     * @Date 2019-09-12
     */
    @Override
    public void handlerData( EasyExcelExecutorContext.DataHandler<M> dataHandler ) {
        throw new RuntimeException(
            String.format("未实现 {@link com.yh.csx.settle.easyExcel.AbstractEasyExcelHandler#handlerData(DataHandler)}"));
    }
}
