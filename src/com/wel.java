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


//��ӭ����
public class wel extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res){
		Connection ct=null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String name="";
		String passwd="";
		try{
			//��session�еõ��û���
			HttpSession hs=req.getSession(true);
			String myName=(String) hs.getAttribute("uname");
			if(myName==null){
				//���session��û���û���Ϣ���ٿ�����û��cookie��Ϣ
				Cookie [] allCookies=req.getCookies();
				if(allCookies!=null){
					for(int i=0; i<allCookies.length; i++){
						Cookie temp=allCookies[i];
						if(temp.getName().equals("myname")){
							//�õ�cookieֵ
							name=temp.getValue();
						}
						else if(temp.getName().equals("mypasswd")){
							passwd=temp.getValue();
						}
					}
					if(!name.equals("")&&!passwd.equals("")){
						//��LoginCLȥ��֤
						res.sendRedirect("LoginCL?username="+name+"&passwd="+passwd);
					}
				}
				//���ص�½����
				res.sendRedirect("Login");
				return;
			}
		
			//��������
			res.setContentType("text/html;Charset=gbk");
			PrintWriter pw = res.getWriter();
			
			//��ҳ�Ĺ���
			int pageSize = 5;//һҳ������ʾ������¼
			int pageNow=1;//ϣ����ʾ�ڼ�ҳ
			int pageCount=0;//���м�ҳ
			
			//��̬����pageNow
			String sPageNow=req.getParameter("pageNow");
			if(sPageNow!=null){
				pageNow=Integer.parseInt(sPageNow);
			}
			
			//����UserBeanCL
			UserBeanCL ubc=new UserBeanCL();
			ArrayList<UserBean> al=ubc.getResultByPage(pageNow, pageSize);
			
			pw.println("<body bgcolor=#CED3FF>");
			
			
			pw.println("<span align =left margin-left=200px>��ӭ��:"+myName+"!<img src=image/computer.jpg height=30px width=30px><a href=Login>ע��</a>&nbsp&nbsp<a href=Main>����</a></span>");
			pw.println("<hr><center>");
			pw.println("<h1>�������</h1>");
			pw.print("<table border=1>\n"+
					 "<tr bgcolor=pink><th>id</th><th>name</th><th>passwd</th><th>phone</th><th>grade</th><th>�޸��û�</th><th>ɾ���û�</th></tr>\n");
			//������ɫ����
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
						"<td><a href=Update?uId="+ub.getUserId()+"&uName="+ub.getUserName()+"&uPass="+ub.getPasswd()+"&uPhone="+ub.getPhone()+"&uGrade="+ub.getGrade()+">�޸��û�</a></td>\n"+
						"<td><a href=DelUserCL?userid="+ub.getUserId()+" onclick='return confirm(\"ȷ��ɾ���û�?\")'>ɾ���û�</a></td>\n"+
						"</tr>\n");
			}
			pw.print("</table>\n");
			/*
			pw.println("<script>"+
						"function isdelete(){"+
						"if(confirm('ȷ��ɾ�����û���')==true)"+
						"return true;"+
						"else return false;"+
						"}"+
						"</script>");
			*/
			if(pageNow!=1){
				pw.println("<a href=wel?pageNow="+(pageNow-1)+">��һҳ</a>");
			}
			pageCount=ubc.getPageCount();
			//��ʾ������
			for(int i=pageNow; i<=pageNow+4&&i<=pageCount; i++){
				pw.println("<a href=wel?pageNow="+i+">"+i+"</a>");
			}
			
			if(pageNow!=pageCount){
				pw.println("<a href=wel?pageNow="+(pageNow+1)+">��һҳ</a>");
			}
			
			//ָ����ת��ĳҳ
			pw.println("<form action=wel>");
			pw.println("��ת��:<input type=text name=pageNow>");
			pw.println("<input type=submit value=go");
			pw.println("</form><br>");
			//pw.println("����ip="+req.getRemoteAddr()+"<br>");
			//pw.println("���Ļ�����="+req.getRemoteHost()+"<br>");
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
