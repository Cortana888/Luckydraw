package com;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertdateCL extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res){
		try{
			//��������
			res.setContentType("text/html;Charset=gbk");
			
			//����userBeanCL�Ĵ����û�����
			UserBeanCL ubc=new UserBeanCL();
			
			
			if(ubc.insertDateUser(req.getParameter("uName"),req.getParameter("uPhone"),req.getParameter("uPasswd"),req.getParameter("uGrade"))){
				//�����ɹ�
				res.sendRedirect("InsertOK");
			}
			else{
				//ʧ��
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
