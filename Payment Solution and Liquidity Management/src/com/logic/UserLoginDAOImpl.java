package com.logic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.simple.JSONObject;
import com.dao.UserLoginDAO;
import com.database.DatabaseConnection;

public class UserLoginDAOImpl implements UserLoginDAO {

	public JSONObject addUserCredentials(String username,String password) {
		JSONObject response = new JSONObject();
		DatabaseConnection Connection = new DatabaseConnection();
		String ADD_LOGIN="insert into login values(?,?)";
		PreparedStatement ps;
		try {
		ps = Connection.openConnection().prepareStatement(ADD_LOGIN);
		ps.setString(1, username);
		ps.setString(2, password);
		int rows=ps.executeUpdate();
		if(rows>0) {
			response.put("error", false);
			response.put("message", "success");
			response.put("data", "");
		}
		else {
			System.out.println("Signup failed");
			response.put("error", true);
			response.put("message", "signup failed");
			response.put("data", "");
		}
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return response;
	}
	public JSONObject authenticateUser(String username,String password) {
		JSONObject response = new JSONObject();
		DatabaseConnection Connection = new DatabaseConnection();
		String SQL_LOGIN = "select * from login where username=? and password=?";
		PreparedStatement ps;
		try {
			ps = Connection.openConnection().prepareStatement(SQL_LOGIN);
			ps.setString(1, username);
			ps.setString(2, password);
			System.out.println("Executing Query");
			ResultSet set = ps.executeQuery();
			if(set.next()) {
				JSONObject login = new JSONObject();
				login.put("userId", set.getInt(1));
				login.put("username", set.getString(2));
				response.put("error", false);
				response.put("message", "success");
				response.put("data", login);
//				System.out.println("SET: "+set.getString(1)+set.getString("username")+set.getString(3));
				System.out.println(response);
			}
			else {
				System.out.println("Authentication failed");
				response.put("error", true);
				response.put("message", "authentication failed");
				response.put("data", "");
				System.out.println(response);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return response;
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
    JSONObject response = new JSONObject();
		return response;
// 		return update;
	}
}
