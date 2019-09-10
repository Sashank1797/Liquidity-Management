package com.dao;

import org.json.simple.JSONObject;

public interface UserLoginDAO {

	JSONObject addUserCredentials(String username, String password);
	JSONObject authenticateUser(String userName, String password);
	JSONObject updatePassword(String userName, String currentPassword, String newPassword );
	
}