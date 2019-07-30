package com.demo.excel;

import com.demo.excel.entity.AverageDailyDeposit;
import com.demo.excel.entity.DemoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @projectName spring-cloud-demo
 * @description: 数据转换 将model数据转换成Entity
 * @author zhanglong
 * @date 2019-07-3015:32
 */

public class  DemoModelConverter implements  Converter<DemoEntity, DemoImportModel > {

  @Override
  public List<DemoEntity> convert(List<DemoImportModel> modelList) {
    List<DemoEntity> demoEntities = new ArrayList<>();
    modelList.forEach(model->{
      DemoEntity demoEntity =new DemoEntity();
      AverageDailyDeposit averageDailyDeposit = new AverageDailyDeposit();




    });
    return  demoEntities;
  }
}
