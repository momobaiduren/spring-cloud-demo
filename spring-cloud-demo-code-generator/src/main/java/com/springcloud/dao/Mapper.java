package com.springcloud.dao;

import com.springcloud.comm.Cfg;
import com.springcloud.utils.FileUtils;
import com.springcloud.utils.Utils;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Mapper {
	
	/**
	 * 生成mapper
     * @param tableName
     */
	public static List<String> generateMapper(String tableName){
		List<String> retList = new ArrayList<String>();
		retList.add("package " + Cfg.mapperPackage+ ";");
		retList.add("\r");
		retList.add("import org.apache.ibatis.annotations.Mapper;");
		retList.add("import com.baomidou.mybatisplus.mapper.BaseMapper;");
		retList.add("import "+Cfg.corePackage+".dao.model.entity."+ Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"DO;");

		retList.add("\r");
		retList.add("/**" + Cfg.MODULE_CN_NAME + "DO");
		retList.add("  * @author " + Cfg.author);
		retList.add("  * @version " + Cfg.version);
		retList.add("  * @description ");
		retList.add("  * @date " + LocalDateTime.now());
		retList.add("  */");
		retList.add("\r");

		retList.add("@Mapper");
		retList.add("public interface "+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Mapper extends BaseMapper<"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"DO>{");
		retList.add("\r");
		retList.add("}");
		
		//创建文件夹
		StringBuffer filePath = new StringBuffer(Cfg.PROJECT_ROOT_PATH).append("/")
			.append(Cfg.daoProjectName.replace(".", "/")).append("/src/main/java/")
			.append(Cfg.mapperPackage.replace(".", "/")).append("/");
		File file = new File(filePath.toString());
		if(!file.exists()){
			file.mkdir();
		}
		FileUtils.writeFile(filePath + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME) +"Mapper.java", retList);
				
		for (String s : retList) {
			System.out.println(s);
		}
		return retList;
	}
	
}
