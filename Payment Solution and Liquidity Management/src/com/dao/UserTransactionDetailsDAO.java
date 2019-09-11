package com.dao;
import java.sql.Date;
import java.util.List;

import javax.print.DocFlavor.STRING;

import com.pojo.UserTransactionDetails;


public interface UserTransactionDetailsDAO {

public boolean addTransaction(String transactionID,String userAccountNo, String counterpartyAccountNo, String date,double amount,String currency);
public List<UserTransactionDetails> getTransactionbyAccount(String AccountNo);
public void randomGenerateCashflow();
//public List<UserTransactionDetails> getTransactionbyDate(Date date);
public List<UserTransactionDetails> deleteTransaction(String transactionID);

}
