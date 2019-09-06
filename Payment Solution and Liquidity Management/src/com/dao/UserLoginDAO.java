package com.dao;


public interface UserLoginDAO {

	boolean authenticateUser(String userName, String password);
	boolean updatePassword(String userName, String currentPassword, String newPassword );
	
}