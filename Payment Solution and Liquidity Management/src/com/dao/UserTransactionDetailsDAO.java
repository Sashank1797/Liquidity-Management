package com.dao;
import java.sql.Date;
import java.util.List;

import com.pojo.UserTransactionDetails;


public interface UserTransactionDetailsDAO {

	public boolean addTransaction(long userAccountNo, long counterpartyAccountNo, Date date,double amount);
	public List<UserTransactionDetails> getTransactionbyAccount(long AccountNo);
	public void randomGenerateCashflow();
	public List<UserTransactionDetails> getTransactionbyDate(Date date);
	public boolean recordTransaction();
	
	
}
