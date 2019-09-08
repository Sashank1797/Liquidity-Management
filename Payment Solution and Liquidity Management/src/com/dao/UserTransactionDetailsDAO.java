package com.dao;
import java.sql.Date;
import java.util.List;

import com.pojo.UserTransactionDetails;

public interface UserTransactionDetailsDAO {

public boolean addTransaction(long transactionID,long userAccountNo, long counterpartyAccountNo, Date date,double amount,String currency);
public List<UserTransactionDetails> getTransactionbyAccount(long AccountNo);
public void randomGenerateCashflow();
public List<UserTransactionDetails> getTransactionbyDate(Date date);
public List<UserTransactionDetails> deleteTransaction(long transactionID);


}
