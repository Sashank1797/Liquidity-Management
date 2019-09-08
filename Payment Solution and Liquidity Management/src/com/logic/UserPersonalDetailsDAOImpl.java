package com.logic;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.database.DatabaseConnection;

public class UserPersonalDetailsDAOImpl implements UserPersonalDetailsDAO {

	public boolean addUser(String name,String contact, String emailID) {
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
		isUpdated=true;
		}
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return userAdded;
	}
	public boolean deleteUser(int userID) {
		boolean userdeleted=false;
		return userdeleted;
	}
//	public UserPersonalDetails displayUserDetails(int userID) {
//		UserPersonalDetails userDetails;
//		return userDetails;
//	}
	
}
