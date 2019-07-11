//package com.springcloud.datasource.holder;
//
//import com.springcloud.datasource.enums.DataSourceEnum;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @title: DataSourceContextHolder
// * @projectName spring-cloud-demo
// * @description: TODO
// * @author zhanglong
// * @date 2019-07-0721:52
// */
//
//
//public class DataSourceContextHolder {
//  private static final ThreadLocal<DataSourceEnum> contextHolder = new InheritableThreadLocal<>();
//  public static List<DataSourceEnum> dataSourceKeys = new ArrayList();
//
//  public DataSourceContextHolder() {
//  }
//
//  public static void setDatasourceType(DataSourceEnum dbType) {
//    contextHolder.set(dbType);
//  }
//
//  public static DataSourceEnum getDatasourceType() {
//    DataSourceEnum dataSourceEnum = (DataSourceEnum)contextHolder.get();
//    return dataSourceEnum;
//  }
//
//  public static void clearDatasourceType() {
//    contextHolder.remove();
//  }
//
//  public static boolean containsDataSource(DataSourceEnum dataSourceEnum) {
//    return dataSourceKeys.contains(dataSourceEnum);
//  }
//}
