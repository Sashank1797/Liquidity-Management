package com.logic;

import com.dao.UserAccountDetailsDAO;
import com.dao.UserTransactionDetailsDAO;
import com.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import com.pojo.UserTransactionDetails;

public class UserTransactionDetailsDAOImpl implements UserTransactionDetailsDAO{

public boolean addTransaction(long transactionID,String userAccountNo, String counterpartyAccountNo, Date date, double amount,String currency) {
	boolean transactionAdded=false;
	int rows_inserted=0;
	DatabaseConnection Connection= new DatabaseConnection();
	PreparedStatement ps;
	String ADD_TRANSACTION="INSERT INTO TRANSACTIONS VALUES(?,?,?,?,?,?,?,?)";
	try {
			ps=Connection.openConnection().prepareStatement(ADD_TRANSACTION);
			ps.setInt(1, ID);
			ps.setInt(2, user_ID);
			ps.setLong(3, transactionID);
			ps.setString(4, userAccountNo);
			ps.setString(5, counterpartyAccountNo);
			
			
			ps.setDouble(6, amount);
			ps.setString(7, currency);
			ps.setDate(8, date);
			rows_inserted=ps.executeUpdate();
			if(rows_inserted>0)
					transactionAdded=true;
			String UPDATE_BALANCE="SELECT BALANCE FROM ACCOUNTS WHERE ACCOUNT_NO=?";
			ps=Connection.openConnection().prepareStatement(UPDATE_BALANCE);
			ps.setString(1, userAccountNo);
			ResultSet set=ps.executeQuery();
			double Amount;
			while(set.next()) {
				Amount=set.getDouble("amount");
			}
			double balance=Amount+amount;
			UserAccountDetailsDAO updateBalance=new UserAccountDetailsDAOImpl();
			boolean isUpdated=updateBalance.updateAccountBalance(userAccountNo,balance);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
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
	@Override
	public boolean addTransaction(long transactionID, long userAccountNo, long counterpartyAccountNo, Date date,
			double amount, String currency) {
		// TODO Auto-generated method stub
		return false;
	}
	}
