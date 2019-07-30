package com.demo.controller;

import com.demo.excel.entity.DemoEntity;
import com.demo.excel.DemoImportModel;
import com.demo.excel.DemoModelConverter;
import com.demo.excel.ExcelUtils;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

/**
 * @title: ExcelDemoController
 * @projectName spring-cloud-demo
 * @description: excel Demo
 * @author zhanglong
 * @date 2019-07-3015:00
 */
@RestController
@RequestMapping("/excel")
public class ExcelDemoController {
  @GetMapping("importData")
  public Object importExcel(HttpServletRequest request) throws RuntimeException {
    if (!(request instanceof StandardMultipartHttpServletRequest)) {
      throw new RuntimeException("导入文件不能为空");
    }
    List<DemoImportModel> data = ExcelUtils.readFromRequest(request, DemoImportModel.class,2);
    List<DemoEntity> demoEntities = new ArrayList<>();
    if (CollectionUtils.isEmpty(data)) {
      throw new RuntimeException("导入数据空");
    }else {
      //进行数据转换
      demoEntities = new DemoModelConverter().convert(data);
    }
  //穿到后台进行处理 这里直接返回了
    return demoEntities;
  }
}
