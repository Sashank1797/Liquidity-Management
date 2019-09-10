package com.logic;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccountDetailsDAOImpl {
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
public boolean updateAccountBalance(double balance,String account_no) {
	boolean balanceUpdated=false;
	Connection conn=getConnection();
	String find_data="update accounts set BALANCE=? WHERE ACCOUNT_NO=?";
	try {
		PreparedStatement ps=conn.prepareStatement(find_data);
		BigDecimal d=new BigDecimal(balance);
		ps.setBigDecimal(1, d);
		ps.setString(2,account_no);
		//ps.setInt(2, id);
		int rs=ps.executeUpdate();
		
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return balanceUpdated;
}
public double poolingFunction() {
	double netAmount=0.0d;
	return netAmount;
}
public static void main(String[] args) {
	UserAccountDetailsDAOImpl user=new UserAccountDetailsDAOImpl();
	boolean result=false;
	result=user.updateAccountBalance(24924.43,"4502842");
	if(result)
		System.out.println("Account updated");
		else
			System.out.println("Account not updated");
}
}
