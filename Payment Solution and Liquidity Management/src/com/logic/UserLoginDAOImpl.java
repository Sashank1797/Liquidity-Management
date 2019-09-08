package com.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
	public int authenticateUser(String userName,String password) {
		DatabaseConnection Connection = new DatabaseConnection();
		int userId=0;
		String SQL_LOGIN = "select * from login where username=? and password=?";
		try {
			ps = Connection.openConnection().prepareStatement(SQL_LOGIN);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet set = ps.executeQuery();
			if(set) {
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
		return update;
	}
}
