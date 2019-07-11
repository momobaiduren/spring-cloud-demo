package com.springcloud.mybatis.generator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <p>
 * mysql 代码生成器演示例子
 * </p>
 *
 * @author zhanglong
 * @since 2018-09-12
 */
public class MysqlGenerator {


    /**
     * 连接地址  10.252.193.29:3306  root  yh2018b2b
     */
    private static String url = "jdbc:mysql://10.252.193.29:3306/csx_b2b_settle?autoCommit=true&useUnicode=true&autoReconnect=true&characterEncoding=utf8";

    //private static String url = "jdbc:mysql://cq-cdb-2tinkehp.sql.tencentcdb.com:63984/ZHESHUO_PRO_DB?autoCommit=true&useUnicode=true&autoReconnect=true&characterEncoding=utf8";
    /**
     * 驱动
     */
    private static String driverName = "com.mysql.jdbc.Driver";
    /**
     * 数据库账号
     */
    private static String username = "root";
    /**
     * 数据库密码  zskj123!
     */
    private static String password = "yh2018b2b";

    private static String parentpackage = "com.springcloud";
    /**
     * 当前项目名
     */
    private static String  projectName = "spring-cloud-demo-persist";
    /**
     * 生的表名
     */
    private static String[] tableNames = new String[]{
        "statement_source_bill"
    };

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * RUN THIS
     */
    public static void main(String[] args) {
        execute();
    }

    private static void execute() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/"+ projectName +"/src/main/java");
        gc.setAuthor("zhanglong");
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        dsc.setDriverName(driverName);
        dsc.setUsername(username);
        dsc.setPassword(password);
        mpg.setDataSource(dsc);

        // 包配置
        final PackageConfig pc = new PackageConfig();
        pc.setModuleName("auto");
        //基础包名  parentpackage
//        pc.setParent("com.baomidou.mybatisplus.samples.generator");
        pc.setParent(parentpackage);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/"+projectName+"/src/main/resources/mapper/auto"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //
//        strategy.setSuperEntityClass("com.baomidou.mybatisplus.samples.generator.common.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setSuperControllerClass(parentpackage + ".common.BaseController");
        strategy.setRestControllerStyle(true);
        if(tableNames.length == 0){
            String strTableName =  scanner("请在控制台输入表名例如： 表1，表2，表3等（多表用,分割开）--------------");
            String[] arrayStr  = strTableName.split(",");
            List<String> tableList = new ArrayList<>();
            for (int i = 0; i <arrayStr.length ; i++) {
                if(arrayStr[i] == null && arrayStr[i].trim().isEmpty()){
                    System.out.println(arrayStr[i]+"表不存在-------------------");
                    continue;
                }
                tableList.add(arrayStr[i]);
            }
            if(tableList.isEmpty()) {
                System.out.println("---------------没有表要生成类------------------");
                return;
            }
        }
        strategy.setInclude(tableNames);
        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
