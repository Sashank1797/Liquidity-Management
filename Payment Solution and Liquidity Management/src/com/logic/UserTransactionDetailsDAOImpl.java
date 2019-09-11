
package com.logic;

import com.dao.UserAccountDetailsDAO;
import com.dao.UserTransactionDetailsDAO;
import java.util.Random;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONObject;
import java.sql.Statement;
import com.pojo.UserTransactionDetails;

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


  public JSONObject addTransaction(long transactionID,String userAccountNo, String counterpartyAccountNo, Date date, double amount,String currency) {
	JSONObject response = new JSONObject();
	int rows_inserted=0;
	DatabaseConnection Connection= new DatabaseConnection();
	PreparedStatement ps;
	String ADD_TRANSACTION="INSERT INTO TRANSACTIONS VALUES(?,?,?,?,?,?,?,?)";
	try {
			ps=Connection.openConnection().prepareStatement(ADD_TRANSACTION);
			ps.setInt(1, 1);
			ps.setInt(2, 1);
			ps.setLong(3, transactionIdGenerator());
			ps.setString(4, userAccountNo);
			ps.setString(5, counterpartyAccountNumberGenerator());		
			ps.setDouble(6, getRandomDoubleBetweenRange(-1000000,1000000));
			ps.setString(7, currency);
			ps.setDate(8, date);
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
  
  

public JSONObject getTransactionbyAccount(long accountNo){
  JSONObject response = new JSONObject();
  String GET_TRANSACTION_ACCOUNT="SELECT * FROM TRANSACTIONS WHERE ACCOUNT_NO=?";
	DatabaseConnection Connection= new DatabaseConnection();
	PreparedStatement ps;
	try {
		ps=Connection.openConnection().prepareStatement(GET_TRANSACTION_ACCOUNT);
		ps.setLong(1, accountNo);
		ResultSet set=ps.executeQuery(GET_TRANSACTION_ACCOUNT);
    if(set.next()) {
	    response.put("error", false);
	    response.put("message", "success");
    	JSONArray transactionsArray = new JSONArray();
      do{
        JSONObject transaction = new JSONObject();
	      transaction.put("transactionID", set.getString(3));
	      transaction.put("userAccountNo", set.getString(4));
	      transaction.put("counterpartyAccountNo", set.getString("counterpartyAccountNo"));
	      transaction.put("date", set.getDate(8));
	      transaction.put("amount", set.getDouble(5));
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
  
  @Override
  JSONObject randomGenerateCashflow() {
  // TODO Auto-generated method stub
	JSONObject response = new JSONObject();
	return response;
  }
  
	@Override
	public List<UserTransactionDetails> deleteTransaction(long transaction_ID) {
		// TODO Auto-generated method stub
		List<UserTransactionDetails> transactionList=new ArrayList<>();
		String SELECTION="SELECT * FROM TRANSACTION WHERE TRANSACTIONID=?";
		String DELETE_TRANSACTION="DELETE FROM TRANSACTION WHERE TRANSACTIONID=?";
		DatabaseConnection Connection=new DatabaseConnection();
		try {
	PreparedStatement ps=Connection.openConnection().prepareStatement(SELECTION);
	ps.setLong(1, transaction_ID);
	ResultSet set=ps.executeQuery();
	while(set.next()) {
	long transactionID=set.getLong("transactionID");
	long userAccountNo=set.getLong("userAccountNo");
	long counterpartyAccountNo=set.getLong("counterpartyAccountNo");
	Date date=set.getDate("date");
	double amount=set.getDouble("amount");
	String currency=set.getString("currency");
	UserTransactionDetails transaction=new UserTransactionDetails(transactionID, userAccountNo, counterpartyAccountNo, date, currency, amount);
	transactionList.add(transaction);
	Connection.openConnection().prepareStatement(DELETE_TRANSACTION);
	}
	} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	
	return transactionList;
	}
	@Override
	public boolean addTransaction(long transactionID, long userAccountNo, long counterpartyAccountNo, Date date,
			double amount, String currency) {
		// TODO Auto-generated method stub
		return false;
	}
	}




 
	