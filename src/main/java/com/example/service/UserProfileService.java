package com.example.service;

import java.util.List;

import com.example.model.Groups;
import com.example.model.UserProfile;


public interface UserProfileService {

	UserProfile findById(int id);

	UserProfile findByType(String type);
	
	List<UserProfile> findAll();
	
	void saveGroup(Groups group);
	
	public boolean isSSOUnique(String sso);
	 
	List<Groups> findGroups();

}
