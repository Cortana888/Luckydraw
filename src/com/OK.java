package com;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//�����ɹ�����
public class OK extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res){
		try{
			//��������
			res.setContentType("text/html;Charset=gbk");
			PrintWriter pw = res.getWriter();
			pw.println("<html>"); 
			pw.println("<body bgcolor=#CED3FF><center>"); 
			pw.println("<h1>��ϲ���޸ĳɹ���</h1>");
			pw.println("<a href=Main>����������</a>&nbsp;&nbsp;&nbsp;<a href=wel>�����޸�</a>");
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
