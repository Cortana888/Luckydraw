package com;
//����һ�������ࣨ����users��<--->����UserBean

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.management.relation.RoleUnresolved;

public class UserBeanCL {
	private Connection ct=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private int pageCount=0;//���м�ҳ
		
	//��ѯ�����û�
	public ArrayList<UserBean> selectDateUser(){
		ArrayList<UserBean> al=new ArrayList<UserBean>();
		try{
			ConnDB cd=new ConnDB();
			ct=cd.getconn();
			ps=ct.prepareStatement("select * from users");
			rs = ps.executeQuery();		
			while(rs.next()){
				//��rs�еģ�ÿ����¼��װ��UserBean
				UserBean ub=new UserBean();
				ub.setUserId(rs.getInt(1));
				ub.setUserName(rs.getString(2));
				ub.setPasswd(rs.getString(3));
				ub.setPhone(rs.getString(4));
				ub.setGrade(rs.getInt(5));
				al.add(ub);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();
			return al;
		}
	}
	
	//����û�
	public boolean insertDateUser(String name,String phone,String passwd,String grade){
		boolean flag =false;
		try{
			ConnDB cd=new ConnDB();
			ct=cd.getconn();
			String sql="insert into users(userName,passwd,phone,grade)"
					+ "values('" +name+ "','" +passwd+ "','" + phone+ "',"+grade+")";
			ps=ct.prepareStatement(sql);
			int num=ps.executeUpdate();
			if(num==1){
				flag = true;
			}
			return flag;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return flag;
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			try{
				if(ct!=null){
					ct.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	}	
		
	//�޸��û�
	public boolean upDateUser(String id,String phone,String passwd,String grade){
		boolean flag =false;
		try{
			ConnDB cd=new ConnDB();
			ct=cd.getconn();
			String sql="update users set passwd='"+passwd+"',phone='"+phone+"',grade='"+grade+"' where userId='"+id+"'";
			ps=ct.prepareStatement(sql);
			int num=ps.executeUpdate();
			if(num==1){
				flag = true;
			}
			return flag;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return flag;
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			try{
				if(ct!=null){
					ct.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
	}	
	
	//ɾ���û�
	public boolean delUser(String id){
		boolean flag =false;
		try{
			ConnDB cd=new ConnDB();
			ct=cd.getconn();
			String sql="delete from users where userId='"+id+"'";
			ps=ct.prepareStatement(sql);
			int num=ps.executeUpdate();
			if(num==1){
				flag = true;
			}
			return flag;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return flag;
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			try{
				if(ct!=null){
					ct.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	//����pageCount
	public int getPageCount(){
		return this.pageCount;
	}
	//��ҳ��ʾ
	public ArrayList<UserBean> getResultByPage(int pageNow,int pageSize){
		int rowCount=0;//���м�����¼
		
		
		ArrayList<UserBean> al=new ArrayList<UserBean>();
		try{
			ConnDB cd=new ConnDB();
			ct=cd.getconn();
			ps=ct.prepareStatement("select count(*) from users");
			rs = ps.executeQuery();
			
			if(rs.next()){
				rowCount = rs.getInt(1);
				
				if(rowCount%pageSize==0){
					pageCount=rowCount/pageSize;
				}else{
					pageCount=rowCount/pageSize+1;
				}
			}
			/*
			ps=ct.prepareStatement("select * from users "
					+ "where userId not in "
					+ "(select userId from users order by userId limit"+pageSize*(pageNow-1)+")"
					+ "limit"+pageSize+"");
			*/
			ps=ct.prepareStatement("select * from users limit "+pageSize*(pageNow-1)+","+pageSize);
			rs=ps.executeQuery();
			while(rs.next()){
				//��rs�еģ�ÿ����¼��װ��UserBean
				UserBean ub=new UserBean();
				ub.setUserId(rs.getInt(1));
				ub.setUserName(rs.getString(2));
				ub.setPasswd(rs.getString(3));
				ub.setPhone(rs.getString(4));
				ub.setGrade(rs.getInt(5));
				al.add(ub);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();
			return al;
		}
		
		
		
}
	
	//��֤�û�
	public boolean checkUser(String u,String p){
		boolean flag=false;
		try{
			//�õ�����
			ConnDB cd=new ConnDB();
			ct=cd.getconn();
			ps=ct.prepareStatement("select passwd from users where username=? order by passwd limit 1 ");
			
			ps.setString(1, u);
			rs=ps.executeQuery();
			if(rs.next()){
				String dbPasswd=rs.getString(1);
				if(dbPasswd.equals(p)){
					flag=true;
				}
			}
			
			
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return flag;
	}
	
	//�ر���Դ
	public void close(){
		try{
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
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
