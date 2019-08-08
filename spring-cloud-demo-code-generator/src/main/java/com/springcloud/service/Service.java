package com.springcloud.service;

import com.springcloud.comm.Cfg;
import com.springcloud.utils.FileUtils;
import com.springcloud.utils.Utils;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Service {
	
	
	/**
	 * 生成Service
     * @param tableName
     */
	public static List<String> generateService(String tableName){
		List<String> retList = new ArrayList<String>();
		retList.add("package "+Cfg.servicePackage+";");
		retList.add("\r");
		retList.add("import com.yh.csx.bsf.core.base.model.PageResponse;");
		retList.add("import "+Cfg.requestPackage+"."+Cfg.MODULE_EN_NAME+"."+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Request;");
		retList.add("import "+Cfg.responsePackage+"."+Cfg.MODULE_EN_NAME+"."+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Response;");
		retList.add("import java.util.List;");
		retList.add("\r");
		retList.add("/**" + Cfg.MODULE_CN_NAME + "DO");
		retList.add("  * @author " + Cfg.author);
		retList.add("  * @version " + Cfg.version);
		retList.add("  * @description ");
		retList.add("  * @date " + LocalDateTime.now());
		retList.add("  */");
		retList.add("\r");
		retList.add("public interface I"+ Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Service {");
		retList.add("\r");
		retList.add("	/**新增"+Cfg.MODULE_CN_NAME+"*/");
		retList.add("	"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Response create"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"("+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Request request);");
		retList.add("\r");
		retList.add("	/**删除"+Cfg.MODULE_CN_NAME+"*/");
		retList.add("	void delete"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"(String id);");
		retList.add("\r");
		retList.add("	/**修改"+Cfg.MODULE_CN_NAME+"*/");
		retList.add("	"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Response update"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"("+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Request request);");
		retList.add("\r");
		retList.add("	/**获取"+Cfg.MODULE_CN_NAME+"详情*/");
		retList.add("	"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Response "+Cfg.MODULE_EN_NAME+"Info(String id);");
		retList.add("\r");
		retList.add("	/**分页查询"+Cfg.MODULE_CN_NAME+"列表*/");
		retList.add("	PageResponse<"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Response> loadPage("+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Request request);");
		retList.add("\r");
		retList.add("	/**查询所有"+Cfg.MODULE_CN_NAME+"列表*/");
		retList.add("	List<"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Response> all();");
		
		if(!Cfg.COLUMN_SELECT_OBJ.trim().equals("")){
			String[] arrys = Cfg.COLUMN_SELECT_OBJ.split(";");
			for (int i = 0; i < arrys.length; i++) {
				String[] arrysI = arrys[i].split(",");
				retList.add("\r");
				retList.add("	/**根据"+arrysI[1]+"获取"+Cfg.MODULE_CN_NAME+"详情*/");
				retList.add("	"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Response get"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"InfoBy"+Utils.getFirstUpperCase(Utils.getTuoFeng(arrysI[0]))+"(String "+Utils.getTuoFeng(arrysI[0])+");");
			}
		}
		
		retList.add("\r");
		retList.add("}");
		
		//创建文件夹
//		String filePath = Cfg.PROJECT_ROOT_PATH + "/csx-b2b-"+Cfg.PROJECT_NAME+"-service";
//		filePath += "/src/main/java/com/yh/csx/"+Cfg.PROJECT_NAME+"/service/";
		StringBuffer filePath = new StringBuffer(Cfg.PROJECT_ROOT_PATH).append("/")
			.append(Cfg.serviceProjectName.replace(".", "/")).append("/src/main/java/")
			.append(Cfg.servicePackage.replace(".", "/")).append("/");
		File file = new File(filePath.toString());
		if(!file.exists()){
			file.mkdir();
		}
		FileUtils.writeFile(filePath + "I" + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME) +"Service.java", retList);
				
		for (String s : retList) {
			System.out.println(s);
		}
		return retList;
	}
	
	/**
	 * 生成Service Impl
	 * @param tableName
	 */
	public static List<String> generateServiceImpl(String tableName){
		List<String> retList = new ArrayList<String>();
		retList.add("package "+Cfg.servicePackage+".impl;");
		retList.add("\r");
		retList.add("import com.baomidou.mybatisplus.mapper.EntityWrapper;");
		retList.add("import com.baomidou.mybatisplus.plugins.Page;");
		retList.add("import com.yh.csx.bsf.core.base.converter.ConverterManager;");
		retList.add("import com.yh.csx.bsf.core.base.model.PageResponse;");
		retList.add("import lombok.extern.slf4j.Slf4j;");
		retList.add("import org.springframework.beans.factory.annotation.Autowired;");
		retList.add("import org.springframework.stereotype.Service;");
		retList.add("import org.springframework.transaction.annotation.Transactional;");
		retList.add("import java.util.List;");
		retList.add("import org.springframework.beans.BeanUtils;");
		retList.add("import com.yh.csx."+Cfg.corePackage+".service.I"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Service;");
		retList.add("import com.yh.csx."+Cfg.corePackage+".dao.repository.I"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Repository;");
		retList.add("import com.yh.csx."+Cfg.corePackage+".dao.model.entity."+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"DO;");
		retList.add("import com.yh.csx.bsf.core.security.user.UserManager;");
		retList.add("import java.util.Date;");
		retList.add("import com.yh.csx."+Cfg.corePackage+".service.request."+Cfg.MODULE_EN_NAME+"."+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Request;");
		retList.add("import com.yh.csx."+Cfg.corePackage+".service.response."+Cfg.MODULE_EN_NAME+"."+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Response;");
		retList.add("import com.yh.csx."+Cfg.corePackage+".service.converter."+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"LoadConverter;");
		retList.add("\r");
		retList.add("/**"+Cfg.MODULE_CN_NAME+"ServiceImpl*/");
		retList.add("@Slf4j");		
		retList.add("@Service");
		retList.add("public class "+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"ServiceImpl implements I"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Service {");
		retList.add("\r");
		retList.add("	@Autowired");
		retList.add("	private I"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Repository "+Cfg.MODULE_EN_NAME+"Repository;");
		retList.add("\r");
		retList.add("	@Override");
		retList.add("	@Transactional");
		retList.add("	public "+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Response create"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"("+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Request request) {");
		retList.add("		"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"DO "+Cfg.MODULE_EN_NAME+" = request.tDO("+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"DO.class);");
		retList.add("		Date date = new Date();");
		retList.add("		UserManager userManager = UserManager.init();");
		retList.add("		BeanUtils.copyProperties(request, "+Cfg.MODULE_EN_NAME+");");
		retList.add("		"+Cfg.MODULE_EN_NAME+".setCreateBy(userManager.getUserName());");
		retList.add("		"+Cfg.MODULE_EN_NAME+".setCreateTime(date);");
		retList.add("		"+Cfg.MODULE_EN_NAME+"Repository.insert("+Cfg.MODULE_EN_NAME+");");
		retList.add("		return "+Cfg.MODULE_EN_NAME+".tResponse("+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Response.class);");
		retList.add("	}");
		retList.add("\r");
		retList.add("	@Override");
		retList.add("	public PageResponse<"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Response> loadPage("+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Request request) {");
		retList.add("		Page<"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"DO> page = "+Cfg.MODULE_EN_NAME+"Repository.selectPage(new Page<>(request.getPage(),request.size()),request.tEntityWrapper());");
		retList.add("		return ConverterManager.builder("+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"LoadConverter.class).convert(page);");
		retList.add("	}");
		retList.add("\r");
		retList.add("	@Override");
		retList.add("	@Transactional");
		retList.add("	public "+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Response update"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"("+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Request request) {");
		retList.add("		UserManager userManager = UserManager.init();");
		retList.add("		"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"DO "+Cfg.MODULE_EN_NAME+"DO = "+Cfg.MODULE_EN_NAME+"Repository.selectById(request.getId());");
		retList.add("		"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"DO "+Cfg.MODULE_EN_NAME+" = request.tDO("+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"DO.class);");
		retList.add("		BeanUtils.copyProperties(request, "+Cfg.MODULE_EN_NAME+");");
		retList.add("		"+Cfg.MODULE_EN_NAME+".setUpdateBy(userManager.getUserName());");
		retList.add("		"+Cfg.MODULE_EN_NAME+".setUpdateTime(new Date());");
		retList.add("		"+Cfg.MODULE_EN_NAME+"Repository.updateAllColumnById("+Cfg.MODULE_EN_NAME+");");
		retList.add("		return "+Cfg.MODULE_EN_NAME+".tResponse("+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Response.class);");
		retList.add("	}");
		retList.add("\r");
		retList.add("	@Override");
		retList.add("	@Transactional");
		retList.add("	public void delete"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"(String id) {");
		retList.add("		"+Cfg.MODULE_EN_NAME+"Repository.deleteById(Long.valueOf(id));");
		retList.add("	}");
		retList.add("\r");
		retList.add("	@Override");
		retList.add("	public "+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Response "+Cfg.MODULE_EN_NAME+"Info(String id) {");
		retList.add("		"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"DO "+Cfg.MODULE_EN_NAME+" = "+Cfg.MODULE_EN_NAME+"Repository.selectById(Long.valueOf(id));");
		retList.add("		"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Response response = "+Cfg.MODULE_EN_NAME+".tResponse("+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Response.class);");
		retList.add("		return response;");
		retList.add("	}");
		
		retList.add("\r");
		retList.add("	@Override");
		retList.add("	public List<"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Response> all() {");
		retList.add("		EntityWrapper ew = new EntityWrapper();");
		retList.add("		ew.orderBy(\"create_time\", true);");
		retList.add("		List<"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"DO> list = "+Cfg.MODULE_EN_NAME+"Repository.selectList(ew);");
		retList.add("		"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"LoadConverter converter = new "+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"LoadConverter();");
		retList.add("		return converter.tList(list);");
		retList.add("	}");
		
		if(!Cfg.COLUMN_SELECT_OBJ.trim().equals("")){
			String[] arrys = Cfg.COLUMN_SELECT_OBJ.split(";");
			for (int i = 0; i < arrys.length; i++) {
				String[] arrysI = arrys[i].split(",");
				retList.add("\r");
				retList.add("	@Override");
				retList.add("	/**根据"+arrysI[1]+"获取"+Cfg.MODULE_CN_NAME+"详情*/");
				retList.add("	public "+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Response get"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"InfoBy"+Utils.getFirstUpperCase(Utils.getTuoFeng(arrysI[0]))+"(String "+Utils.getTuoFeng(arrysI[0])+"){");
				retList.add("		EntityWrapper ew = new EntityWrapper();");
				retList.add("		ew.eq(\""+arrysI[0]+"\", "+Utils.getTuoFeng(arrysI[0])+");");
				retList.add("		"+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"DO "+Cfg.MODULE_EN_NAME+"DO = "+Cfg.MODULE_EN_NAME+"Repository.selectOne(ew);");
				retList.add("		return "+Cfg.MODULE_EN_NAME+"DO.tResponse("+Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)+"Response.class);");
				retList.add("	}");
			}
		}
		retList.add("\r");
		retList.add("}");
		
		//创建文件夹
//		String filePath = Cfg.PROJECT_ROOT_PATH + "/csx-b2b-"+Cfg.PROJECT_NAME+"-service";
//		filePath += "/src/main/java/com/yh/csx/"+Cfg.PROJECT_NAME+"/service/impl/";

		StringBuffer filePath = new StringBuffer(Cfg.PROJECT_ROOT_PATH).append("/")
			.append(Cfg.serviceProjectName.replace(".", "/")).append("/src/main/java/")
			.append(Cfg.servicePackage.replace(".", "/")).append("/").append("impl").append("/");

		File file = new File(filePath.toString());
		if(!file.exists()){
			file.mkdir();
		}
		FileUtils.writeFile(filePath + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME) +"ServiceImpl.java", retList);
				
		for (String s : retList) {
			System.out.println(s);
		}
		return retList;
	}
}
