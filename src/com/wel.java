package com;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//欢迎界面
public class wel extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res){
		Connection ct=null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String name="";
		String passwd="";
		try{
			//从session中得到用户名
			HttpSession hs=req.getSession(true);
			String myName=(String) hs.getAttribute("uname");
			if(myName==null){
				//如果session中没有用户信息，再看看有没有cookie信息
				Cookie [] allCookies=req.getCookies();
				if(allCookies!=null){
					for(int i=0; i<allCookies.length; i++){
						Cookie temp=allCookies[i];
						if(temp.getName().equals("myname")){
							//得到cookie值
							name=temp.getValue();
						}
						else if(temp.getName().equals("mypasswd")){
							passwd=temp.getValue();
						}
					}
					if(!name.equals("")&&!passwd.equals("")){
						//到LoginCL去验证
						res.sendRedirect("LoginCL?username="+name+"&passwd="+passwd);
					}
				}
				//返回登陆界面
				res.sendRedirect("Login");
				return;
			}
		
			//中文乱码
			res.setContentType("text/html;Charset=gbk");
			PrintWriter pw = res.getWriter();
			
			//分页的功能
			int pageSize = 5;//一页可以显示几条记录
			int pageNow=1;//希望显示第几页
			int pageCount=0;//共有几页
			
			//动态接收pageNow
			String sPageNow=req.getParameter("pageNow");
			if(sPageNow!=null){
				pageNow=Integer.parseInt(sPageNow);
			}
			
			//调用UserBeanCL
			UserBeanCL ubc=new UserBeanCL();
			ArrayList<UserBean> al=ubc.getResultByPage(pageNow, pageSize);
			
			pw.println("<body bgcolor=#CED3FF>");
			
			
			pw.println("<span align =left margin-left=200px>欢迎你:"+myName+"!<img src=image/computer.jpg height=30px width=30px><a href=Login>注销</a>&nbsp&nbsp<a href=Main>返回</a></span>");
			pw.println("<hr><center>");
			pw.println("<h1>管理界面</h1>");
			pw.print("<table border=1>\n"+
					 "<tr bgcolor=pink><th>id</th><th>name</th><th>passwd</th><th>phone</th><th>grade</th><th>修改用户</th><th>删除用户</th></tr>\n");
			//定义颜色数组
			String [] mycol={"silver","pink"};
			for(int i=0;i<al.size();i++){
				UserBean ub=(UserBean)al.get(i);
				pw.print( 
						"<tr bgcolor="+mycol[i%2]+">\n"+
						"<td>"+ub.getUserId()+"</td>\n"+
						"<td>"+ub.getUserName()+"</td>\n"+
						"<td>"+ub.getPasswd()+"</td>\n"+
						"<td>"+ub.getPhone()+"</td>\n"+
						"<td>"+ub.getGrade()+"</td>\n"+
						"<td><a href=Update?uId="+ub.getUserId()+"&uName="+ub.getUserName()+"&uPass="+ub.getPasswd()+"&uPhone="+ub.getPhone()+"&uGrade="+ub.getGrade()+">修改用户</a></td>\n"+
						"<td><a href=DelUserCL?userid="+ub.getUserId()+" onclick='return confirm(\"确认删除用户?\")'>删除用户</a></td>\n"+
						"</tr>\n");
			}
			pw.print("</table>\n");
			/*
			pw.println("<script>"+
						"function isdelete(){"+
						"if(confirm('确认删除该用户？')==true)"+
						"return true;"+
						"else return false;"+
						"}"+
						"</script>");
			*/
			if(pageNow!=1){
				pw.println("<a href=wel?pageNow="+(pageNow-1)+">上一页</a>");
			}
			pageCount=ubc.getPageCount();
			//显示超链接
			for(int i=pageNow; i<=pageNow+4&&i<=pageCount; i++){
				pw.println("<a href=wel?pageNow="+i+">"+i+"</a>");
			}
			
			if(pageNow!=pageCount){
				pw.println("<a href=wel?pageNow="+(pageNow+1)+">下一页</a>");
			}
			
			//指定跳转到某页
			pw.println("<form action=wel>");
			pw.println("跳转到:<input type=text name=pageNow>");
			pw.println("<input type=submit value=go");
			pw.println("</form><br>");
			//pw.println("您的ip="+req.getRemoteAddr()+"<br>");
			//pw.println("您的机器名="+req.getRemoteHost()+"<br>");
			pw.println("</center></body>");
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ct!=null){
				try {
					ct.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
		}
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res){
		doGet(req, res);
	}
}
