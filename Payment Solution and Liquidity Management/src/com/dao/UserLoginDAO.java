package com.dao;

import org.json.simple.JSONObject;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public interface UserLoginDAO {

	JSONObject addUserCredentials(String data) throws org.json.simple.parser.ParseException;
	JSONObject authenticateUser(String data) throws org.json.simple.parser.ParseException;
//	JSONObject updatePassword(String userName, String currentPassword, String newPassword );
	
}