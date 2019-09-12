
package com.logic;

import com.dao.UserAccountDetailsDAO;
import com.dao.UserTransactionDetailsDAO;
import java.util.Random;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import java.sql.Statement;

import com.pojo.UserTransactionDetails;

@Path("/transactionalinformation")
public class UserTransactionDetailsDAOImpl implements UserTransactionDetailsDAO{

	public static int getRandomNumberInRange(int min, int max) {

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	public static String transactionIdGenerator() {
		int x = getRandomNumberInRange(10000, 99999);
		ArrayList<Integer> list = new ArrayList<Integer>();
		while(list.contains(x)) {
			x = getRandomNumberInRange(10000, 99999);
		}
		list.add(x);
		String transactionId="TXA79"+x;
		return transactionId;
	}
	
	
	public static String counterpartyAccountNumberGenerator() {
		int n = getRandomNumberInRange(10000, 99999);
		String account_number="70010"+n;
		return account_number;
	}
	
	public static double getRandomDoubleBetweenRange(double min, double max){
	    //double x = ((Math.random()*(max-min))+min);
	   double x = (Math.floor(((Math.random()*(max-min))+min)*100))/100;
	    return x;
    
  }
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/randomtransaction")
	public JSONObject randomGenerateTransaction(String data) throws ParseException {
		//Parameters - String userAccountNo, String date,String currency
		JSONObject response = new JSONObject();
		JSONParser parser=new JSONParser();
		JSONObject request=(JSONObject) parser.parse(data);
		String account_no=(String)request.get("account_no");
		String date=(String)request.get("date");
		String currency=(String)request.get("currency");		
	int rows_inserted=0;
	DatabaseConnection Connection= new DatabaseConnection();
	PreparedStatement ps;
	String ADD_TRANSACTION="INSERT INTO TRANSACTIONS VALUES(?,?,?,?,?,?,?,?)";
	
	try {
			ps=Connection.openConnection().prepareStatement(ADD_TRANSACTION);
			ps.setInt(1, 3);
			ps.setInt(2, 3);
			ps.setString(3, transactionIdGenerator());
			ps.setString(4, account_no);
			ps.setString(5, counterpartyAccountNumberGenerator());

			ps.setDouble(6, getRandomDoubleBetweenRange(-1000000,1000000));
			ps.setString(7, currency);
			ps.setString(8, date);
			rows_inserted=ps.executeUpdate();
      if(rows_inserted>0) {
      	response.put("error", false);
      	response.put("message", "success");
	     response.put("data", "");
      }
      else {
      	response.put("error", true);
      	response.put("message", "Transaction not added");
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
	@Path("/inserttransaction")
	public JSONObject addTransaction(String data) throws ParseException {
		//Parameters- String userAccountNo, String counterpartyAccountNo, String date,double amount, String currency
		JSONObject response = new JSONObject();
		JSONParser parser=new JSONParser();
		JSONObject request=(JSONObject) parser.parse(data);
		String account_no=(String)request.get("account_no");
		String counterparty_account_no=(String)request.get("counterparty_account_no");
		Double amount=(Double)request.get("amount");
		String currency=(String)request.get("currency");
		String date=(String)request.get("date");
	int rows_inserted=0;
	DatabaseConnection Connection= new DatabaseConnection();
	PreparedStatement ps;
	String ADD_TRANSACTION="INSERT INTO TRANSACTIONS VALUES(?,?,?,?,?,?,?,?)";
	
	try {
			ps=Connection.openConnection().prepareStatement(ADD_TRANSACTION);
			ps.setInt(1, 1);
			ps.setInt(2, 1);
			ps.setString(3, transactionIdGenerator());
			ps.setString(4, account_no);
			ps.setString(5, counterparty_account_no);
			ps.setDouble(6, amount);
			ps.setString(7, currency);
			ps.setString(8, date);
			rows_inserted=ps.executeUpdate();
      if(rows_inserted>0) {
      	response.put("error", false);
      	response.put("message", "success");
	     response.put("data", "");
      }
      else {
      	response.put("error", true);
      	response.put("message", "Transaction not added");
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
	@Path("/transactiondata")
	public JSONObject getTransactionbyCurrency(String data) throws ParseException{
		//Parameters- String currency
		JSONObject response = new JSONObject();
		JSONParser parser=new JSONParser();
		JSONObject request=(JSONObject) parser.parse(data);
		String currency=(String)request.get("currency");
		String GET_TRANSACTION_ACCOUNT="SELECT * FROM TRANSACTIONS WHERE currency=?";
		
	DatabaseConnection Connection= new DatabaseConnection();
	PreparedStatement ps;
	try {
		ps=Connection.openConnection().prepareStatement(GET_TRANSACTION_ACCOUNT);
		ps.setString(1, currency);
		ResultSet set=ps.executeQuery();
		
		if(set.next()) {
	    	response.put("error", false);
	    	response.put("message", "success");
    	JSONArray transactionsArray = new JSONArray();
    	do{
    		JSONObject transaction = new JSONObject();
    		transaction.put("id", set.getString(1));
  	      transaction.put("user_id", set.getString(2));
	      transaction.put("transaction_ID", set.getString(3));
	      transaction.put("account_No", set.getString(4));
	      transaction.put("counterparty_account_no", set.getString(5));
	      transaction.put("transaction_date", set.getString(8));
	      transaction.put("amount", set.getDouble(6));
	      transaction.put("currency", set.getString(7));
	      transactionsArray.add(transaction);
      }while(set.next());
      response.put("data", transactionsArray);
    }
    else {
	    response.put("error", true);
	    response.put("message", "No transactions fetched");
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
	@Path("/removetransaction")
	@Override
	public JSONObject deleteTransaction(String data) throws ParseException {
		//Parameters - string transaction_id
		// TODO Auto-generated method stub
		JSONObject response=new JSONObject();
		JSONParser parser=new JSONParser();
		JSONObject request=(JSONObject) parser.parse(data);
		String transaction_id=(String)request.get("account_no");
		
		String DELETE_TRANSACTION="DELETE FROM TRANSACTIONs WHERE TRANSACTION_ID=?";
		DatabaseConnection Connection=new DatabaseConnection();
	try {
		PreparedStatement ps=Connection.openConnection().prepareStatement(DELETE_TRANSACTION);
		ps.setString(1, transaction_id);
		int deleted = ps.executeUpdate();
	  if(deleted>0){
      response.put("error", false);
	    response.put("message", "Sucess");
	    response.put("data", "");
    }
    else {
	    response.put("error", true);
	    response.put("message", "No transactions deleted");
	    response.put("data", "");
    }
	} 
	catch (SQLException e) {
	// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return response;
	}

@Override
public JSONObject getCashFlows(String currency, String date) {
	// TODO Auto-generated method stub
	JSONObject response = new JSONObject();
	String GET_INFLOW="SELECT SUM(AMOUNT) FROM TRANSACTIONS WHERE CURRENCY=? AND DATE=? AND AMOUNT>0";
	String GET_OUTFLOW="SELECT SUM(AMOUNT) FROM TRANSACTIONS WHERE CURRENCY=? AND DATE=? AND AMOUNT<0";
	double inflow=0.0d, outflow=0.0d;
	
	DatabaseConnection connection= new DatabaseConnection();
	PreparedStatement ps;
	
	try {
		ps=connection.openConnection().prepareStatement(GET_INFLOW);
		ps.setString(1, currency);
		ps.setString(2, date);
		ResultSet set=ps.executeQuery();
		
		if(set.next())
		{
			inflow=set.getDouble("SUM(AMOUNT)");
			//System.out.println(balance_GBP);
	    }
		else {
			
		}
				
	} catch (SQLException e) {	
	    e.printStackTrace();
	}
	try {
		ps=connection.openConnection().prepareStatement(GET_OUTFLOW);
		ps.setString(1, currency);
		ps.setString(2, date);
		ResultSet set=ps.executeQuery();
		
		if(set.next())
		{
			outflow=set.getDouble("SUM(AMOUNT)");
			//System.out.println(balance_GBP);
	    }
		else {
		}			
	} catch (SQLException e) {	
	    e.printStackTrace();
	}
	
	return response;
}
}
	