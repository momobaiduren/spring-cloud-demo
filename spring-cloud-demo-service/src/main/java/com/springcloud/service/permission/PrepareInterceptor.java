package com.springcloud.service.permission;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import java.sql.Connection;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMap;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

/**
 * <Description> <br>
 *
 * @author jiangzh<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2019/08/17 14:20 <br>
 */

@Slf4j
@Component
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PrepareInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
        MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");

        if (!SqlCommandType.SELECT.equals(mappedStatement.getSqlCommandType())) {
            return invocation.proceed();
        }

//        Class<?> doClass = mappedStatement.getResultMaps().get(0).getType();
//        Map<Field, PermissionField> fieldPermissionFieldMap = getFieldPermissionFieldMap(doClass);

        Class<?> doClass = mappedStatement.getResultMaps().get(0).getType();
        if (doClass == null) {
            return invocation.proceed();
        }
        ParameterMap parameterMap = mappedStatement.getParameterMap();
//        BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
//        String originalSql = boundSql.getSql();
        //拼接sql
//        String sql = joinSql(doClass,originalSql);
//        metaStatementHandler.setValue("delegate.boundSql.sql", originalSql);
        return invocation.proceed();

    }


//    private String joinSql(Class<?> doClass,String originalSql) {
//        UserManager init = UserManager.init();
//        //公司权限
//        List<String> companys = init.getProperties("companys");
//        //采购组织权限
//        List<String> purchaseOrgList = init.getPurchaseOrgList();
//
//        //是否是供应商协同
////        SupplierVO defaultSupplier = init.getProperties("defaultSupplier");
//
//        String [] scopeNames = new String[]{
//            "company_code",
//            "purchase_org_code"
//        };
//
//        Field companyCode = null;
//        Field purchaseOrgCode = null;
//        try{
//            companyCode = doClass.getDeclaredField("companyCode");
//            purchaseOrgCode = doClass.getDeclaredField("purchaseOrgCode");
//        }catch (Exception e) {
//
//        }
//
//        boolean annotationPresent = false;
//        boolean annotationPresent2 = false;
//        if (companyCode != null) {
//            annotationPresent = companyCode.isAnnotationPresent(AuthFieldIgnore.class);
//        }
//        if (purchaseOrgCode != null) {
//            annotationPresent2 = purchaseOrgCode.isAnnotationPresent(AuthFieldIgnore.class);
//        }
//
//        if (!annotationPresent && !annotationPresent2) {
//            return originalSql;
//        }
//
////        TableName annotation = doClass.getAnnotation(TableName.class);
////        String tabName = annotation.value();
//
//        int whereInx = originalSql.indexOf("WHERE");
//        int orderByInx = originalSql.indexOf("ORDER BY") == -1 ? originalSql.length() : originalSql.indexOf("ORDER BY");
//
//        String tab = whereInx == -1 ? originalSql.substring(0, orderByInx) : originalSql.substring(0, whereInx) ;
//        String where = whereInx == -1 ? " where 1=1 " : originalSql.substring(whereInx, orderByInx) ;
//        String orderBy = originalSql.substring(orderByInx);
//
//        String join ="";
//        if (annotationPresent) {
////            join +=" and temp_data_scope." + scopeNames[0] + " in ('" + companys.stream().collect(Collectors.joining("','")) + "')";
////            join +=" and " + tabName+ "." + scopeNames[0] + " in ('" + companys.stream().collect(Collectors.joining("','")) + "') ";
//            join +=" and company_code in ('" + companys.stream().collect(Collectors.joining("','")) + "') ";
//        }
//        if (annotationPresent2) {
////            join +=" and temp_data_scope." + scopeNames[1] + " in ('" + purchaseOrgList.stream().collect(Collectors.joining("','")) + "')";
////            join +=" and " + tabName+ "." + scopeNames[1] + " in ('" + purchaseOrgList.stream().collect(Collectors.joining("','")) + "') ";
//            join +=" and purchase_org_code in ('" + purchaseOrgList.stream().collect(Collectors.joining("','")) + "') ";
//        }
//
//        String sql = tab + where + join + orderBy ;
//
////        String sql = "select * from (" + originalSql + ") temp_data_scope "+ join;
//        return sql;
//    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }


}
