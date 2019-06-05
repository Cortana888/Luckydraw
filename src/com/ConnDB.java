package com;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

//从数据库中得到链接

public class ConnDB {
	
	static Properties pros = null;//可以帮助读取和处理资源部文件中的信息
	
	static{	//加载JDBCUtil类的时候调用
		pros = new Properties();
		
		try {
			pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getconn(){
		try {
			Class.forName(pros.getProperty("mysqlDriver"));
			return (Connection) DriverManager.getConnection(pros.getProperty("mysqlURL"),pros.getProperty("mysqlUser"),pros.getProperty("mysqlPwd"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
}
