package com.springcloud.excle;

import com.springcloud.excle.EasyExcelExecutorContext.DataHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zhanglong
 * @description: 默认导入实现处理器 （如果操作进行数据操作引入在@Handler实现上引入或配置springIOC）
 * @date 2019-08-31 22:07
 */
@Slf4j
@Service
public class DefaultEasyExcelHandler<M extends ExcelModel> extends AbstractEasyExcelHandler<M> {
    @Override
    public void handlerData( DataHandler<M> dataHandler) {
        throw new RuntimeException(
            String.format("未实现bind @AbstractEasyExcelHandler接口重写@handlerData()方法"));
    }


}
