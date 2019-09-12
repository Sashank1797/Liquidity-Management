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
import com.sun.org.apache.xpath.internal.operations.And;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Path("/accountinformation")
public class UserAccountDetailsDAOImpl implements UserAccountDetailsDAO{

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/update")
	public JSONObject updateAccountBalance(String data) throws ParseException{
		//parameters - String currency,double balance
		JSONObject response = new JSONObject();
		JSONParser parser=new JSONParser();
		JSONObject request=(JSONObject) parser.parse(data);
		String currency=(String)request.get("currency");
		double balance=(double)request.get("balance");
		String BALANCE_UPDATE ="update accounts set balance=? where currency=?"; 
		String other_currency1=null;
		String other_currency2=null;
		switch(currency) {
		case "GBP":
			other_currency1="EUR";
			other_currency2="USD";
			break;
		case "EUR":
			other_currency1="GBP";
			other_currency2="USD";
			break;
		case "USD":
			other_currency1="EUR";
			other_currency2="USD";
			break;
		}
		PreparedStatement ps;
		try {
			DatabaseConnection Connection=new DatabaseConnection();
			ps=Connection.openConnection().prepareStatement(BALANCE_UPDATE);
			ps.setDouble(1, balance);
			ps.setString(2, currency);
			int row1=ps.executeUpdate();
			ps.setDouble(1, 0);
			ps.setString(2, other_currency1);
			int row2=ps.executeUpdate();
			ps.setDouble(1, 0);
			ps.setString(2, other_currency2);
			int row3=ps.executeUpdate();
			if(row1>0 && row2>0 && row3>0) {
				response.put("error", false);
				response.put("message", "success");
				response.put("data", "");
			}
			else {
				response.put("error", true);
			    response.put("message", "Balance not updated");
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
	@Path("/accountdata")
	public JSONObject getAccountBalanceandNumberbyID(String data) throws ParseException{
	// TODO Auto-generated method stub
		//int user_ID
		JSONObject response = new JSONObject();
		JSONParser parser=new JSONParser();
		JSONObject request=(JSONObject) parser.parse(data);
		int user_ID=(Integer) request.get("user_id");
	String GET_BY_ID="SELECT BALANCE, ACCOUNT_NO FROM ACCOUNTS WHERE USER_ID=?";
	DatabaseConnection Connection=new DatabaseConnection();
	PreparedStatement ps;
	try {
		ps=Connection.openConnection().prepareStatement(GET_BY_ID);
		ps.setInt(1, user_ID);
		ResultSet set=ps.executeQuery();
		if(set.next()) {
			response.put("error", false);
			response.put("message", "success");
	    	JSONArray accountDetailsArray = new JSONArray();
			do {
			JSONObject accountDetails=new JSONObject();
			accountDetails.put("account_no", set.getString(2));
			accountDetails.put("balance",set.getDouble(1));
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
	return response;
}



	
}
