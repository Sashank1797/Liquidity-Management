package com.dao;
import java.sql.Date;
import org.json.simple.JSONObject;

public interface UserTransactionDetailsDAO {

	public JSONObject randomGenerateTransaction(String userAccountNo, String date,String currency);
	public JSONObject addTransaction(String userAccountNo, String counterpartyAccountNo, String date,double amount,String currency);
	public JSONObject getTransactionbyAccount(String AccountNo);
	public JSONObject deleteTransaction(String transactionID);
}
