package com.techllenapps.cardealershipconsoleapp.entities;

import java.io.Serializable;

public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String userName;
	private String passWord;
	private String userRole;
	private enum UserRole{Admin,Customer,Employee};
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String userName, String passWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
	}
	
	public User(String userName, String passWord,String userRole) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.userRole = userRole;

	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	@Override
	public String toString() {
		return "User [userName=" + userName + ", passWord=" + passWord + ", userRole=" + userRole + "]";
	}

	

}
