package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.UserProfileDao;
import com.example.model.Groups;
import com.example.model.User;
import com.example.model.UserProfile;


@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService{
	
	@Autowired
	UserProfileDao dao;
	
	public UserProfile findById(int id) {
		return dao.findById(id);
	}

	public UserProfile findByType(String type){
		return dao.findByType(type);
	}

	public List<UserProfile> findAll() {
		return dao.findAll();
	}
	
	public void saveGroup(Groups group){
		dao.saveGroup(group);
	}
	public boolean isSSOUnique(String sso) {
		Groups group = isGroupSSOUnique(sso);
		return ( group == null);
	}

	
	private Groups isGroupSSOUnique(String sso) {
		// TODO Auto-generated method stub
		return dao.isGroupSSOUnique(sso);
	}
	public List<Groups> findGroups(){
		
		return dao.findGroups();
	}

	
	

}
