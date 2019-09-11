package com.pojo;

public class UserTransactionDetails {

	String transactionID;
	String userAccountNo;
	String counterpartyAccountNo;
	String date;
	String currency;
	double amount;
	public UserTransactionDetails(String userAccountNo, String counterpartyAccountNo, String date,
			String currency, double amount) {
		this.userAccountNo = userAccountNo;
		this.counterpartyAccountNo = counterpartyAccountNo;
		this.date = date;
		this.currency = currency;
		this.amount = amount;
	}
	public UserTransactionDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
