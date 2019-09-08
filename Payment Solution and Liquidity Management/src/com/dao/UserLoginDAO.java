package com.dao;


public interface UserLoginDAO {

	boolean addUserCredentials(String username, String password);
	int authenticateUser(String userName, String password);
	boolean updatePassword(String userName, String currentPassword, String newPassword );
	
}