package com.logic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dao.UserLoginDAO;
import com.database.DatabaseConnection;

public class UserLoginDAOImpl implements UserLoginDAO {

	public boolean addUserCredentials(String username,String password) {
		DatabaseConnection Connection = new DatabaseConnection();
		boolean login=false;
		String ADD_LOGIN="insert into login values(?,?)";
		PreparedStatement ps;
		try {
		ps = Connection.openConnection().prepareStatement(ADD_LOGIN);
		ps.setString(1, username);
		ps.setString(2, password);
		int rows=ps.executeUpdate();
		if(rows>0) {
		login=true;
		}
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return login;
	}
	public int authenticateUser(String username,String password) {
		DatabaseConnection Connection = new DatabaseConnection();
		int userId=0;
		String SQL_LOGIN = "select * from login where username=? and password=?";
		PreparedStatement ps;
		try {
			ps = Connection.openConnection().prepareStatement(SQL_LOGIN);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet set = ps.executeQuery();
			System.out.println(set);
			if(set.next()) {
				userId = set.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return userId;
	}
	public boolean updatePassword(String userName,String currentPassword,String newPassword) {
		boolean update=false;
		String AUTHENTICATE_USER="SELECT PASSWORD FROM LOGIN WHERE USERNAME=?";
		String UPDATE_PASSWORD="UPDATE LOGIN SET PASSWORD=? WHERE USERNAME=?";
		DatabaseConnection Connection=new DatabaseConnection();
		PreparedStatement ps;
		String password=null;
		try {
			ps=Connection.openConnection().prepareStatement(UPDATE_PASSWORD);
			ps.setString(1, userName);
			//ps.setString(2, currentPassword);
			ResultSet set=ps.executeQuery();
			if(set.next()) {
					password=set.getString("password");
			}
			if(password.equals(currentPassword)) {
				ps=Connection.openConnection().prepareStatement(UPDATE_PASSWORD);
				ps.setString(1, newPassword);
				ps.setString(2, userName);
				update=true;
			}
			else {
				System.out.println("error");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return update;
	}
}
