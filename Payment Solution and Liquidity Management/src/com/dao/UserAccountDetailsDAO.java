package com.dao;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public interface UserAccountDetailsDAO {

	public JSONObject updateAccountBalance(String data) throws ParseException;
	public JSONObject getAccountBalanceandNumberbyID(String data) throws ParseException;
}