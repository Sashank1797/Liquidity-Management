package com.dao;

import org.json.simple.JSONObject;

public interface UserAccountDetailsDAO {

	public JSONObject updateAccountBalance(String accountNo, double balance);
	public JSONObject poolingFunction(int userID);
	
}