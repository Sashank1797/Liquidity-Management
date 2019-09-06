package com.dao;

public interface UserAccountDetailsDAO {

	public boolean updateAccountBalance(String accountNo, double balance);
	public double poolingFunction(int userID);
	
}