
package com.dao;
import java.sql.Date;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public interface UserTransactionDetailsDAO {

public JSONObject addTransaction(String transactionID,String userAccountNo, String counterpartyAccountNo, String date,double amount,String currency);
public JSONObject getTransactionbyAccount(String AccountNo);
public void randomGenerateCashflow();
public JSONObject deleteTransaction(String transactionID);
public JSONObject getCashFlows(String currency, String date);