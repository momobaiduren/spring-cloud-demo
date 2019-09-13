package com.demo.excel;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Objects;

/**
 * @author zhanglong
 * @description: 执行器
 * @date 2019-08-3120:46
 */
@Slf4j
public final class EasyExcelExecutor {

    private EasyExcelExecutorContext easyExcelExecutorContext;

    private EasyExcelExecutor() {
    }

    public static EasyExcelExecutor init(EasyExcelHandler easyExcelHandler) {
        EasyExcelExecutor easyExcelExecutor = new EasyExcelExecutor();
        if (Objects.isNull(easyExcelHandler)) {
            easyExcelHandler = EasyExcelHandler.DEFAULTEASYEXCELHANDLER;
        }
        if(Objects.isNull(easyExcelExecutor.easyExcelExecutorContext)) {
            easyExcelExecutor.easyExcelExecutorContext = new EasyExcelExecutorContext();
            easyExcelExecutor.easyExcelExecutorContext.easyExcelExecutorContextBuilder().builderEasyExcelHandler(easyExcelHandler);
        }
        return easyExcelExecutor;
    }


    public <M extends ReadModel> void importExcel(final MultipartFile file, final Class<M> clazz ) {
        importExcel(file, clazz,null, false);
    }

    public <M extends ReadModel> void importExcel(final MultipartFile file, final Class clazz, HttpServletResponse response,
                                         final boolean isEmportError ) {
        if (Objects.isNull(file)) {
            throw new RuntimeException("导入文件不能为空");
        }
        try (InputStream inputStream = file.getInputStream()) {
            // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
            EasyExcel.read(inputStream, clazz, new ExcelEventListener(easyExcelExecutorContext));
            if (isEmportError && Objects.nonNull(response)) {
              List<M> errorList =  easyExcelExecutorContext.dataHandler().errorData();
                exportResponse(clazz, "error_" + file.getOriginalFilename(),
                    file.getOriginalFilename()
                        .substring(file.getOriginalFilename().lastIndexOf(".")),errorList,response);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public <M extends ExcelModel>  void exportResponse( Class<M> clazz, String fileName, String sheetName,
        List<M> data, HttpServletResponse response ) {
        if (Objects.isNull(response)) {
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
        response
            .setHeader("Content-disposition", "attachment;filename=" + fileNameEncoded + ".xlsx");
        try (OutputStream outputStream = response.getOutputStream()) {
            if (StringUtils.isBlank(sheetName)) {
                sheetName = "sheet0";
            }
            EasyExcel.write(outputStream, clazz,sheetName,data);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
