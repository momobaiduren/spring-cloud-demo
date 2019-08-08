package com.springcloud.dao;

import com.springcloud.comm.Cfg;
import com.springcloud.utils.FileUtils;
import com.springcloud.utils.Utils;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Repository {
	
	/**
	 * 生成Repository
     * @param tableName
     */
	public static List<String> generateRepository(String tableName){
		List<String> retList = new ArrayList<String>();
		retList.add("package "+Cfg.repositoryPackage+";");
		retList.add("\r");
		retList.add("import com.baomidou.mybatisplus.service.IService;");
		retList.add("import "+Cfg.corePackage+".dao.model.entity."+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"DO;");
		retList.add("\r");
		retList.add("/**" + Cfg.MODULE_CN_NAME + "DO");
		retList.add("  * @author " + Cfg.author);
		retList.add("  * @version " + Cfg.version);
		retList.add("  * @description ");
		retList.add("  * @date " + LocalDateTime.now());
		retList.add("  */");
		retList.add("\r");
		retList.add("public interface I"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Repository extends IService<"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"DO>{");
		retList.add("\r");
		retList.add("}");
		//创建文件夹
//		String filePath = Cfg.PROJECT_ROOT_PATH + "/csx-b2b-"+Cfg.PROJECT_NAME+"-dao";
//		filePath += "/src/main/java/com/yh/csx/"+Cfg.PROJECT_NAME+"/dao/repository/";
		StringBuffer filePath = new StringBuffer(Cfg.PROJECT_ROOT_PATH).append("/")
			.append(Cfg.daoProjectName.replace(".", "/")).append("/src/main/java/")
			.append(Cfg.repositoryPackage.replace(".", "/")).append("/");
		File file = new File(filePath.toString());
		if(!file.exists()){
			file.mkdir();
		}
		FileUtils.writeFile(filePath + "I" +Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME) +"Repository.java", retList);
				
		for (String s : retList) {
			System.out.println(s);
		}		
		return retList;
	}
	
	/**
	 * 生成Repository Impl
	 * @param tableName
	 */
	public static List<String> generateRepositoryImpl(String tableName){
		List<String> retList = new ArrayList<String>();
		retList.add("package "+Cfg.repositoryPackage+".impl;");
		retList.add("\r");
		retList.add("import "+Cfg.corePackage+".dao.repository.I"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Repository;");
		retList.add("import "+Cfg.corePackage+".dao.mapper."+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Mapper;");
		retList.add("import org.springframework.stereotype.Repository;");
		retList.add("import com.baomidou.mybatisplus.service.impl.ServiceImpl;");
		retList.add("import "+Cfg.corePackage+".dao.model.entity."+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"DO;");
		retList.add("\r");
		retList.add("/**" + Cfg.MODULE_CN_NAME + "DO");
		retList.add("  * @author " + Cfg.author);
		retList.add("  * @version " + Cfg.version);
		retList.add("  * @description ");
		retList.add("  * @date " + LocalDateTime.now());
		retList.add("  */");
		retList.add("\r");
		retList.add("@Repository");
		retList.add("public class "+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"RepositoryImpl extends ServiceImpl<"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Mapper,"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"DO> implements I"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Repository{");
		retList.add("\r");
		retList.add("}");
		//创建文件夹
//		String filePath = Cfg.PROJECT_ROOT_PATH + "/csx-b2b-"+Cfg.PROJECT_NAME+"-dao";
//		filePath += "/src/main/java/com/yh/csx/"+Cfg.PROJECT_NAME+"/dao/repository/impl/";
		StringBuffer filePath = new StringBuffer(Cfg.PROJECT_ROOT_PATH).append("/")
			.append(Cfg.daoProjectName.replace(".", "/")).append("/src/main/java/")
			.append(Cfg.repositoryPackage.replace(".", "/")).append("impl/");
		File file = new File(filePath.toString());
		if(!file.exists()){
			file.mkdir();
		}
		FileUtils.writeFile(filePath + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME) +"RepositoryImpl.java", retList);
				
		for (String s : retList) {
			System.out.println(s);
		}
		return retList;
	}
	
}
