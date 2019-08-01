//package com.springcloud.datasource.interceptor;
//
//import java.util.Properties;
//import java.util.concurrent.Executor;
//import org.apache.ibatis.mapping.MappedStatement;
//import org.apache.ibatis.plugin.Interceptor;
//import org.apache.ibatis.plugin.Intercepts;
//import org.apache.ibatis.plugin.Invocation;
//import org.apache.ibatis.plugin.Signature;
//import org.apache.ibatis.session.ResultHandler;
//import org.apache.ibatis.session.RowBounds;
//
///**
// * @author zhanglong
// * @title: DataSourceInterceptor
// * @projectName spring-cloud-demo
// * @description: 数据源拦截器
// * @date 2019-07-3023:18
// */
//@Intercepts({
//    @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
//        RowBounds.class, ResultHandler.class}),
//    @Signature(type = Executor.class, method = "update", args = {MappedStatement.class,
//        Object.class})})
//public class DataSourceInterceptor implements Interceptor {
//
//  @Override
//  public Object intercept(Invocation invocation) throws Throwable {
//    return null;
//  }
//
//  @Override
//  public Object plugin(Object target) {
//    return null;
//  }
//
//  @Override
//  public void setProperties(Properties properties) {
//
//  }
//}
