package com.demo.excel;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanglong
 * @description: 处理器抽象实现（实体类的增强转换）
 * @date 2019-08-3122:07
 */
@Slf4j
public abstract class AbstractEasyExcelHandler implements
    EasyExcelHandler {

    /**
     * 默认强制实现{@link #readHandlerData(EasyExcelExecutorContext.DataHandler)}
     * @Author zhanglong
     * @Version V1.0.0
     * @Date 2019-09-12
     */
    @Override
    public <M extends ReadModel> void readHandlerData( EasyExcelExecutorContext.DataHandler<M> dataHandler ) {
        throw new RuntimeException(
                String.format("未实现 {@link com.yh.csx.settle.easyExcel.AbstractEasyExcelHandler#handlerData(DataHandler)}"));
    }

    @Override
    public abstract  <S, M extends ExcelModel> List<M> writeHandlerData(List<S> source, Class<M> mClass);

    public <S, M extends ExcelModel> List<M> converterList(List<S> source, Class<M> mClass) {
        List<M> mList = new ArrayList<>();
        source.forEach(s -> {
            try {
                M m = mClass.newInstance();
                BeanUtils.copyProperties(s,m);
                mList.add(m);
            } catch (InstantiationException e) {
                log.error(e.getMessage());
            } catch (IllegalAccessException e) {
                log.error(e.getMessage());
            }
        });
        return mList;
    }

}
