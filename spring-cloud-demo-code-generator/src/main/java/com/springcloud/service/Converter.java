package com.springcloud.service;

import com.springcloud.comm.Cfg;
import com.springcloud.utils.FileUtils;
import com.springcloud.utils.Utils;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Converter {

    /**
     * 生成Converter
     */
    public static List<String> generateConverter(String tableName) {
        List<String> retList = new ArrayList<String>();
        retList.add("package " + Cfg.servicePackage + ";");
        retList.add("\r");
        retList.add("import com.yh.csx.bsf.core.base.converter.PageConverter;");
        retList.add(
            "import " + Cfg.servicePackage + "." + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)
                + "DO;");
        retList.add(
            "import " + Cfg.corePackage + ".service.response." + Cfg.MODULE_EN_NAME + "." + Utils
                .getFirstUpperCase(Cfg.MODULE_EN_NAME) + "Response;");
        retList.add("import java.util.ArrayList;");
        retList.add("import java.util.List;");
        retList.add("\r");
        retList.add("/**" + Cfg.MODULE_CN_NAME + "DO");
        retList.add("  * @author " + Cfg.author);
        retList.add("  * @version " + Cfg.version);
        retList.add("  * @description ");
        retList.add("  * @date " + LocalDateTime.now());
        retList.add("  */");
        retList.add("\r");
        retList.add("public class " + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)
            + "LoadConverter extends PageConverter<" + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)
            + "DO, " + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME) + "Response> {");
        retList.add("\r");
        retList.add("	@Override");
        retList.add("	public List<" + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)
            + "Response> tList(List<" + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)
            + "DO> source) {");
        retList.add("		List<" + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)
            + "Response> list = new ArrayList<>(source.size());");
        retList.add("		source.forEach(item ->{");
        retList.add(
            "		" + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME) + "Response response = new "
                + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME) + "Response();");
        List<Map<String, String>> list = Utils.getTableInfo(tableName);
        for (Map<String, String> map : list) {
            retList.add(
                "			response.set" + Utils.getFirstUpperCase(map.get("beanColumnName"))
                    + "(item.get" + Utils.getFirstUpperCase(map.get("beanColumnName")) + "());");

        }

        retList.add("			list.add(response);");
        retList.add("		});");
        retList.add("		return list;");
        retList.add("	}");
        retList.add("}");

        //创建文件夹
        StringBuffer filePath = new StringBuffer(Cfg.PROJECT_ROOT_PATH).append("/")
            .append(Cfg.serviceProjectName.replace(".", "/")).append("/src/main/java/")
            .append(Cfg.serviceConverterPackage.replace(".", "/")).append("/");
//		Cfg.PROJECT_ROOT_PATH + "/csx-b2b-"+Cfg.PROJECT_NAME+"-service";
//		filePath += "/src/main/java/com/yh/csx/"+Cfg.PROJECT_NAME+"/service/converter/";
        File file = new File(filePath.toString());
        if (!file.exists()) {
            file.mkdir();
        }
        FileUtils.writeFile(
            filePath + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME) + "LoadConverter.java", retList);

        for (String s : retList) {
            System.out.println(s);
        }
        return retList;
    }


}
