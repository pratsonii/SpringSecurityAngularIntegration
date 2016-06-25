package com.example;

public class UserDto 
{
	private String userName;
	private String password;
	private String role;
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public User convertToModel(User user)
	{
		if(user == null)
		{
			user = new User();
		}
		
		user.setUserName(userName);
		user.setPassword(password);
		user.setRole(role);
		
		return user;
		
	}
}
