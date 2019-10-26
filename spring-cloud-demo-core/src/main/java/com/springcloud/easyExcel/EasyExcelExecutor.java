package com.springcloud.easyExcel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * create by ZhangLong on 2019-08-31
 *
 * @description: 执行器 直接调用方法 默认使用 javax.validation 默认校验器，在实体类上添加 java.validation注解例如@NotNull(message = "xxx不能为空")
 */
@Slf4j
public final class EasyExcelExecutor {

    private EasyExcelExecutor() {
    }

    /**
     * create by ZhangLong on 2019-08-31
     *
     * @param excleDataConsumer 导入数据的消费处理 并且ExcleData非空
     *                          description 导入
     */
    public static <M extends ReadModel> void importExcel(Consumer<ExcleData<M>> excleDataConsumer, final MultipartFile file, final Class<M> clazz) {
        importExcelAndExportErrorData(excleDataConsumer, file, clazz, null);
    }

    /**
     * create by ZhangLong on 2019-08-31
     *
     * @param excleDataConsumer 导入数据的消费处理 并且ExcleData非空
     * @param response          不为null实现导出处理
     *                          description 导入
     */
    @SuppressWarnings("all")
    public static <M extends ReadModel> void importExcelAndExportErrorData(Consumer<ExcleData<M>> excleDataConsumer, final MultipartFile file,
                                                                           final Class<M> clazz, final HttpServletResponse response) {
        ExcleData<M> excleData = new ExcleData<>();
        if (Objects.isNull(file)) {
            throw new RuntimeException("导入文件不能为空");
        }
        try (InputStream inputStream = file.getInputStream()) {
            // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
            //新版本方法
//            EasyExcel.read(inputStream,clazz, new ExcelEventListener(excleDataConsumer,excleData));
            EasyExcelFactory.readBySax(inputStream, new Sheet(1, 1, clazz),
                    new ExcelEventListener(excleDataConsumer, excleData));
            if (Objects.nonNull(response)) {
                List<M> errorList = (List<M>) excleData.errorData();
                exportResponse(clazz, "error_" + file.getOriginalFilename(),
                        Objects.requireNonNull(file.getOriginalFilename())
                                .substring(file.getOriginalFilename().lastIndexOf(".")), response, klass -> excleData.errorData());
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * create by ZhangLong on 2019-08-31
     * description 导出数据
     */
    @SuppressWarnings("all")
    public static <M extends ExcelModel> void exportResponse(Class<M> clazz, String fileName, String sheetName,
                                                             HttpServletResponse response, Function<Class<M>, List<M>> dataListFunction) {
        Objects.requireNonNull(dataListFunction, "dataListFunction could not be null");
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
        response.setHeader("Content-disposition", "attachment;filename=" + fileNameEncoded + ".xlsx");
        try (OutputStream outputStream = response.getOutputStream()) {
            if (StringUtils.isBlank(sheetName)) {
                sheetName = "sheet0";
            }
            //新版本方法
//            EasyExcel.write(outputStream, clazz, sheetName, data);
            List<M> dataList = dataListFunction.apply(clazz);
            if (Objects.nonNull(dataList)) {
                ExcelWriter writer = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX, true);
                Sheet sheet1 = new Sheet(1, 0, clazz, sheetName, null);
                sheet1.setAutoWidth(true);
                writer.write(dataList, sheet1);
                writer.finish();
                outputStream.flush();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
    /**
     * create by ZhangLong on 2019/10/21
     * description 导出到服务本地指定文件目录下
     */
    public static <M extends ExcelModel> void exportResponse(Class<M> clazz, File excleFile, String sheetName,
                                                              List<M> dataList) {
        Objects.requireNonNull(excleFile, "dataListFunction could not be null");
        if (excleFile.exists()) {
            try (OutputStream outputStream = new FileOutputStream(excleFile)) {
                //新版本方法
                if (CollectionUtils.isNotEmpty(dataList)) {
                    ExcelWriter writer;
                    writer = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX, true);
                    Sheet sheet1 = new Sheet(1, 0, clazz, sheetName, null);
                    sheet1.setAutoWidth(true);
                    writer.write(dataList, sheet1);
                    writer.finish();
                    outputStream.flush();
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }else {
            try {
                excleFile.createNewFile();
                exportResponse(clazz, excleFile, sheetName, dataList);
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }
}