package com;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateCL extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res){
		try{
			//中文乱码
			res.setContentType("text/html;Charset=gbk");
			
			//调用userBeanCL的删除用户方法
			UserBeanCL ubc=new UserBeanCL();
			
			
			if(ubc.upDateUser(req.getParameter("uId"),req.getParameter("newPhone"),req.getParameter("newPasswd"),req.getParameter("newGrade"))){
				//删除成功
				res.sendRedirect("OK");
			}
			else{
				//失败
				res.sendRedirect("Err");
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
