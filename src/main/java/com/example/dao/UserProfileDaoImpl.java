package com.example.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.model.Groups;
import com.example.model.User;
import com.example.model.UserProfile;



@Repository("userProfileDao")
public class UserProfileDaoImpl extends AbstractDao<Integer, UserProfile> implements UserProfileDao{

	
	@Autowired
	SessionFactory sessionFactory;
	
	public UserProfile findById(int id) {
		return (UserProfile) getByKey(id);
	}

	public UserProfile findByType(String type) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("type", type));
		return (UserProfile) crit.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<UserProfile> findAll(){
		Criteria crit = createEntityCriteria();
		crit.addOrder(Order.asc("type"));
		System.out.println("============="+(List<UserProfile>)crit.list());
		return (List<UserProfile>)crit.list();
		
	}
	
	public void saveGroup(Groups group){
	System.out.println("============"+group);
	Session session=sessionFactory.openSession();
	session.save(group);
		
	}
	
	
	public Groups isGroupSSOUnique(String type) {
		// TODO Auto-generated method stub
		System.out.println("RECHAEDDDDDDDDDDDD+++++++++++++++++++++====="+type);
		Session session=sessionFactory.openSession();
		Criteria crit = session.createCriteria(Groups.class);
		crit.add(Restrictions.gt("groupName", type));
		System.out.println("CRIR"+crit);
		return (Groups) crit.uniqueResult();
	}
	public List<Groups> findGroups(){
		 Session session=sessionFactory.openSession();
			Criteria crit = session.createCriteria(Groups.class);
			crit.addOrder(Order.asc("id"));
			crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			List<Groups> ls = (List<Groups>) crit.list();
				
			for (Groups groups : ls) {
				System.out.println(ls);

			}
			
			return ls;
		
	}
	
	
}
