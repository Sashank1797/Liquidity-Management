package com.logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLoginDAOImpl {
	public Connection getConnection() {
		Connection conn=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver loaded successfully");
			//jdbc:data_base:install_server:port/database
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","abcd123");
			System.out.println("Connection obtained");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	public boolean authenticateUser(String userName,String password) {
		boolean authenticate=false;
		Connection conn=getConnection();
		String find_data="select * from login";
		try {
			PreparedStatement ps=conn.prepareStatement(find_data);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				System.out.println("username: "+rs.getString(2)+" password: "+rs.getString(3));
				if(rs.getString(2).equals(userName) && rs.getString(3).equals(password))
					return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return authenticate;
	}
	public boolean updatePassword(String userName,String currentPassword,String newPassword) {
		boolean update=false;
		Connection conn=getConnection();
		String find_data="select * from login where username=?";
		try {
			PreparedStatement ps=conn.prepareStatement(find_data);
			ps.setString(1, userName);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				if(rs.getString(3).equals(currentPassword)) {
					System.out.println("Current password: "+rs.getString(3));
					find_data="update  login set password=? where username=?";
					ps=conn.prepareStatement(find_data);
					ps.setString(1, newPassword);
					ps.setString(2, userName);
					ps.executeQuery();
					return true;
				}
					
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return update;
	}
	public static void main(String[] args) {
		UserLoginDAOImpl dao=new UserLoginDAOImpl();
		UserAccountDetailsDAOImpl user=new UserAccountDetailsDAOImpl();
		boolean result=false;
//		 result=dao.authenticateUser("Kartik", "kartik123");
//		if(result)
//		System.out.println("Account logged in");
//		else
//			System.out.println("Account not logged in");
//		result=dao.updatePassword("Ryan", "ryan123", "ryan456");
//		if(result)
//			System.out.println("Account updated");
//			else
//				System.out.println("Account not updated");
	
	}
}
