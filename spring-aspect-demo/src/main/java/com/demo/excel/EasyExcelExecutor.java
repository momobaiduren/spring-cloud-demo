package com.demo.excel;

import com.alibaba.excel.EasyExcel;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhanglong
 * @description: 执行器
 * @date 2019-08-3120:46
 */
@Slf4j
public final class EasyExcelExecutor {

    private EasyExcelExecutorContext easyExcelExecutorContext;

    private EasyExcelExecutor(EasyExcelExecutorContext easyExcelExecutorContext){
        this.easyExcelExecutorContext = easyExcelExecutorContext;
    }

    public static EasyExcelExecutor bind(final EasyExcelHandler easyExcelHandler,final HttpServletResponse response){
        EasyExcelExecutorContext easyExcelExecutorContext = new EasyExcelExecutorContext();
        easyExcelExecutorContext.setEasyExcelHandler(easyExcelHandler);
        easyExcelExecutorContext.setResponse(response);
        return new EasyExcelExecutor(easyExcelExecutorContext);
    }

    public <T> void importExcel(final MultipartFile file,final Class<T> clazz) {
        if (Objects.nonNull(file)) {
            throw new RuntimeException("导入文件不能为空");
        }
        String fileName = file.getOriginalFilename();
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, clazz, new ExcelEventListener(easyExcelExecutorContext)).sheet(0).doRead();
    }

    public <T> void exportResponse( Class<T> clazz, String fileName,String sheetName,
        List<T> data) {
        HttpServletResponse response = easyExcelExecutorContext.response();
        if(Objects.isNull(response)) {
            throw new RuntimeException("未绑定响应{@HttpServletResponse}参数");
        }
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileNameEncoded = null;
        try {
            fileNameEncoded = URLEncoder
                .encode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("导出文件名编码异常：", e);
        }
        response.setHeader("Content-disposition", "attachment;filename=" + fileNameEncoded + ".xlsx");
        try(OutputStream outputStream = response.getOutputStream()){
            if(StringUtils.isNotBlank(sheetName)) {
                EasyExcel.write(outputStream,clazz).sheet(sheetName).doWrite(data);
            }else {
                EasyExcel.write(outputStream,clazz).sheet(0).doWrite(data);
            }

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
