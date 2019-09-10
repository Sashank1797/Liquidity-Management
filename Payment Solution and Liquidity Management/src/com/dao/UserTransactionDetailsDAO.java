package com.dao;
import java.sql.Date;
import org.json.simple.JSONObject;

public interface UserTransactionDetailsDAO {

	public JSONObject addTransaction(long transactionID,long userAccountNo, long counterpartyAccountNo, Date date,double amount,String currency);
	public JSONObject getTransactionbyAccount(String AccountNo);
	public JSONObject randomGenerateCashflow();
	public JSONObject getTransactionbyDate(Date date);
	public JSONObject deleteTransaction(long transactionID);

}
