package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Luckydraw extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res){
		
		try {
			res.setContentType("text/html;charset=gbk"); 
			PrintWriter pw = res.getWriter();
			pw.println("<html>"); 
			pw.println("<body bgcolor=#CED3FF><center>"); 
			pw.println("<h1>�齱����</h1>");
			pw.println("<form action=LuckydrawCL>");
			pw.println("<table border=1>");
			
			pw.println("<tr><td>��Ʒ���ƣ�</td><td><input type=text name=awardName></td></tr>");
			pw.println("<tr><td>��Ʒ������</td><td><input type=text name=awardCount></td></tr>");
			pw.println("<tr><td><input type=submit value=�ύ></td>&nbsp&nbsp");
			pw.println("<td><a href=Main>����</a></td></tr>");
			pw.println("</table></form>");
			pw.println("</center></body>");		
			pw.println("</html>");		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res){
		doGet(req,res);
	}
}
