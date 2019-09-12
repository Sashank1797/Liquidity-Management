
package com.logic;

import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dao.UserAccountDetailsDAO;
import com.pojo.UserTransactionDetails;

import com.database.DatabaseConnection;
import org.json.simple.JSONObject;

public class UserAccountDetailsDAOImpl implements UserAccountDetailsDAO{
	
	public JSONObject updateAccountBalance(String accountNo,double balance) {
		JSONObject response = new JSONObject();
		String BALANCE_UPDATE ="update accounts set balance=? where account_no={SELECT BALANCE FROM ACCOUNTS WHERE ACCOUNT_NO=?}"; 
		
		PreparedStatement ps;
		try {
			DatabaseConnection Connection=new DatabaseConnection();
			ps=Connection.openConnection().prepareStatement(BALANCE_UPDATE);
			ps.setString(1, accountNo);
			ResultSet set=ps.executeQuery();
			if(set.next()) {
				response.put("error", false);
				response.put("message", "success");
				response.put("data", "");
        return response;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.put("error", true);
    response.put("message", "Balance not updated");
    response.put("data", "");
    return response;
	}

@Override
public JSONObject getAccountBalanceandNumberbyID(int user_ID) {
	// TODO Auto-generated method stub
	JSONObject response=new JSONObject();
	String GET_BY_ID="SELECT BALANCE, ACCOUNT_NO FROM FROM ACCOUNTS WHERE USER_ID=?";
	DatabaseConnection Connection=new DatabaseConnection();
	PreparedStatement ps;
	try {
		ps=Connection.openConnection().prepareStatement(GET_BY_ID);
		ResultSet set=ps.executeQuery();
		if(set.next()) {
			response.put("error", false);
			response.put("message", "success");
			JSONObject accountBalance=new JSONObject();
			accountBalance.put("balance",set.getDouble(5));
			response.put("data", accountBalance);
		}
		else {
			response.put("error", true);
			response.put("message", "Failed to get data");
			response.put("data", "  ");
			
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}

}

