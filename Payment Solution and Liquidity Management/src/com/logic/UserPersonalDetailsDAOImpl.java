package com.logic;

import java.sql.PreparedStatement;
import org.json.simple.JSONObject;
import java.sql.SQLException;
import com.dao.UserPersonalDetailsDAO;
import com.database.DatabaseConnection;

public class UserPersonalDetailsDAOImpl implements UserPersonalDetailsDAO {

	public JSONObject addUser(String name,String contact, String emailId) {
		JSONObject response = new JSONObject();
		DatabaseConnection Connection = new DatabaseConnection();
		String ADD_USER="insert into users values(?,?,?)";
		PreparedStatement ps;
		try {
		ps = Connection.openConnection().prepareStatement(ADD_USER);
		ps.setString(1, name);
		ps.setString(2, contact);
		ps.setString(3, emailId);
		int rows=ps.executeUpdate();
		if(rows>0) {
			response.put("error", false);
			response.put("message", "success");
			response.put("data", "");
		}
		else {
			response.put("error", true);
			response.put("message", "Signup failed");
			response.put("data", "");
		}
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return response;
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
  	JSONObject response = new JSONObject();
		return response;
// 		return userdeleted;
	}

	public JSONObject displayUser(int userID) {
		// TODO Auto-generated method stub
		JSONObject response = new JSONObject();
		return response;
	}
	
}
