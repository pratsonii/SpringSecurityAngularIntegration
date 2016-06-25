package com.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="Users")
//@JsonTypeInfo(include=As.WRAPPER_OBJECT,use = com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME)
public class User 
{
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "USER_ID")
	private int userId;
	
	@Column(name = "USER_NAME",unique=true)
	private String userName;
	
	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "ROLE")
	private String role;
	public int getUserId() {
		return userId;
	}

//	public void setUserId(int userId) {
//		this.userId = userId;
//	}

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
