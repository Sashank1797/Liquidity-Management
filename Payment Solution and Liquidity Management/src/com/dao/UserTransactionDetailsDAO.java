package com.dao;
import java.sql.Date;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public interface UserTransactionDetailsDAO {

	public JSONObject randomGenerateTransaction(String data) throws ParseException;
	public JSONObject addTransaction(String data) throws ParseException;
	public JSONObject getTransactionbyCurrency(String data) throws ParseException;
	public JSONObject deleteTransaction(String data) throws ParseException;
}
