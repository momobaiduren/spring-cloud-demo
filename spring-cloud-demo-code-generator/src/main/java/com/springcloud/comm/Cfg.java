package com.springcloud.comm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import lombok.Data;

@Data
public class Cfg {

	public static Cfg init() {
		Cfg cfg = new Cfg();
		Properties properties = new Properties();
		try(InputStream in = Cfg.class.getClassLoader().getResourceAsStream("application.properties")){
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cfg;
	}
	public static String DB_URL = null;
	public static String PROJECT_ROOT_PATH = null;

	public static String tableNames = null;

	public static String MODULE_EN_NAME = null;
	public static String MODULE_CN_NAME = null;
	public static String COLUMN_SELECT_OBJ = null;

	public static String author = null;
	public static String version = null;
	public static String corePackage = null;

	public static String controllerProjectName;
	public static String daoProjectName;
	public static String serviceProjectName;

	public static String controllerPackage;

	public static String entityPackage;
	public static String mapperPackage;
	public static String repositoryPackage;


	public static String serviceConverterPackage;
	public static String enumPackage;
	public static String requestPackage;
	public static String responsePackage;
	public static String servicePackage;



}
