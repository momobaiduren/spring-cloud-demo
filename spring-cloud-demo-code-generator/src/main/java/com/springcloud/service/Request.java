package com.springcloud.service;

import com.springcloud.comm.Cfg;
import com.springcloud.utils.FileUtils;
import com.springcloud.utils.Utils;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Request {
	
	/**
	 * 生成Request
	 */
	public static List<String> generateRequest(String tableName){
		List<String> retList = new ArrayList<String>();
		retList.add("package "+Cfg.requestPackage+"."+Cfg.MODULE_EN_NAME+";");
		retList.add("\r");
		retList.add("import com.baomidou.mybatisplus.mapper.EntityWrapper;");
		retList.add("import com.yh.csx.bsf.core.base.model.GeneralRequest;");
		retList.add("import io.swagger.annotations.ApiModel;");
		retList.add("import io.swagger.annotations.ApiModelProperty;");
		retList.add("import lombok.Data;");
		retList.add("import org.springframework.util.StringUtils;");
		retList.add("import org.springframework.format.annotation.DateTimeFormat;");
		retList.add("import com.fasterxml.jackson.annotation.JsonFormat;");
		retList.add("\r");
		retList.add("/**" + Cfg.MODULE_CN_NAME + "DO");
		retList.add("  * @author " + Cfg.author);
		retList.add("  * @version " + Cfg.version);
		retList.add("  * @description ");
		retList.add("  * @date " + LocalDateTime.now());
		retList.add("  */");
		retList.add("\r");
		retList.add("@Data");
		retList.add("@ApiModel(value = \"创建"+Cfg.MODULE_CN_NAME+"\")");
		retList.add("\r");
		retList.add("public class "+ Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Request extends GeneralRequest {");
		List<Map<String, String>> list = Utils.getTableInfo(tableName);
		for (Map<String, String> map : list) {
			if(map.get("beanColumnName").equals("createBy") || map.get("beanColumnName").equals("createTime")
				|| map.get("beanColumnName").equals("updateBy") || map.get("beanColumnName").equals("updateTime")){
				continue;
			}
			if(map.get("javaType").endsWith("Date")){
				retList.add("	@JsonFormat(pattern = \"yyyy-MM-dd HH:mm:ss\")");
				retList.add("	@DateTimeFormat(pattern = \"yyyy-MM-dd HH:mm:ss\")");
			}
			retList.add("	@ApiModelProperty(value = \""+map.get("columnComment")+"\")");
			retList.add("	private " + map.get("javaType") + " " + map.get("beanColumnName") + ";");
		}
		retList.add("\r");
		retList.add("	public EntityWrapper tEntityWrapper() {");
		retList.add("		EntityWrapper ew = new EntityWrapper();");
		for (Map<String, String> map : list) {
			if(map.get("beanColumnName").equals("createBy") || map.get("beanColumnName").equals("createTime")
					|| map.get("beanColumnName").equals("updateBy") || map.get("beanColumnName").equals("updateTime")){
				continue;
			}
			
			if(map.get("javaType").equals("String")){
				retList.add("		if(!StringUtils.isEmpty("+map.get("beanColumnName")+")){");
				retList.add("			ew.eq(\""+map.get("dbColumnName")+"\", "+map.get("beanColumnName")+");");
				retList.add("		}");
			}else{
				retList.add("		if("+map.get("beanColumnName")+" != null){");
				retList.add("			ew.eq(\""+map.get("dbColumnName")+"\", "+map.get("beanColumnName")+");");
				retList.add("		}");
			}
		}
		
		retList.add("		ew.orderBy(\"create_time\", false);");
		retList.add("		return ew;");
		retList.add("	}");
		retList.add("\r");
		retList.add("}");
		
		//创建文件夹
//		String filePath = Cfg.PROJECT_ROOT_PATH + "/csx-b2b-"+Cfg.PROJECT_NAME+"-service";
//		filePath += "/src/main/java/com/yh/csx/"+Cfg.PROJECT_NAME+"/service/request/" ;
		StringBuffer filePath = new StringBuffer(Cfg.PROJECT_ROOT_PATH).append("/")
			.append(Cfg.serviceProjectName.replace(".", "/")).append("/src/main/java/")
			.append(Cfg.requestPackage.replace(".", "/")).append("/").append(Cfg.MODULE_EN_NAME).append("/");
		File file = new File(filePath.toString());
		if(!file.exists()){
			file.mkdir();
		}
//		filePath += Cfg.MODULE_EN_NAME + "/";
//		file = new File(filePath);
		if(!file.exists()){
			file.mkdir();
		}
		FileUtils.writeFile(filePath + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME) +"Request.java", retList);
				
		for (String s : retList) {
			System.out.println(s);
		}
		return retList;
	}
	
}
