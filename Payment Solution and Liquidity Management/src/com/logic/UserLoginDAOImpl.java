package com.logic;

public class UserLoginDAOImpl {

	public boolean addUserCredentials(String username,String password) {
		boolean authenticate=false;
		String ADD_LOGIN="insert into login values(?,?)";
		PreparedStatement ps;
		try {
		ps = openConnection().prepareStatement(ADD_LOGIN);
		ps.setString(1, username);
		ps.setString(2, password);
		int rows=ps.executeUpdate();
		if(rows>0) {
		isUpdated=true;
		}
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return authenticate;
	}
	public boolean authenticateUser(String userName,String password) {
		boolean authenticate=false;
		return authenticate;
	}
	public boolean updatePassword(String userName,String currentPassword,String newPassword) {
		boolean update=false;
		return update;
	}
}
