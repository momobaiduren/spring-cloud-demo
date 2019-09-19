package com.demo.excel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhanglong
 * @description: 默认导入实现处理器 （如果操作进行数据操作引入在@Handler实现上引入或配置springIOC）
 * @date 2019-08-31 22:07
 */
@Slf4j
@Service
public class DefaultEasyExcelHandler extends AbstractEasyExcelHandler {

    @Override
    public <S, M extends ExcelModel> List<M> writeHandlerData(List<S> source, Class<M> mClass) {
        List<M> mList = converterList(source, mClass);
        return mList;
    }
}
