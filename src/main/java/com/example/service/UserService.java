package com.example.service;

import java.util.ArrayList;
import java.util.List;

import com.example.model.Groups;
import com.example.model.User;


public interface UserService {
	
	User findById(int id);
	
	User findBySSO(String sso);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserBySSO(String sso);

	List<User> findAllUsers(); 
	
	boolean isUserSSOUnique(Integer id, String sso);
	
	public ArrayList<String>  getSubUsers(String loginID);

}