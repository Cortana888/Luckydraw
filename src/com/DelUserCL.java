package com;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//处理删除用户
public class DelUserCL extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res){
		try{
			//中文乱码
			res.setContentType("text/html;Charset=gbk");
			
			//调用userBeanCL的删除用户方法
			UserBeanCL ubc=new UserBeanCL();
			
			//接收从wel.java传递的id号
			String id=req.getParameter("userid");
			
			if(ubc.delUser(id)){
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
