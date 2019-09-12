
package com.dao;

import java.sql.Date;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public interface UserTransactionDetailsDAO {

	public JSONObject addTransaction(String userAccountNo,String counterpartyAccountNo, String name, double amount,String currency);
	public JSONObject getTransactionbyAccount(String AccountNo);
	public JSONObject randomGenerateCashflow(String currency);
	public JSONObject deleteTransaction(String transactionID);
  public JSONObject getCashFlows(String currency, String date);
}