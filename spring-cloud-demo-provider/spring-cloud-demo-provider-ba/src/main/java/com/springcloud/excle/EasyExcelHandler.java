package com.springcloud.excle;


import com.baomidou.mybatisplus.extension.service.IService;
import com.springcloud.excle.EasyExcelExecutorContext.DataHandler;
import java.util.List;

/**
 * @author zhanglong
 * @description: 处理器接口
 * @date 2019-08-3120:52
 */
@FunctionalInterface
public interface EasyExcelHandler<M extends ExcelModel> {



    EasyExcelHandler DEFAULTEASYEXCELHANDLER = new DefaultEasyExcelHandler();

    void handlerData( DataHandler<M> dataHandler,
        List<IService<?>> iServices );
}
