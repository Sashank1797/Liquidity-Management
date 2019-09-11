package com.dao;
import java.sql.Date;
import org.json.simple.JSONObject;

public interface UserTransactionDetailsDAO {

	public JSONObject addTransaction(String transactionID,String userAccountNo, String counterpartyAccountNo, Date date,double amount,String currency);
	public JSONObject getTransactionbyAccount(String AccountNo);
	public JSONObject deleteTransaction(String transactionID);
}
