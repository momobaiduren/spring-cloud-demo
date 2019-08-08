package com.springcloud.pojo;

import com.springcloud.comm.Cfg;
import com.springcloud.utils.FileUtils;
import com.springcloud.utils.Utils;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Pojo {

    public static List<String> generateCommBody(String tableName) {
        List<String> retList = new ArrayList<String>();
        List<Map<String, String>> list = Utils.getTableInfo(tableName);
        for (Map<String, String> map : list) {
            if (map.get("beanColumnName").equals("createBy") || map.get("beanColumnName")
                .equals("createTime")
                || map.get("beanColumnName").equals("updateBy") || map.get("beanColumnName")
                .equals("updateTime")) {
                continue;
            }
            retList.add("	//" + map.get("columnComment"));
            retList
                .add("	private " + map.get("javaType") + " " + map.get("beanColumnName") + ";");
        }
        retList.add("\r");

        return retList;
    }


    /**
     * 生成Do
     */
    public static List<String> generateDo(String tableName) {
        List<String> retList = new ArrayList<String>();
        retList.add("package " + Cfg.entityPackage + ";");
        retList.add("\r");
        retList.add("import com.baomidou.mybatisplus.annotations.TableId;");
        retList.add("import com.baomidou.mybatisplus.annotations.TableName;");
        retList.add("import com.baomidou.mybatisplus.enums.IdType;");
        retList.add("import com.yh.csx.bsf.core.base.model.GeneralDO;");
        retList.add("\r");
        retList.add("import lombok.Data;");
        retList.add("\r");
        retList.add("/**" + Cfg.MODULE_CN_NAME + "DO");
        retList.add("  * @author " + Cfg.author);
        retList.add("  * @version " + Cfg.version);
        retList.add("  * @description ");
        retList.add("  * @date " + LocalDateTime.now());
        retList.add("  */");
        retList.add("\r");
        retList.add("@Data");
        retList.add("@TableName(\"" + tableName + "\")");
        retList.add("public class " + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)
            + "DO extends GeneralDO{");
        retList.add("\r");
        retList.add("	private static final long serialVersionUID = 1L;");
        retList.add("\r");
        retList.add("	@TableId(type = IdType.ID_WORKER)");
        retList.addAll(generateCommBody(tableName));
        retList.add("}");

        //创建文件夹
//		corePackage
        StringBuffer filePath = new StringBuffer(Cfg.PROJECT_ROOT_PATH).append("/")
            .append(Cfg.daoProjectName.replace(".", "/")).append("/src/main/java/")
            .append(Cfg.entityPackage.replace(".", "/"));
//        Cfg.PROJECT_ROOT_PATH + "/csx-b2b-" + Cfg.PROJECT_NAME + "-dao";
//        filePath += "/src/main/java/com/yh/csx/" + Cfg.corePackage + "/dao/model/entity/";
        File file = new File(filePath.toString());
        if (!file.exists()) {
            file.mkdir();
        }
        FileUtils
            .writeFile(filePath + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME) + "DO.java", retList);

        for (String s : retList) {
            System.out.println(s);
        }
        return retList;
    }

}
