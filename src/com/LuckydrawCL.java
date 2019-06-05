package com;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LuckydrawCL extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res){
		ArrayList<UserBean> al=new ArrayList<UserBean>();
		HashSet<UserBean> hs = new HashSet<UserBean>();
		int totalNum = 0;//����齱������
		int awardCount = 0;//��Ʒ����
		try{
			//��������
			res.setContentType("text/html;Charset=gbk");
			//����userBeanCL�Ĵ����û�����
			UserBeanCL ubc=new UserBeanCL();
			//���û������Ա����Ϣ
			al = ubc.selectDateUser();
			//Ա��������
			totalNum = al.size();
			awardCount =Integer.parseInt(req.getParameter("awardCount"));
			while(hs.size()<awardCount){
				 int ran = (int) (Math.random()*(totalNum)); 
				 hs.add(al.get(ran));
			}
			
			res.setContentType("text/html;charset=gbk"); //2
			PrintWriter pw = res.getWriter();
			pw.println("<html>"); 
			pw.println("<body bgcolor=#CED3FF><center>"); 
			pw.println("<h1>��ϲ���н�����</h1>");
			pw.println("<p>�н�����:</p>");
			for(UserBean user:hs){
				String phoneNumber = user.getPhone();
				phoneNumber = phoneNumber.replaceAll("(\\d{3})\\d{4}(\\d)","$1****$2");
				pw.println("<p>"+user.getUserName()+"   "+phoneNumber+"</p>");
			}
			pw.println("<p>��ϲ�����û����"+req.getParameter("awardName")+"һ����</p>");
			pw.println("<a href=Main>����������</a>&nbsp;&nbsp;&nbsp;<a href=Luckydraw>�����齱</a>");
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
