package com.springcloud.excle;


import com.baomidou.mybatisplus.extension.service.IService;
import com.springcloud.excle.EasyExcelExecutorContext.DataHandler;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zhanglong
 * @description: 默认导入实现处理器 （如果操作进行数据操作引入在@Handler实现上引入或配置springIOC）
 * @date 2019-08-31 22:07
 */
@Slf4j
@Service
public class DefaultEasyExcelHandler extends AbstractEasyExcelHandler {


    @Override
    public void handlerData( DataHandler dataHandler, List iServices ) {
        super.handlerData(dataHandler, iServices);
    }
}
