package com.example.model;

import java.io.Serializable;

public enum UserProfileType implements Serializable{
	
	USER("USER"),
	DBA("DBA"),
	ADMIN("ADMIN"),
	MANAGER("MANAGER"),
	EMPLOYEE("EMPLOYEE");
	
	String userProfileType;
	
	private UserProfileType(String userProfileType){
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType(){
		return userProfileType;
	}
	
}
