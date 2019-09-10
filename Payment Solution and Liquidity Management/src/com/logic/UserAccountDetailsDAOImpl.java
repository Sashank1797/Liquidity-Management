	package com.logic;
	
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	
	import com.dao.UserAccountDetailsDAO;
	import com.database.DatabaseConnection;
	public class UserAccountDetailsDAOImpl implements UserAccountDetailsDAO{
	public boolean updateAccountBalance(String accountNo,double balance){
		boolean balanceUpdated=false;
		String FETCH_BALANCE="SELECT BALANCE FROM ACCOUNTS WHERE ACCOUNT_NO=?";
		String BALANCE_UPDATE ="update accounts set balance=? where account_no=?"; 
		
		
		PreparedStatement ps;
		try {
			DatabaseConnection Connection=new DatabaseConnection();
			ps=Connection.openConnection().prepareStatement(FETCH_BALANCE);
			ps.setString(1, accountNo);
			ResultSet set=ps.executeQuery();
			double Amount = 0;
			while(set.next()) {
				Amount=set.getDouble("amount");
			}
			Amount+=balance;
			ps = Connection.openConnection().prepareStatement(BALANCE_UPDATE);
			ps.setDouble(1, Amount);
			ps.setString(2, accountNo);
			int rows= ps.executeUpdate();
			if(rows>0) 
				balanceUpdated=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return balanceUpdated;
	}
	
	
	}
