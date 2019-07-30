package com.demo.excel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

/**
 * @author Akuo
 */
public class ExcelUtils {

    public static final int DEFAULT_SHEET = 1;

    private ExcelUtils() {
    }

    /**
     * 从HttpRequest中读取导入文件
     *
     * @param <T> 目标对象类型
     * @param request {@link HttpServletRequest} must be {@link StandardMultipartHttpServletRequest}
     * @param clazz 目标对象类型的Class对象
     * @param i
     * @return 读取结果
     */
    public static <T extends ReadModel> List<T> readFromRequest(HttpServletRequest request,
        Class<T> clazz, int startRowNum) {
        if (!(request instanceof StandardMultipartHttpServletRequest)) {
            throw new RuntimeException("导入文件不能为空");
        }

        List<T> dataList = new ArrayList<>();
        StandardMultipartHttpServletRequest standardMultipartRequest = (StandardMultipartHttpServletRequest) request;
        MultiValueMap<String, MultipartFile> multiFileMap = standardMultipartRequest.getMultiFileMap();
        Collection<List<MultipartFile>> values = multiFileMap.values();
        values.forEach(multipartFiles -> multipartFiles.forEach(multipartFile -> {
            try {
                ExcelEventListener<T> readRet = ExcelUtils
                        .read(new BufferedInputStream(multipartFile.getInputStream()), startRowNum,
                                clazz);
                dataList.addAll(readRet.getData());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));

        return dataList;
    }

    /**
     * 导入excel读取并校验数据
     *
     * @param inputStream 输入流
     * @param headLine 最开始一条的行数 0开始
     * @param clazz extends ReadModel
     * @return ExcelEventListener
     */
    public static <T extends ReadModel> ExcelEventListener<T> read(InputStream inputStream, int headLine,
            Class<T> clazz) {
        ExcelEventListener<T> excelEventListener = new ExcelEventListener<>();
        EasyExcelFactory
            .readBySax(inputStream, new Sheet(DEFAULT_SHEET, headLine, clazz), excelEventListener);
        return excelEventListener;
    }

    public static void exportResponse(HttpServletResponse response, Class clazz, String fileName,
            List<? extends BaseRowModel> data) {
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException("导出IO异常：");
        }
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        String fileNameEncoded = null;
        try {
            fileNameEncoded = URLEncoder
                    .encode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("导出文件名编码异常：");
        }
        response.setHeader("Content-disposition", "attachment;filename=" + fileNameEncoded + ".xlsx");
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
        Sheet sheet1 = new Sheet(1, 0, clazz, "sheet1", null);
        sheet1.setAutoWidth(true);
        writer.write(data, sheet1);
        writer.finish();
        try {
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException("导出流关闭异常：");
        }
    }

}
