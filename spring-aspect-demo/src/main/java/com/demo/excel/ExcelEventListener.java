package com.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * @author Akuo
 * @param <T extends ReadModel>
 */
@Data
public class ExcelEventListener<T extends ReadModel> extends AnalysisEventListener<T> {

  private List<T> data = new ArrayList<>();
  private List<String> errorMsg = new ArrayList<>();



  @Override
  public void invoke(T model, AnalysisContext analysisContext) {
    String check = model.check();
    if (!StringUtils.isEmpty(check)) {
      errorMsg.add(String.format("第%d行检查错误：%s", analysisContext.getCurrentRowNum() + 1, check));
    }
    data.add(model);
  }

  @Override
  public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    //do nothing
  }

  public boolean hasError() {
    if (this.errorMsg.isEmpty()) {
      return false;
    }
    return true;
  }

}
