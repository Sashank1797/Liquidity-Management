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
import java.sql.Statement;

import com.pojo.UserTransactionDetails;

public class UserTransactionDetailsDAOImpl implements UserTransactionDetailsDAO{

public boolean addTransaction(String transactionID,String userAccountNo,String counterpartyAccountNo, String date, double amount,String currency) {
	boolean transactionAdded=false;
	int rows_inserted=0;
	
	DatabaseConnection connection= new DatabaseConnection();
	PreparedStatement ps;
	
	for(int i=5;i<9;i++) {
		
		String ADD_TRANSACTION="INSERT INTO TRANSACTIONS VALUES(?,?,?,?,?,?,?,?)";
		
		try {
			ps=connection.openConnection().prepareStatement(ADD_TRANSACTION);
			ps.setInt(1, i);
			ps.setInt(2, 1);
			ps.setString(3, transactionID);
			ps.setString(4, userAccountNo);
			ps.setString(5, counterpartyAccountNo);
			ps.setDouble(6, amount);
			ps.setString(7, currency);
			ps.setString(8, date);
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
public List<UserTransactionDetails> getTransactionbyAccount(String accountNo){
	
	List<UserTransactionDetails> transactionList=new ArrayList<>();
	String GET_TRANSACTION_by_ACCOUNT="SELECT * FROM TRASACTIONS WHERE ACCOUNT_NO=?";
	DatabaseConnection connection= new DatabaseConnection();
	PreparedStatement ps;
	try {
		ps=connection.openConnection().prepareStatement(GET_TRANSACTION_by_ACCOUNT);
		ps.setString(1, accountNo);
		ResultSet set=ps.executeQuery();
		
		while(set.next())
		{
			String transactionID=set.getString("TRASACTION_ID");
			String userAccountNo=set.getString("ACCOUNT_NO");
			String counterpartyAccountNo=set.getString("COUNTERPARTY_ACCOUNT_NO");
			Date date=set.getDate("TRANSACTION_DATE");
			double amount=set.getDouble("AMOUNT");
			String currency=set.getString("CURRENCY");
			UserTransactionDetails transaction=new UserTransactionDetails(transactionID, userAccountNo, counterpartyAccountNo, date, currency, amount);
			transactionList.add(transaction);
		
	    }
	} catch (SQLException e) {
	
	    e.printStackTrace();
	}
	return transactionList;
}

//public List<UserTransactionDetails> getTransactionbyDate(Date date1){
//	List<UserTransactionDetails> transactionList=new ArrayList<>();
//	String GET_TRANSACTION_ACCOUNT="SELECT * FROM TRASACTIONS WHERE DATE=?";
//	DatabaseConnection Connection= new DatabaseConnection();
//	PreparedStatement ps;
//	try {
//		ps=Connection.openConnection().prepareStatement(GET_TRANSACTION_ACCOUNT);
//		ps.setDate(1, date1);
//		ResultSet set=ps.executeQuery(GET_TRANSACTION_ACCOUNT);
//		while(set.next())
//		{
//		long transactionID=set.getLong("transactionID");
//		long userAccountNo=set.getLong("userAccountNo");
//		long counterpartyAccountNo=set.getLong("counterpartyAccountNo");
//		Date date=set.getDate("date");
//		double amount=set.getDouble("amount");
//		String currency=set.getString("currency");
//		UserTransactionDetails transaction=new UserTransactionDetails(transactionID, userAccountNo, counterpartyAccountNo, date, currency, amount);
//		transactionList.add(transaction);
//	}
//	}
//	catch (Exception e) {
//	// TODO: handle exception
//	e.printStackTrace();
//	}
//	return transactionList;
//}


@Override
public void randomGenerateCashflow() {
// TODO Auto-generated method stub

}
@Override
public List<UserTransactionDetails> deleteTransaction(String transaction_ID) {

	List<UserTransactionDetails> transactionList=new ArrayList<>();
	
	String SELECTION="SELECT * FROM TRANSACTIONS WHERE TRANSACTION_ID=?";
	String DELETE_TRANSACTION="DELETE FROM TRANSACTIONS WHERE TRANSACTION_ID=?";
	
	DatabaseConnection connection=new DatabaseConnection();
	PreparedStatement ps1;
	try {
		PreparedStatement ps=connection.openConnection().prepareStatement(SELECTION);
		ps.setString(1, transaction_ID);
		ResultSet set=ps.executeQuery();
		
		while(set.next()) {
			String transactionID=set.getString("TRASACTION_ID");
			String userAccountNo=set.getString("ACCOUNT_NO");
			String counterpartyAccountNo=set.getString("COUNTERPARTY_ACCOUNT_NO");
			Date date=set.getDate("TRANSACTION_DATE");
			double amount=set.getDouble("AMOUNT");
			String currency=set.getString("CURRENCY");
			
			UserTransactionDetails transaction=new UserTransactionDetails(transactionID, userAccountNo, counterpartyAccountNo, date, currency, amount);
			transactionList.add(transaction);
			
			// Delete by TRANSACTION_ID
			
			ps1=connection.openConnection().prepareStatement(DELETE_TRANSACTION);
			ps1.setString(1, transactionID);
			ps1.executeQuery();
	}
	} catch (SQLException e) {
	// TODO Auto-generated catch block
	    e.printStackTrace();
	}

    return transactionList;
}
}
