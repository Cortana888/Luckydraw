package com;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//主界面
public class Main extends HttpServlet  {
	
	public void doGet(HttpServletRequest req,HttpServletResponse res){
		try{
			//中文乱码
			res.setContentType("text/html;Charset=gbk");
			PrintWriter pw = res.getWriter();
			pw.println("<html>"); 
			pw.println("<img src=image/girl.jpg height=200px width=400px><hr>");
			pw.println("<body bgcolor=#CED3FF><center>"); 
			pw.println("<h1>主界面</h1>");
			pw.println("<a href=wel>管理用户</a><br>");
			pw.println("<a href=Insertdate>添加用户</a><br>");
			pw.println("<a href=Luckydraw>用户抽奖</a><br>");
			pw.println("<a href=Login>安全退出</a><br>");
			
			pw.println("</body>");
			pw.println("</html>");
		}
		catch(Exception ex){ 
			ex.printStackTrace();
		}
	}
	
public void doPost(HttpServletRequest req,HttpServletResponse res){
		doGet(req,res);
	}

}

