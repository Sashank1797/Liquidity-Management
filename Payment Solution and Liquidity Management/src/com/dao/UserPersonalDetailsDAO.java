package com.dao;

import com.pojo.UserPersonalDetails;

public interface UserPersonalDetailsDAO {

	public boolean addUser( String name, String contact, String address, String emailID );
	public boolean deleteUser(int userID);
	public UserPersonalDetails displayUser(int userID);
}
