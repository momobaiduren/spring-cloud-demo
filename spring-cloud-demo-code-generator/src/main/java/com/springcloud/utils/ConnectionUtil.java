package com.springcloud.utils;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionUtil {

	public static Connection getMySqlConnection(String dbUrl){
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
//			String url="jdbc:mysql://" + ERPConfig.dbIpPort +"/" + ERPConfig.dbName + "?user=" + ERPConfig.dbUserName + "&password=" + ERPConfig.dbUserPwd;
			String url = dbUrl;
			con = DriverManager.getConnection(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
	
	
	public static void main(String[] args) {
		String url = "jdbc:mysql://10.252.192.49:3306/demo?useUnicode=true&characterEncoding=UTF8&useSSL=false&allowMultiQueries=true&user=root&password=yh2018b2b";
		System.out.println(getMySqlConnection(url) == null);
		//SQLSERVER查询表的列名以及数据类型语句
		//select column_name,data_type from information_schema.columns where table_name = N's_user'
	}
}
