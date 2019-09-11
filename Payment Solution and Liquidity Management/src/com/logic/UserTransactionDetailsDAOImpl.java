package com.logic;

import com.dao.UserTransactionDetailsDAO;
import com.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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


public boolean addTransaction(String transactionID,String userAccountNo,String counterpartyAccountNo, Date date, double amount,String currency) {
boolean transactionAdded=false;
int rows_inserted=0;
DatabaseConnection Connection= new DatabaseConnection();
PreparedStatement ps;
for(int i=0;i<30;i++) {
String ADD_TRANSACTION="INSERT INTO TRANSACTIONS VALUES(?,?,?,?,?,?,?,?)";
try {
ps=Connection.openConnection().prepareStatement(ADD_TRANSACTION);
ps.setInt(1, 1);
ps.setInt(2, 1);
ps.setString(3, transactionIdGenerator());
ps.setString(4, userAccountNo);
ps.setString(5, counterpartyAccountNumberGenerator());
ps.setDouble(6, getRandomDoubleBetweenRange(-1000000,1000000));
ps.setString(7, currency);
ps.setDate(8, date);
rows_inserted=ps.executeUpdate();
if(rows_inserted>0)
transactionAdded=true;
} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}

}
return transactionAdded;
}
public List<UserTransactionDetails> getTransactionbyAccount(long accountNo){
List<UserTransactionDetails> transactionList=new ArrayList<>();
String GET_TRANSACTION_ACCOUNT="SELECT * FROM TRASACTIONS WHERE ACCOUNTNO=?";
DatabaseConnection Connection= new DatabaseConnection();
PreparedStatement ps;
try {
ps=Connection.openConnection().prepareStatement(GET_TRANSACTION_ACCOUNT);
ps.setLong(1, accountNo);
ResultSet set=ps.executeQuery(GET_TRANSACTION_ACCOUNT);
while(set.next())
{
long transactionID=set.getLong("transactionID");
long userAccountNo=set.getLong("userAccountNo");
long counterpartyAccountNo=set.getLong("counterpartyAccountNo");
Date date=set.getDate("date");
double amount=set.getDouble("amount");
String currency=set.getString("currency");
UserTransactionDetails transaction=new UserTransactionDetails(transactionID, userAccountNo, counterpartyAccountNo, date, currency, amount);
transactionList.add(transaction);

}
} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
return transactionList;
}

public List<UserTransactionDetails> getTransactionbyDate(Date date1){
List<UserTransactionDetails> transactionList=new ArrayList<>();
String GET_TRANSACTION_ACCOUNT="SELECT * FROM TRASACTIONS WHERE DATE=?";
DatabaseConnection Connection= new DatabaseConnection();
PreparedStatement ps;
try {
ps=Connection.openConnection().prepareStatement(GET_TRANSACTION_ACCOUNT);
ps.setDate(1, date1);
ResultSet set=ps.executeQuery(GET_TRANSACTION_ACCOUNT);
while(set.next())
{
long transactionID=set.getLong("transactionID");
long userAccountNo=set.getLong("userAccountNo");
long counterpartyAccountNo=set.getLong("counterpartyAccountNo");
Date date=set.getDate("date");
double amount=set.getDouble("amount");
String currency=set.getString("currency");
UserTransactionDetails transaction=new UserTransactionDetails(transactionID, userAccountNo, counterpartyAccountNo, date, currency, amount);
transactionList.add(transaction);
}
}
catch (Exception e) {
// TODO: handle exception
e.printStackTrace();
}
return transactionList;
}


@Override
public void randomGenerateCashflow() {
// TODO Auto-generated method stub

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
}
