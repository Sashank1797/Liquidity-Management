package com.dao;
import org.json.simple.JSONObject;

public interface UserTransactionDetailsDAO {

	public JSONObject addTransaction(String userAccountNo,
			String counterpartyAccountNo, String name, double amount,String currency);
	public JSONObject getTransactionbyAccount(String AccountNo);
	public JSONObject randomGenerateCashflow( String currency);
	public JSONObject deleteTransaction(String transactionID);

}
