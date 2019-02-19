package com.douzon.guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzon.guestbook.vo.GuestbookVo;

public class GuestbookDao {
	public static Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		
		Connection conn=null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url="jdbc:mysql://localhost:3306/webdb";
			conn=DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}

	public List<GuestbookVo> getList() {
		// TODO Auto-generated method stub
		List<GuestbookVo> list=new ArrayList<GuestbookVo>();
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			
			String sql=" select no, name, message, reg_date from guestbook order by no desc";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				long no=rs.getLong(1);
				String name=rs.getString(2);
				String message=rs.getString(3);
				String reg_date=rs.getString(4);
				
				GuestbookVo vo=new GuestbookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setMessage(message);
				vo.setReg_date(reg_date);
				
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public boolean insert(GuestbookVo vo) {
		// TODO Auto-generated method stub
		boolean result=false;
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=getConnection();
			
			String sql="  insert into guestbook " + 
					" value(null,?, ?, ?,sysdate());";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPasswd());
			pstmt.setString(3, vo.getMessage());
			
			int count=pstmt.executeUpdate();
			
			result=count==1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public GuestbookVo select(int no) {
		// TODO Auto-generated method stub
		GuestbookVo vo=new GuestbookVo();
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			
			String sql=" select * from guestbook where no=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				String name=rs.getString(2);
				String pw=rs.getString(3);
				String message=rs.getString(4);
				String reg_date=rs.getString(5);
				
				vo.setNo(no);
				vo.setName(name);
				vo.setPasswd(pw);
				vo.setMessage(message);
				vo.setReg_date(reg_date);
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return vo;
	}

	public boolean delete(int no) {
		// TODO Auto-generated method stub
		boolean result=false;
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=getConnection();
			
			String sql="  delete from guestbook " + 
					" where no=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			int count=pstmt.executeUpdate();
			
			result=count==1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}
