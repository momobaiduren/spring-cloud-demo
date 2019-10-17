package com.demo.excel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @author zhanglong
 * @description: 描述
 * @date 2019-09-0513:49
 */
@Slf4j
public class EasyExcel<T> {

    public static <M extends ReadModel> void read( InputStream inputStream, Class<M> clazz, ExcelEventListener excelEventListener ) {
        EasyExcelFactory.readBySax(inputStream, new Sheet(1, 1, clazz), excelEventListener);
    }

    public static <M extends ExcelModel> void write( OutputStream outputStream, Class<M> clazz, String sheetName, List<M> data) {
        try (OutputStream out = outputStream) {
            ExcelWriter writer = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX, true);
            Sheet sheet1 = new Sheet(1, 0, clazz, sheetName, null);
            sheet1.setAutoWidth(true);
            writer.write(data, sheet1);
            writer.finish();
            out.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }

    }
}
