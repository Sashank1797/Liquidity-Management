package com.logic;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dao.UserPersonalDetailsDAO;
import com.database.DatabaseConnection;
import com.pojo.UserPersonalDetails;

public class UserPersonalDetailsDAOImpl implements UserPersonalDetailsDAO {

	public boolean addUser(String name,String contact, String emailId) {
		DatabaseConnection Connection = new DatabaseConnection();
		boolean userAdded=false;
		String ADD_USER="insert into users values(?,?,?)";
		PreparedStatement ps;
		try {
		ps = Connection.openConnection().prepareStatement(ADD_USER);
		ps.setString(1, name);
		ps.setString(2, contact);
		ps.setString(3, emailId);
		int rows=ps.executeUpdate();
		if(rows>0) {
		userAdded=true;
		}
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return userAdded;
	}
	public boolean deleteUser(int userID) {
		boolean userdeleted=false;
		DatabaseConnection Connection = new DatabaseConnection();
		String DELETE_USER="DELETE FROM ";"insert into users values(?,?,?)";
		PreparedStatement ps;
		try {
		ps = Connection.openConnection().prepareStatement(ADD_USER);
		ps.setString(1, name);
		ps.setString(2, contact);
		ps.setString(3, emailId);
		int rows=ps.executeUpdate();
		if(rows>0) {
		userAdded=true;
		}
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return userdeleted;
	}

	public UserPersonalDetails displayUser(int userID) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
