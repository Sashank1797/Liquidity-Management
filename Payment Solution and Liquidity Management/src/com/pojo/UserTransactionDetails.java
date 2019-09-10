package com.pojo;

import java.sql.Date;

public class UserTransactionDetails {

	int transactionID;
	String userAccountNo;
	String counterpartyAccountNo;
	Date date;
	double amount;
	String currency;
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public long getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	public String getUserAccountNo() {
		return userAccountNo;
	}
	public void setUserAccountNo(String userAccountNo) {
		this.userAccountNo = userAccountNo;
	}
	public String getCounterpartyAccountNo() {
		return counterpartyAccountNo;
	}
	public void setCounterpartyAccountNo(String counterpartyAccountNo) {
		this.counterpartyAccountNo = counterpartyAccountNo;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public UserTransactionDetails(int transactionID, String userAccountNo, String counterpartyAccountNo, Date date,
			double amount,String currency) {
		super();
		this.transactionID = transactionID;
		this.userAccountNo = userAccountNo;
		this.counterpartyAccountNo = counterpartyAccountNo;
		this.date = date;
		this.amount = amount;
		this.currency=currency;
	}
	public UserTransactionDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
