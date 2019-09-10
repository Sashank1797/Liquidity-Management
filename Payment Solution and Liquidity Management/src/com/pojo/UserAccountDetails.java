package com.pojo;

public class UserAccountDetails {

	private String accountNoUSD;
	private String accountNoGBP;
	private String accountNoEUR;
	private String baseCurrency;
	private double amountPayable;
	private double amountReceivable;
	private double balanceUSD;
	private double balanceGBP;
	private double balanceEUR;
	public String getAccountNoUSD() {
		return accountNoUSD;
	}
	public void setAccountNoUSD(String accountNoUSD) {
		this.accountNoUSD = accountNoUSD;
	}
	public String getAccountNoGBP() {
		return accountNoGBP;
	}
	public void setAccountNoGBP(String accountNoGBP) {
		this.accountNoGBP = accountNoGBP;
	}
	public String getAccountNoEUR() {
		return accountNoEUR;
	}
	public void setAccountNoEUR(String accountNoEUR) {
		this.accountNoEUR = accountNoEUR;
	}
	public String getBaseCurrency() {
		return baseCurrency;
	}
	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}
	public double getAmountPayable() {
		return amountPayable;
	}
	public void setAmountPayable(double amountPayable) {
		this.amountPayable = amountPayable;
	}
	public double getAmountReceivable() {
		return amountReceivable;
	}
	public void setAmountReceivable(double amountReceivable) {
		this.amountReceivable = amountReceivable;
	}
	public double getBalanceUSD() {
		return balanceUSD;
	}
	public void setBalanceUSD(double balanceUSD) {
		this.balanceUSD = balanceUSD;
	}
	public double getBalanceGBP() {
		return balanceGBP;
	}
	public void setBalanceGBP(double balanceGBP) {
		this.balanceGBP = balanceGBP;
	}
	public double getBalanceEUR() {
		return balanceEUR;
	}
	public void setBalanceEUR(double balanceEUR) {
		this.balanceEUR = balanceEUR;
	}
	public UserAccountDetails(String accountNoUSD, String accountNoGBP, String accountNoEUR, String baseCurrency,
			double amountPayable, double amountReceivable, double balanceUSD, double balanceGBP, double balanceEUR) {
		this.accountNoUSD = accountNoUSD;
		this.accountNoGBP = accountNoGBP;
		this.accountNoEUR = accountNoEUR;
		this.baseCurrency = baseCurrency;
		this.amountPayable = amountPayable;
		this.amountReceivable = amountReceivable;
		this.balanceUSD = balanceUSD;
		this.balanceGBP = balanceGBP;
		this.balanceEUR = balanceEUR;
	}
	
	public UserAccountDetails() {
		super();
	}
	
}
