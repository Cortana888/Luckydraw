package com;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//����ɾ���û�
public class DelUserCL extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res){
		try{
			//��������
			res.setContentType("text/html;Charset=gbk");
			
			//����userBeanCL��ɾ���û�����
			UserBeanCL ubc=new UserBeanCL();
			
			//���մ�wel.java���ݵ�id��
			String id=req.getParameter("userid");
			
			if(ubc.delUser(id)){
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
