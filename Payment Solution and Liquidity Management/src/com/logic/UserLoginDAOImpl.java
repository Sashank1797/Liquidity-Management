package com.logic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import com.dao.UserLoginDAO;
import com.database.DatabaseConnection;

@Path("/login")
public class UserLoginDAOImpl implements UserLoginDAO {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/signup")
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
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/login")
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
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/changepassword")
	public JSONObject updatePassword(String userName,String currentPassword,String newPassword) {
		JSONObject response = new JSONObject();
		String UPDATE_PASSWORD="UPDATE LOGIN SET PASSWORD=? WHERE USERNAME=? AND PASSWORD=?";
		DatabaseConnection Connection=new DatabaseConnection();
		PreparedStatement ps;
		try {
			ps=Connection.openConnection().prepareStatement(UPDATE_PASSWORD);
			ps.setString(1, newPassword);
			ps.setString(2, userName);
			ps.setString(3, currentPassword);
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
// 		return update;
	}
}
