package com;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateCL extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res){
		try{
			//��������
			res.setContentType("text/html;Charset=gbk");
			
			//����userBeanCL��ɾ���û�����
			UserBeanCL ubc=new UserBeanCL();
			
			
			if(ubc.upDateUser(req.getParameter("uId"),req.getParameter("newPhone"),req.getParameter("newPasswd"),req.getParameter("newGrade"))){
				//ɾ���ɹ�
				res.sendRedirect("OK");
			}
			else{
				//ʧ��
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
