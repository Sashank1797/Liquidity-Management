package com.dao;
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

import com.database.DatabaseConnection;
import com.logic.UserTransactionDetailsDAOImpl;
import com.pojo.UserTransactionDetails;
@Path("/netBalance")
public class getNetBalance {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/usd")
	public JSONObject usdBalance() {
		JSONObject obj=new JSONObject();
		
		String GET_BALANCE_by_CURRENCY="SELECT SUM(AMOUNT) FROM TRANSACTIONS WHERE CURRENCY=? ";
		DatabaseConnection connection= new DatabaseConnection();
		double balance_USD=0.0d;
		String currency="USD";
		String account_no="000000";
		PreparedStatement ps;
		try {
			ps=connection.openConnection().prepareStatement(GET_BALANCE_by_CURRENCY);
			ps.setString(1, currency);
			ResultSet set=ps.executeQuery();
			
			while(set.next())
			{
				balance_USD=set.getDouble("SUM(AMOUNT)");
				System.out.println(balance_USD);
		    }
			String query="select account_no from accounts where account_type=?";
			ps=connection.openConnection().prepareStatement(query);
			ps.setString(1, currency);
			set=ps.executeQuery();
			while(set.next())
			{
				account_no=set.getString(1);
				System.out.println("Account number: "+account_no);
		    }
			
		obj.put("balance", balance_USD);
		obj.put("account_no", account_no);
			// getting total
		} catch (SQLException e) {
			
		    e.printStackTrace();
		}
		return obj;
	}
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/gbp")
	public JSONObject gbpBalance() {
		JSONObject obj=new JSONObject();
		
		String GET_BALANCE_by_CURRENCY="SELECT SUM(AMOUNT) FROM TRANSACTIONS WHERE currency=? ";
		DatabaseConnection connection= new DatabaseConnection();
		double balance_USD=0.0d;
		String currency="GBP";
		String account_no="000000";
		PreparedStatement ps;
		try {
			ps=connection.openConnection().prepareStatement(GET_BALANCE_by_CURRENCY);
			ps.setString(1, currency);
			ResultSet set=ps.executeQuery();
			
			while(set.next())
			{
				balance_USD=set.getDouble("SUM(AMOUNT)");
				//account_no=set.getString("ACCOUNT_NO");
				System.out.println("Gbp balance: "+balance_USD);
		    }
			String query="select account_no from accounts where account_type=?";
			ps=connection.openConnection().prepareStatement(query);
			ps.setString(1, currency);
			set=ps.executeQuery();
			while(set.next())
			{
				account_no=set.getString(1);
				System.out.println("Account number: "+account_no);
		    }
//			
		obj.put("balance", balance_USD);
		obj.put("account_no", account_no);
			// getting total
		} catch (SQLException e) {
			
		    e.printStackTrace();
		}
		return obj;
	}
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/eur")
	public JSONObject eurBalance() {
		JSONObject obj=new JSONObject();
		
		String GET_BALANCE_by_CURRENCY="SELECT SUM(AMOUNT) FROM TRANSACTIONS WHERE CURRENCY=? ";
		DatabaseConnection connection= new DatabaseConnection();
		double balance_USD=0.0d;
		String currency="EUR";
		String account_no="000000";
		PreparedStatement ps;
		try {
			ps=connection.openConnection().prepareStatement(GET_BALANCE_by_CURRENCY);
			ps.setString(1, currency);
			ResultSet set=ps.executeQuery();
			
			while(set.next())
			{
				balance_USD=set.getDouble("SUM(AMOUNT)");
				System.out.println(balance_USD);
		    }
			String query="select account_no from accounts where account_type=?";
			ps=connection.openConnection().prepareStatement(query);
			ps.setString(1, currency);
			set=ps.executeQuery();
			while(set.next())
			{
				account_no=set.getString(1);
				System.out.println("Account number: "+account_no);
		    }
		obj.put("balance", balance_USD);
		obj.put("account_no", account_no);
			// getting total
		} catch (SQLException e) {
			
		    e.printStackTrace();
		}
		return obj;
	}


}
