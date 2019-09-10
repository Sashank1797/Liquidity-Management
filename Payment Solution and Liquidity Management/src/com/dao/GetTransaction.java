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
@Path("/getTransaction")
public class GetTransaction {
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
	@POST
	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/msg")
	public JSONObject getTransactionbyAccount(String accountNo){
		System.out.println("Account no: "+accountNo);
		List<UserTransactionDetails> transactionList=new ArrayList<>();
		JSONObject obj;
		JSONObject obj1 = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		UserTransactionDetails ut=null;
		Connection conn=getConnection();
		int user_id=0;
		String currency="USD";
		try {
		String query="select * from transactions";
		PreparedStatement ps=conn.prepareStatement(query);
		//ps.setString(1, accountNo);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			obj = new JSONObject();
			obj.put("transaction_id", rs.getInt(1));
			obj.put("user_id", rs.getInt(2));
			obj.put("account_no", rs.getString(3));
			obj.put("counterparty_account_no", rs.getString(4));
			obj.put("amount", rs.getDouble(5));
			obj.put("currency", rs.getString(6));
			obj.put("date", rs.getDate(7));
//			System.out.println(obj);
			jsonArr.add(obj);
//			System.out.println(jsonArr);
			
		}
//		System.out.println("outside: "+jsonArr);
		obj1.put("Array", jsonArr);
//		System.out.println();
//		System.out.println("JsonArray: "+obj1);
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj1;
	}
//	@Path("/delete")
//	public JSONObject deleteTransaction(String value){
//		JSONObject obj=null;
//		JSONObject obj1 = new JSONObject();
//		JSONArray jsonArr = new JSONArray();
//		System.out.println("Inside delete: "+value);
//		int transaction_id=104;
//		Connection conn=getConnection();
//		try {
//			String query="delete from transactions where transaction_id=?";
//			PreparedStatement ps=conn.prepareStatement(query);
//			ps.setInt(1, transaction_id);
//			ps.executeUpdate();
//			 query="select * from transactions";
//			 ps=conn.prepareStatement(query);
//			//ps.setString(1, accountNo);
//			ResultSet rs=ps.executeQuery();
//			while(rs.next()) {
//				obj = new JSONObject();
//				obj.put("transaction_id", rs.getInt(1));
//				obj.put("user_id", rs.getInt(2));
//				obj.put("account_no", rs.getString(3));
//				obj.put("counterparty_account_no", rs.getString(4));
//				obj.put("amount", rs.getDouble(5));
//				obj.put("currency", rs.getString(6));
//				obj.put("date", rs.getDate(7));
////				System.out.println(obj);
//				jsonArr.add(obj);
////				System.out.println(jsonArr);
//				
//			}
//			System.out.println("outside: "+jsonArr);
//			obj1.put("Array", jsonArr);
//			System.out.println();
//			System.out.println("JsonArray: "+obj1);
//				} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		return obj;
//	}
	public static void main(String[] args) {
		GetTransaction trans=new GetTransaction();
		Date date=new Date(2019,02,23);
		//trans.addTransaction("594304", "439201", date, 6594.21);
		trans.getTransactionbyAccount("594304");
		//trans.getTransactionbyDate(date);
//		trans.randomGenerateCashFlow(1);
	}
}
