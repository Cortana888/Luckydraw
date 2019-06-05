package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.crypto.provider.RC2Parameters;

//��½����
public class LoginCL extends HttpServlet  {
	UserBeanCL ubc=null;
	public void doGet(HttpServletRequest req,HttpServletResponse res){
		
		try{
			//�����û���������
			String u=req.getParameter("username");
			String p=req.getParameter("passwd"); 
	        
			//����UserBeanCL
			ubc=new UserBeanCL();
			
	        //��֤
			if(ubc.checkUser(u,p)){
			
					String keep=req.getParameter("keep");
					if(keep!=null){
						//���û������뱣���ڿͻ���
						//����
						Cookie name=new Cookie("myname",u);
						Cookie pass=new Cookie("mypasswd", p);
						//����ʱ��
						name.setMaxAge(7*24*3600);
						pass.setMaxAge(7*24*3600);
						//��д���ͻ���
						res.addCookie(name);
						res.addCookie(pass);
					}
					//���û���Ϣ����session
					HttpSession hs =req.getSession(true);
					hs.setMaxInactiveInterval(60);
					hs.setAttribute("uname", u);
					res.sendRedirect("Main");
				}	
			else{
				//�û������������
				res.sendRedirect("Login");
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}finally {
			ubc.close();
		}
	}
	
public void doPost(HttpServletRequest req,HttpServletResponse res){
		doGet(req,res);
	}

}
