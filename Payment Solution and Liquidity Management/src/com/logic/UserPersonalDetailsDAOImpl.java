package com.logic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.simple.JSONObject;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dao.UserPersonalDetailsDAO;
import com.database.DatabaseConnection;

@Path("/userinformation")
public class UserPersonalDetailsDAOImpl implements UserPersonalDetailsDAO {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/signup")
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


	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/showdata")
	public JSONObject displayUser(int userID) {
		// TODO Auto-generated method stub
		JSONObject response = new JSONObject();
		DatabaseConnection Connection=new DatabaseConnection();
		PreparedStatement ps;
		String DISPLAY_USER="SELECT * FROM USERS WHERE ID=?";
		try {
			ps=Connection.openConnection().prepareStatement(DISPLAY_USER);
			ps.setInt(1, userID);
			ResultSet set=ps.executeQuery();
			if(set.next()) {
				response.put("error", true);
				response.put("message", "Success");
				JSONObject user=new JSONObject();
				user.put("id",set.getInt(1));
				user.put("name", set.getString(2));
				user.put("contact_no", set.getString(3));
				user.put("email_id", set.getString(4));
				response.put("data", user);
			}
			else {
				response.put("error", false);
				response.put("message", "Can't be displayed!");
				response.put("data"," " );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
}
