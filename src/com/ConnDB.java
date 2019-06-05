package com;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

//�����ݿ��еõ�����

public class ConnDB {
	
	static Properties pros = null;//���԰�����ȡ�ʹ�����Դ���ļ��е���Ϣ
	
	static{	//����JDBCUtil���ʱ�����
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
