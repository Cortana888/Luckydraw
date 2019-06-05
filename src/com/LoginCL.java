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

//登陆处理
public class LoginCL extends HttpServlet  {
	UserBeanCL ubc=null;
	public void doGet(HttpServletRequest req,HttpServletResponse res){
		
		try{
			//接收用户名和密码
			String u=req.getParameter("username");
			String p=req.getParameter("passwd"); 
	        
			//调用UserBeanCL
			ubc=new UserBeanCL();
			
	        //验证
			if(ubc.checkUser(u,p)){
			
					String keep=req.getParameter("keep");
					if(keep!=null){
						//将用户名密码保存在客户端
						//创建
						Cookie name=new Cookie("myname",u);
						Cookie pass=new Cookie("mypasswd", p);
						//设置时间
						name.setMaxAge(7*24*3600);
						pass.setMaxAge(7*24*3600);
						//回写到客户端
						res.addCookie(name);
						res.addCookie(pass);
					}
					//将用户信息存入session
					HttpSession hs =req.getSession(true);
					hs.setMaxInactiveInterval(60);
					hs.setAttribute("uname", u);
					res.sendRedirect("Main");
				}	
			else{
				//用户名或密码错误
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
