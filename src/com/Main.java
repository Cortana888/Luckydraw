package com;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//������
public class Main extends HttpServlet  {
	
	public void doGet(HttpServletRequest req,HttpServletResponse res){
		try{
			//��������
			res.setContentType("text/html;Charset=gbk");
			PrintWriter pw = res.getWriter();
			pw.println("<html>"); 
			pw.println("<img src=image/girl.jpg height=200px width=400px><hr>");
			pw.println("<body bgcolor=#CED3FF><center>"); 
			pw.println("<h1>������</h1>");
			pw.println("<a href=wel>�����û�</a><br>");
			pw.println("<a href=Insertdate>����û�</a><br>");
			pw.println("<a href=Luckydraw>�û��齱</a><br>");
			pw.println("<a href=Login>��ȫ�˳�</a><br>");
			
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

