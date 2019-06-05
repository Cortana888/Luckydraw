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
		int totalNum = 0;//参与抽奖总人数
		int awardCount = 0;//奖品数量
		try{
			//中文乱码
			res.setContentType("text/html;Charset=gbk");
			//调用userBeanCL的创建用户方法
			UserBeanCL ubc=new UserBeanCL();
			//调用获得所有员工信息
			al = ubc.selectDateUser();
			//员工总人数
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
			pw.println("<h1>恭喜你中奖啦！</h1>");
			pw.println("<p>中奖名单:</p>");
			for(UserBean user:hs){
				String phoneNumber = user.getPhone();
				phoneNumber = phoneNumber.replaceAll("(\\d{3})\\d{4}(\\d)","$1****$2");
				pw.println("<p>"+user.getUserName()+"   "+phoneNumber+"</p>");
			}
			pw.println("<p>恭喜以上用户获得"+req.getParameter("awardName")+"一个！</p>");
			pw.println("<a href=Main>返回主界面</a>&nbsp;&nbsp;&nbsp;<a href=Luckydraw>继续抽奖</a>");
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
