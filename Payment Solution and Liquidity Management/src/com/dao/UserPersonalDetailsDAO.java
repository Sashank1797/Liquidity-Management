package com.dao;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public interface UserPersonalDetailsDAO {

	public JSONObject addUser(String data) throws ParseException;
	public JSONObject displayUser(String data) throws ParseException;
}
