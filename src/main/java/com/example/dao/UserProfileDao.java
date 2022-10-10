package com.example.dao;

import java.util.List;

import com.example.model.Groups;
import com.example.model.UserProfile;


public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
	
	void saveGroup(Groups group);
	
	public Groups isGroupSSOUnique(String type);
	
	List<Groups> findGroups();
}
