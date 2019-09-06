package com.pojo;

public class UserPersonalDetails {

	private String name;
	
	private String contact;
	private String address;
	private String emailID;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public UserPersonalDetails(String name, String userID, String contact, String address, String emailID) {
		super();
		this.name = name;
		
		this.contact = contact;
		this.address = address;
		this.emailID = emailID;
	}
	public UserPersonalDetails() {
		// TODO Auto-generated constructor stub
		super();
	}

}
