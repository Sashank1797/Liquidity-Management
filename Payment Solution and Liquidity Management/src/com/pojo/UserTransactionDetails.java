package com.pojo;

import java.sql.Date;

public class UserTransactionDetails {

String transactionID;
String userAccountNo;
String counterpartyAccountNo;
Date date;
String currency;
double amount;
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
public UserTransactionDetails(String transactionID, String userAccountNo, String counterpartyAccountNo, Date date,
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
