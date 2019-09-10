package com.logic;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.pojo.UserAccountDetails;
import com.pojo.UserTransactionDetails;

public class UserTransactionDetailsDAOImpl {
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
	public List<UserTransactionDetails> getTransactionbyAccount(String accountNo){
		List<UserTransactionDetails> transactionList=new ArrayList<>();
		UserTransactionDetails ut=null;
		Connection conn=getConnection();
		int user_id=0;
		String currency="USD";
		try {
		String query="select * from transactions where account_no=?";
		PreparedStatement ps=conn.prepareStatement(query);
		ps.setString(1, accountNo);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			ut=new UserTransactionDetails(rs.getInt(1),rs.getString(3),rs.getString(4),rs.getDate(7),rs.getDouble(5),rs.getString(6));
			transactionList.add(ut);
			System.out.println("User id: "+rs.getInt(2));
		}
		for (UserTransactionDetails utl : transactionList) {
			System.out.println(utl.getUserAccountNo());
		}
//			int rs=ps.executeUpdate();
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transactionList;
	}
	public List<UserTransactionDetails> getTransactionbyDate(Date date){
		List<UserTransactionDetails> transactionList=new ArrayList<>();
		UserTransactionDetails ut=null;
		Connection conn=getConnection();
		int user_id=0;
		String currency="USD";
		try {
		String query="select * from transactions where transaction_date=?";
		PreparedStatement ps=conn.prepareStatement(query);
		ps.setDate(1, date);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			ut=new UserTransactionDetails(rs.getInt(1),rs.getString(3),rs.getString(4),rs.getDate(7),rs.getDouble(5),rs.getString(6));
			transactionList.add(ut);
			System.out.println("User id: "+rs.getInt(2));
		}
		for (UserTransactionDetails utl : transactionList) {
			System.out.println(utl.getUserAccountNo());
		}
//			int rs=ps.executeUpdate();
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transactionList;
		
	}

	
	public static void main(String[] args) {
		UserTransactionDetailsDAOImpl trans=new UserTransactionDetailsDAOImpl();
		Date date=new Date(2019,02,23);
		//trans.addTransaction("594304", "439201", date, 6594.21);
//		trans.getTransactionbyAccount("594304");
		//trans.getTransactionbyDate(date);
		trans.randomGenerateCashFlow(2);
	}
	
	public void randomGenerateCashFlow(int userdId) {
		Connection conn=getConnection();
		try {
			int curr_id=0;
		    Date datee=new Date(23,06,1997);
		    String query="select transaction_id,transaction_date+1 from transactions";
			PreparedStatement ps=conn.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				if(rs.getInt(1)>curr_id) {
					curr_id=rs.getInt(1);
					datee=rs.getDate(2);
				}
				
			}
		for(int i=0;i<5;i++) {
		
		 query="select account_no,account_type,balance from accounts where user_id=?";
		 ps=conn.prepareStatement(query);
		ps.setInt(1, userdId);
		 rs=ps.executeQuery();
		List<Accounts> list=new ArrayList<Accounts>();
		Accounts ua=null;
		Random random = new Random();

		while(rs.next()) {
			System.out.println("Account no: "+rs.getString(1)+" Account type: "+rs.getString(2));
			ua=new Accounts(rs.getString(1),rs.getString(2),rs.getDouble(3));
			list.add(ua);
		}
		for (Accounts accounts : list) {
			System.out.println("Account no: "+accounts.account_no);
		}
		int randomNo=random.nextInt(list.size());
		String accNo=list.get(randomNo).account_no;
		String cur=list.get(randomNo).account_type;
		Random r = new Random();
	    int counterparty_accno = 100000 + (int)(r.nextFloat() * 899900);
	    String counterparty_acc_no=String.valueOf(counterparty_accno);
	    System.out.println("Random: "+counterparty_accno);
	    double amount=random.nextDouble()*10000;
	    System.out.println("amount: "+amount);
	    String amountstr=String.valueOf(amount);
	    amountstr=amountstr.substring(0,7);
	    amount=Double.parseDouble(amountstr);
	   // System.out.println("String val: "+amountstr);
//	    int month[]= {1,2,3,4,5,6,7,8};
//	    int year[]= {2018,2019};
//	    int day=random.nextInt(28);
//	    int monthno=random.nextInt(8);
//	    int yearno=random.nextInt(2);
//	    System.out.println("year no: "+year[yearno]);
//	    Date date=new Date(year[yearno],month[monthno],day);
//	    System.out.println("Yr: "+date.getYear());
//	    System.out.println("Date: "+date);
	    addTransaction(accNo,counterparty_acc_no,datee,amount);
		}
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
class Accounts{
	String account_no;
	String account_type;
	double balance;
	public Accounts(String an,String at,double bal) {
		account_no=an;
		account_type=at;
		balance=bal;
	}
}