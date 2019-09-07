package com.pojo;

public class UserLogin {

	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public UserLogin() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public UserLogin(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
}
