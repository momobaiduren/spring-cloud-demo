package com.springcloud.service;

import com.springcloud.comm.Cfg;
import com.springcloud.utils.FileUtils;
import com.springcloud.utils.Utils;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Enum {
	//枚举列中文名称
	private String cnName;
	//枚举列英文名称
	private String enName;
	private List<String[]> keyList;//枚举code，值，中文名称，code不配置默认为key+序号
	
	/**
	 * 生成Enum
     * @param tableName
     */
	public static void generateEnum(String tableName){
		
		//创建文件夹
//		String filePath = Cfg.PROJECT_ROOT_PATH + "/csx-b2b-"+Cfg.corePackage+"-service";
//		filePath += "/src/main/java/com/yh/csx/"+Cfg.PROJECT_NAME+"/service/enums/";
		StringBuffer filePath = new StringBuffer(Cfg.PROJECT_ROOT_PATH).append("/")
			.append(Cfg.serviceProjectName.replace(".", "/")).append("/src/main/java/")
			.append(Cfg.enumPackage.replace(".", "/")).append("/");
		File file = new File(filePath.toString());
		if(!file.exists()){
			file.mkdir();
		}

		List<com.springcloud.service.Enum> enumList = Utils.enumList;
		for (Enum enum1 : enumList) {
			List<String> retList = new ArrayList<>();
			retList.add("package "+Cfg.enumPackage+";");
			retList.add("\r");
			retList.add("import "+Cfg.corePackage+".core.enums.ErrorCodeEnum;");
			retList.add("import "+Cfg.corePackage+".core.exception.SettleException;");
			retList.add("\r");
			retList.add("/**" + Cfg.MODULE_CN_NAME + "DO");
			retList.add("  * @author " + Cfg.author);
			retList.add("  * @version " + Cfg.version);
			retList.add("  * @description ");
			retList.add("  * @date " + LocalDateTime.now());
			retList.add("  */");
			retList.add("\r");
			retList.add("public enum "+ Utils.getFirstUpperCase(enum1.enName)+"Enum {");

			for (String[] arrys : enum1.getKeyList()) {
				if(arrys.length > 2){//有配置key名称
					retList.add("	" + arrys[0] +"("+ arrys[1] +", \""+ arrys[2] +"\"),");
				}else{
					retList.add("	KEY"+ arrys[0] +"("+ arrys[0] +", \""+ arrys[1] +"\"),");
				}
			}
			retList.add("	;");
			retList.add("\r");
			retList.add("	private Integer code;");
			retList.add("\r");
			retList.add("	private String desc;");
			retList.add("\r");
			retList.add("	"+Utils.getFirstUpperCase(enum1.enName)+"Enum(Integer code, String desc) {");
			retList.add("		this.code = code;");
			retList.add("		this.desc = desc;");
			retList.add("	}");
			retList.add("\r");
			retList.add("	public Integer value() {");
			retList.add("		return this.code;");
			retList.add("	}");
			retList.add("\r");
			retList.add("	public String desc() {");
			retList.add("		return this.desc;");
			retList.add("	}");
			retList.add("\r");
			retList.add("	public static "+Utils.getFirstUpperCase(enum1.enName)+"Enum tEnum(String target) {");
			retList.add("		for ("+Utils.getFirstUpperCase(enum1.enName)+"Enum el : "+Utils.getFirstUpperCase(enum1.enName)+"Enum.values()) {");
			retList.add("			if (el.code.equals(target)) {");
			retList.add("				return el;");
			retList.add("			}");
			retList.add("		}");
			retList.add("		throw new SettleException(ErrorCodeEnum.SYS50001001, target);");
			retList.add("	}");
			retList.add("\r");
			retList.add("	public static String tDesc(Integer target) {");
			retList.add("		for ("+Utils.getFirstUpperCase(enum1.enName)+"Enum el : "+Utils.getFirstUpperCase(enum1.enName)+"Enum.values()) {");
			retList.add("			if (el.code.equals(target)) {");
			retList.add("				return el.desc;");
			retList.add("			}");
			retList.add("		}");
			retList.add("		throw new SettleException(ErrorCodeEnum.SYS50001001, target);");
			retList.add("	}");
			retList.add("}");

			FileUtils.writeFile(filePath + Utils.getFirstUpperCase(enum1.enName) +"Enum.java", retList);
			
		}
		
	}


	
	
	public String getCnName() {
		return cnName;
	}
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public List<String[]> getKeyList() {
		return keyList;
	}
	public void setKeyList(List<String[]> keyList) {
		this.keyList = keyList;
	}
}
