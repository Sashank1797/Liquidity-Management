package com.pojo;

import java.util.Date;

public class UserTransactionDetails {

public UserTransactionDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
long transactionID;
long userAccountNo;
long counterpartyAccountNo;
Date date;
String currency;
double amount;
public long getTransactionID() {
return transactionID;
}
public void setTransactionID(long transactionID) {
this.transactionID = transactionID;
}
public long getUserAccountNo() {
return userAccountNo;
}
public void setUserAccountNo(long userAccountNo) {
this.userAccountNo = userAccountNo;
}
public long getCounterpartyAccountNo() {
return counterpartyAccountNo;
}
public void setCounterpartyAccountNo(long counterpartyAccountNo) {
this.counterpartyAccountNo = counterpartyAccountNo;
}
public Date getDate() {
return date;
}
public void setDate(Date date) {
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
public UserTransactionDetails(long transactionID, long userAccountNo, long counterpartyAccountNo, Date date,
String currency, double amount) {
super();
this.transactionID = transactionID;
this.userAccountNo = userAccountNo;
this.counterpartyAccountNo = counterpartyAccountNo;
this.date = date;
this.currency = currency;
this.amount = amount;
}

}
