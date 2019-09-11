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

import com.logic.UserTransactionDetailsDAOImpl;
import com.pojo.UserTransactionDetails;
@Path("/addTransaction")
public class addTransaction {
	public Connection getConnection() {
		Connection conn=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver loaded successfully");
			//jdbc:data_base:install_server:port/database
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","abcd123");
			System.out.println("Connection obtained");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	public boolean addTransaction(String userAccountNo,String counterpartyAccountNo,Date date,double amount) {
		boolean transactionAdded=false;
		Connection conn=getConnection();
		int user_id=0;
		String currency="USD";
		
		Date datee=new Date(23,06,1997);
		try {
		String query="select * from accounts where account_no=?";
		PreparedStatement ps=conn.prepareStatement(query);
		ps.setString(1, userAccountNo);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			System.out.println("User id: "+rs.getInt(2));
			user_id=rs.getInt(2);
			currency=rs.getString(4);
		}
		
		
		query="insert into transactions(user_id,account_no,counterparty_account_no,amount,currency,transaction_date) values(?,?,?,?,?,?)\r\n" + 
				"";
		ps=conn.prepareStatement(query);
		 ps.setInt(1, user_id);
		 ps.setString(2, userAccountNo);
		 ps.setString(3, counterpartyAccountNo);
		 ps.setDouble(4, amount);
		 ps.setString(5,currency);
		 ps.setDate(6, date);
		 int val=ps.executeUpdate();
		 if(val>0) {
			 System.out.println("Data inserted");
		 }
		 else {
			 System.out.println("Failed");
		 }
//			int rs=ps.executeUpdate();
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transactionAdded;
	}
	@POST
	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add")
	public String add(String data) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(data);
		System.out.println("Hope i get a value here: "+json.get("reciepient_name"));
		String counterparty_acc_no=(String)json.get("account_no");
		System.out.println("account_no: "+counterparty_acc_no);
		String am=(String)json.get("amount");
		System.out.println("am : "+am);
		//Long lng=new Long((long) json.get("amount"));
		double amount=Double.parseDouble(am);
		int user_id=2;
		String account_no="000000";
		String currency="USD";
		Connection conn=getConnection();
		try {
			int curr_id=0;
		    Date date=new Date(23,06,1997);
		    String query="select transaction_id,transaction_date from transactions";
			PreparedStatement ps=conn.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				if(rs.getInt(1)>curr_id) {
					curr_id=rs.getInt(1);
					date=rs.getDate(2);
				}
			}
			query="select account_no from accounts where account_type=?";
			ps=conn.prepareStatement(query);
			ps.setString(1, currency);
			rs=ps.executeQuery();
			while(rs.next()) {
			account_no=rs.getString(1);
			System.out.println("Account_no: "+account_no);
			}
			boolean result=addTransaction(account_no,counterparty_acc_no,date,amount);
			if(result) {
				System.out.println("Transaction added");
				data="Transaction added succesfully";
				return data;
			}
			else {
				System.out.println("Transaction not added");
			}
		}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return currency;
	}
}
