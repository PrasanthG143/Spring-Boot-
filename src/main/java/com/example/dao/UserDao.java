package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.model.User;


public interface UserDao {

	User findById(int id);
	
	User findBySSO(String sso);
	
	void save(User user);
	
	void deleteBySSO(String sso);
	
	List<User> findAllUsers();
	
	public  ArrayList<String>  getSubUsers(String loginID);
	

}

