package com;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 添加用户
 * @author MA
 *
 */
public class Insertdate extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res){
		try{
			//中文乱码
			res.setContentType("text/html;Charset=gbk");
			PrintWriter pw = res.getWriter();
			pw.println("<html>"); 
			pw.println("<body bgcolor=#CED3FF><center>"); 
			pw.println("<h1>添加用户界面</h1>");
			pw.println("<form action=InsertdateCL>");
			pw.println("<table border=1>");
			
			pw.println("<tr><td>name</td><td><input type=text name=uName></td></tr>");
			pw.println("<tr><td>passwd</td><td><input type=text name=uPasswd></td></tr>");
			pw.println("<tr><td>phone</td><td><input type=text name=uPhone></td></tr>");
			pw.println("<tr><td>grade</td><td><input type=text name=uGrade></td></tr>");
			pw.println("<tr><td><input type=submit value=提交></td>&nbsp&nbsp");
			pw.println("<td><a href=Main>返回</a></td></tr>");
			pw.println("</table></form>");
			pw.println("</center></body>");		
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
