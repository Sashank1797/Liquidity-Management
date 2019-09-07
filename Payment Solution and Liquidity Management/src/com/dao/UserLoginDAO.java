package com.dao;


public interface UserLoginDAO {

	boolean addUserCredentials(String username, String password);
	boolean authenticateUser(String userName, String password);
	boolean updatePassword(String userName, String currentPassword, String newPassword );
	
}