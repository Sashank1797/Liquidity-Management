package com.logic;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.glassfish.jersey.server.ResourceConfig;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
	
import com.dao.UserAccountDetailsDAO;
import com.database.DatabaseConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Path("/accountinformation")
public class UserAccountDetailsDAOImpl implements UserAccountDetailsDAO{

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/update")
	public JSONObject updateAccountBalance(String accountNo,double balance) {
		JSONObject response = new JSONObject();
		String BALANCE_UPDATE ="update accounts set balance=? where account_no={SELECT BALANCE FROM ACCOUNTS WHERE ACCOUNT_NO=?}"; 
		
		PreparedStatement ps;
		try {
			DatabaseConnection Connection=new DatabaseConnection();
			ps=Connection.openConnection().prepareStatement(BALANCE_UPDATE);
			ps.setString(1, accountNo);
			ResultSet set=ps.executeQuery();
			if(set.next()) {
				response.put("error", false);
				response.put("message", "success");
				response.put("data", "");
        return response;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.put("error", true);
    response.put("message", "Balance not updated");
    response.put("data", "");
    return response;
	}

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/accountdata")
	public JSONObject getAccountBalanceandNumberbyID(int user_ID) {
	// TODO Auto-generated method stub
	JSONObject response=new JSONObject();
	String GET_BY_ID="SELECT BALANCE, ACCOUNT_NO FROM FROM ACCOUNTS WHERE USER_ID=?";
	DatabaseConnection Connection=new DatabaseConnection();
	PreparedStatement ps;
	try {
		ps=Connection.openConnection().prepareStatement(GET_BY_ID);
		ResultSet set=ps.executeQuery();
		if(set.next()) {
			response.put("error", false);
			response.put("message", "success");
	    	JSONArray accountDetailsArray = new JSONArray();
			do {
			JSONObject accountDetails=new JSONObject();
			accountDetails.put("account_no", set.getString(3));
			accountDetails.put("balance",set.getDouble(5));
			accountDetailsArray.add(accountDetails);
			}while(set.next());
			response.put("data", accountDetailsArray);
		}
		else {
			response.put("error", true);
			response.put("message", "Failed to get data");
			response.put("data", "  ");
			
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
}
