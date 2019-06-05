package com;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertdateCL extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res){
		try{
			//中文乱码
			res.setContentType("text/html;Charset=gbk");
			
			//调用userBeanCL的创建用户方法
			UserBeanCL ubc=new UserBeanCL();
			
			
			if(ubc.insertDateUser(req.getParameter("uName"),req.getParameter("uPhone"),req.getParameter("uPasswd"),req.getParameter("uGrade"))){
				//创建成功
				res.sendRedirect("InsertOK");
			}
			else{
				//失败
				res.sendRedirect("InsertErr");
			}
		}
		catch(Exception ex){ 
			ex.printStackTrace();
		}
	}
	
public void doPost(HttpServletRequest req,HttpServletResponse res){
		doGet(req,res);
	}
}
