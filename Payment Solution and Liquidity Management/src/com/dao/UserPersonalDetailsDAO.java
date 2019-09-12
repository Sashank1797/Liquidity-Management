package com.dao;

import org.json.simple.JSONObject;

public interface UserPersonalDetailsDAO {

	public JSONObject addUser(String name, String contact, String emailID);
	public JSONObject displayUser(int userID);
}
