package com.logic;

import com.dao.UserTransactionDetailsDAO;

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

public JSONObject addTransaction(long transactionID,long userAccountNo, long counterpartyAccountNo, Date date, double amount,String currency) {
JSONObject response = new JSONObject();
int rows_inserted=0;
DatabaseConnection Connection= new DatabaseConnection();
PreparedStatement ps;
String ADD_TRANSACTION="INSERT INTO TRANSACTIONS VALUES(?,?,?,?,?,?,?,?)";
try {
ps=Connection.openConnection().prepareStatement(ADD_TRANSACTION);
//ps.setInt(1, ID);
//ps.setInt(2, user_ID);
ps.setLong(3, transactionID);
ps.setLong(4, userAccountNo);
ps.setLong(5, counterpartyAccountNo);
ps.setDouble(6, amount);
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

public JSONObject getTransactionbyAccount(String accountNo){
	JSONObject response = new JSONObject();
String GET_TRANSACTION_ACCOUNT="SELECT * FROM TRANSACTIONS WHERE ACCOUNT_NO=342847847";	//Should be a ? for user choice
DatabaseConnection Connection= new DatabaseConnection();
PreparedStatement ps;
try {
ps=Connection.openConnection().prepareStatement(GET_TRANSACTION_ACCOUNT);
//ps.setString(1, accountNo);		//Error here, check
ResultSet set=ps.executeQuery(GET_TRANSACTION_ACCOUNT);
if(set.next()) {
	response.put("error", false);
	response.put("message", "success");
	JSONArray transactionsArray = new JSONArray();
do{
//long transactionID=set.getLong("transactionID");
//long userAccountNo=set.getLong("userAccountNo");
//long counterpartyAccountNo=set.getLong("counterpartyAccountNo");
//Date date=set.getDate("date");
//double amount=set.getDouble("amount");
//String currency=set.getString("currency");
//UserTransactionDetails transaction=new UserTransactionDetails(transactionID, userAccountNo, counterpartyAccountNo, date, currency, amount);
//transactionList.add(transaction);
	
	//JSONObject Obj2 = (JSONObject) jso2.get("Object2"); Typecasting? try
	JSONObject transaction = new JSONObject();
	transaction.put("transactionID", set.getString(3));
	transaction.put("userAccountNo", set.getString(4));
//	transaction.put("counterpartyAccountNo", set.getString("counterpartyAccountNo"));
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
System.out.println(response);
return response;
}

public JSONObject getTransactionbyDate(Date date1){		//To be deleted
JSONObject response = new JSONObject();
List<UserTransactionDetails> transactionList=new ArrayList<>();
String GET_TRANSACTION_ACCOUNT="SELECT * FROM TRANSACTIONS WHERE DATE=?";
DatabaseConnection Connection= new DatabaseConnection();
PreparedStatement ps;
try {
ps=Connection.openConnection().prepareStatement(GET_TRANSACTION_ACCOUNT);
ps.setDate(1, date1);
ResultSet set=ps.executeQuery(GET_TRANSACTION_ACCOUNT);
if(set.next()) {
	response.put("error", false);
	response.put("message", "success");
do{
//long transactionID=set.getLong("transactionID");
//long userAccountNo=set.getLong("userAccountNo");
//long counterpartyAccountNo=set.getLong("counterpartyAccountNo");
//Date date=set.getDate("date");
//double amount=set.getDouble("amount");
//String currency=set.getString("currency");
//UserTransactionDetails transaction=new UserTransactionDetails(transactionID, userAccountNo, counterpartyAccountNo, date, currency, amount);
//transactionList.add(transaction);
	
	//JSONObject Obj2 = (JSONObject) jso2.get("Object2"); Typecasting? try
	JSONObject transaction = new JSONObject();
	transaction.put("transactionID", set.getString("transactionID"));
	transaction.put("userAccountNo", set.getString("userAccountNo"));
	transaction.put("counterpartyAccountNo", set.getString("counterpartyAccountNo"));
	transaction.put("date", set.getDate("date"));
	transaction.put("amount", set.getDouble("amount"));
	transaction.put("currency", set.getString("currency"));
	response.put("data", transaction);	//Adds or Updates?
}while(set.next());
}
else {
	response.put("error", true);
	response.put("message", "No transactions fetched");
	response.put("data", "");
}
}
catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
return response;
}


@Override
public JSONObject randomGenerateCashflow() {
// TODO Auto-generated method stub
	JSONObject response = new JSONObject();
	return response;
}

@Override
public JSONObject deleteTransaction(long transaction_ID) {
// TODO Auto-generated method stub
	JSONObject response = new JSONObject();
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

return response;
}
}
