package com;

import java.io.PrintWriter;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//��½����
public class Login extends HttpServlet  {
	
	public void doGet(HttpServletRequest req,HttpServletResponse res){
		try{
			//��������
			res.setContentType("text/html;Charset=gbk");
			PrintWriter pw = res.getWriter();
			pw.println("<html>"); 
			pw.println("<body bgcolor=#CED3FF><center>"); 
			pw.println("<h1>��½����</h1>\n"+
					"<form action=LoginCL method=post>\n" +
					"�û���:<input type=text name=username><br>\n" +
					"&nbsp&nbsp����:<input type=password name=passwd><br>\n" +
					"<input type=checkbox name=keep value=2>��ס����<br>\n"+
					"<input type=submit value=Login><br>\n" +
					"</form>\n"+
					"</center></body>\n"+
					"</html>");
		}
		catch(Exception ex){ 
			ex.printStackTrace();
		}
	}
	
public void doPost(HttpServletRequest req,HttpServletResponse res){
		doGet(req,res);
	}

}
