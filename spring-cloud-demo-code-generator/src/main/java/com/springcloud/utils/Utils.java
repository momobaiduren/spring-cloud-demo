package com.springcloud.utils;

import com.springcloud.comm.Cfg;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Utils {
	
	public static List<Map<String, String>> TABLE_INFO = null;
	public static List<com.springcloud.service.Enum> enumList = null;

	/**
	 * 返回数据库的列名，数据库类型，BEAN列名，Java类型
	 * @return
	 */
	public static List<Map<String, String>> getTableInfo(String tabaleName){
//		if(TABLE_INFO != null){
//			return TABLE_INFO;
//		}
		enumList = new ArrayList<>();
		
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			Statement stmt = ConnectionUtil.getMySqlConnection(Cfg.DB_URL).createStatement();
			String sql = "select column_name as columnName,data_type as dataType,column_comment as columnComment, "
					+ "is_nullable as isNullable from information_schema.columns where table_name = N'" + tabaleName + "' ";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				Map<String, String> map = new HashMap<String, String>();
				String columnName = rs.getString("columnName").toLowerCase();
				map.put("dbColumnName", columnName);
				map.put("isNullable", rs.getString("isNullable").toUpperCase());//是否可以为空 YES可以，NO不可以
				if(columnName.contains("_")){
					
					StringBuilder sb = new StringBuilder();
					int index = columnName.indexOf("_");
					sb.append(columnName.substring(0, index));
					sb.append(columnName.substring(index + 1, index + 2).toUpperCase());
					sb.append(columnName.substring(index + 2, columnName.length()));
					map.put("beanColumnName", getTuoFeng(sb.toString()));
				}else{
					map.put("beanColumnName", columnName);
				}

				String dataType = rs.getString("dataType").toUpperCase();
				map.put("dataType", dataType);
				String javaType = "";
				if(dataType.equals("VARCHAR")){
					javaType = "String";
				}else if(dataType.equals("CHAR")){
					javaType = "String";
				}else if(dataType.equals("TEXT")){
					javaType = "String";
				}else if(dataType.equals("JSON")){
					javaType = "String";
				}
				
				else if(dataType.equals("TINYINT")){
					javaType = "Integer";
				}else if(dataType.equals("SMALLINT")){
					javaType = "Integer";
				}else if(dataType.equals("MEDIUMINT")){
					javaType = "Integer";
				}
				
				else if(dataType.equals("INT")){
					javaType = "Long";
				}else if(dataType.equals("INTEGER")){
					javaType = "Long";
				}else if(dataType.equals("BIGINT")){
					javaType = "Long";
				}
				
				else if(dataType.equals("BIT")){
					javaType = "Boolean";
				}
				
				
				
				else if(dataType.equals("DECIMAL")){
					javaType = "java.math.BigDecimal";
				}

				else if(dataType.equals("DOUBLE")){
					javaType = "Double";
				}else if(dataType.equals("FLOAT")){
					javaType = "Float";
				}
				
				else if(dataType.equals("DATE")){
					javaType = "java.util.Date";
				}else if(dataType.equals("TIME")){
					javaType = "java.util.Date";
				}else if(dataType.equals("DATETIME")){
					javaType = "java.util.Date";
				}else if(dataType.equals("TIMESTAMP")){
					javaType = "java.util.Date";
				}
				
				else if(dataType.equals("BLOB")){
					javaType = "java.lang.byte[]";
				}
				map.put("javaType", javaType);
				
				String columnComment = rs.getString("columnComment");
				if(columnComment != null){
					if(columnComment.startsWith("枚举")){
						map.put("columnComment", columnComment.replaceAll("\\\r\\\n", " "));//注释用
						String[] arrys = columnComment.split("\\\r\\\n");
						com.springcloud.service.Enum enum1 = new com.springcloud.service.Enum();
						enum1.setCnName(arrys[1]);
						enum1.setEnName(map.get("beanColumnName"));
						List<String[]> keyList = new ArrayList<>();
						for (int i = 2; i < arrys.length; i++) {
							String arrysI = arrys[i];
							arrysI = arrysI.replaceAll("，", ",");
							keyList.add(arrysI.split(","));
						}
						enum1.setKeyList(keyList);
						enumList.add(enum1);
					}else{
						map.put("columnComment", columnComment);
					}
				}else{
					map.put("columnComment", " ");
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		TABLE_INFO = list;
		return list;
	}
	
	/**
	 * 提取出要显示在页面的列
	 * @param tableInfoList
	 * @return
	 */
//	public static List<Map<String, String>> getShowCloumnInfo(List<Map<String, String>> tableInfoList){
//		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
//		String[] arry = Config.showColumn.split(";");
//		for (int i = 0; i < arry.length; i++) {
//			for (Map<String, String> map : tableInfoList) {
//				if (map.get("dbColumnName").equals(arry[i])) {
//					list.add(map);
//				}
//			}
//		}
//		return list;
//	}
	
	/**
	 * 提取出查询参数列
	 * @param tableInfoList
	 * @return
	 */
//	public static List<Map<String, String>> getQueryParaCloumnInfo(List<Map<String, String>> tableInfoList){
//		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
//		String[] arry = Config.queryParaColumn.split(";");
//		for (int i = 0; i < arry.length; i++) {
//			String[] para = arry[i].split(",");
//			for (Map<String, String> map : tableInfoList) {
//				if (map.get("dbColumnName").equals(para[0])) {
//					map.put("queryType", para[1]);
//					list.add(map);
//				}
//			}
//		}
//		return list;
//	}
	
	/**
	 * 获得首字母大写
	 * @return
	 */
	public static String getFirstUpperCase(String value){
		
		return value.substring(0, 1).toUpperCase() + value.substring(1, value.length());
	}
	
	/**
	 * 获得驼峰法则的字符串
	 * @return
	 */
	public static String getTuoFeng(String columnName){
		if(!columnName.contains("_")){
			return columnName;
		}
		StringBuilder sb = new StringBuilder();
		int index = columnName.indexOf("_");
		sb.append(columnName.substring(0, index));
		sb.append(columnName.substring(index + 1, index + 2).toUpperCase());
		sb.append(columnName.substring(index + 2, columnName.length()));
		while(sb.toString().contains("_")){
			return getTuoFeng(sb.toString());
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		
//		List<Map<String, String>> list = getTableInfo();
//		List<Map<String, String>> list2 = getQueryParaCloumnInfo(list);
//		for (Map<String, String> map : list2) {
//			System.out.println(map.get("dbColumnName"));
//			System.out.println(map.get("beanColumnName"));
//			System.out.println(map.get("dataType"));
//			System.out.println(map.get("javaType"));
//			System.out.println(map.get("queryType"));
//			System.out.println("------------");
//		}
		System.out.println(getTuoFeng("abc_def_ghj_hui"));
	}
}
