package com.demo.excel;

import com.alibaba.excel.metadata.BaseRowModel;

/**
 * @author Akuo
 */
public abstract class ReadModel extends BaseRowModel implements ILineChecker{
  @Override
  public abstract String check();
}
