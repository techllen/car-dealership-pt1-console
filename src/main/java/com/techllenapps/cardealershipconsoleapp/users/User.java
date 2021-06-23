package com.techllenapps.cardealershipconsoleapp.users;

public class User {
	
	private String userName;
	private String passWord;
	private String userRole;
	private enum UserRole{Admin,Customer,Employee};
	
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
