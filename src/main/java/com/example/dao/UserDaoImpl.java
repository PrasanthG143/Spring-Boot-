package com.example.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.model.User;



@Repository("userDao")
public  class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {
	
	
	
	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	public User findById(int id) {
		User user = getByKey(id);
		if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	public User findBySSO(String sso) {
		logger.info("SSO : {}", sso);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		User user = (User)crit.uniqueResult();
		System.out.println("User Profileeee"+user);

		if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<User> users = (List<User>) criteria.list();
		
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}*/
		return users;
	}

	public void save(User user) {
		System.out.println("While Saving....");
		System.out.println(user);
		persist(user);
	}

	public void deleteBySSO(String sso) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		User user = (User)crit.uniqueResult();
		delete(user);
	}
	public ArrayList<String> getSubUsers(String loginID){
		   ArrayList<String> subordinateList = new ArrayList<String>();
		//String loginID=(String)httpSession.getAttribute("userid");
		 String fname="";
		 String rname="";
		Map<String ,String> map=new HashMap<String, String>();
		 List<User> user=null;
		Criteria crit = createEntityCriteria();
		List<User> users1 = (List<User>) crit.list();
		for (User user2 : users1) {
			//System.out.println(user2.getFirstName()+"===="+user2.getId());
			fname=user2.getId().toString();
			rname=user2.getReferenceId();
			map.put(this.findUserNames(Integer.parseInt(fname)),findRefereredUser(Integer.parseInt(rname)));

			//System.out.println(user2.getReferenceId()+"======"+user2.getGroupId());
		}
         //map.put("prasanth", "guduru");
		// return user;
		 Object [] keys =map.keySet().toArray();
		//  Object[] key = map.keySet().toArray();
            String users = getusers(keys, map,loginID);
            //System.out.println("Final Users are"+users);
            
            String[] a = users.split("=");
            System.out.println(a);
            for (int i = 0; i < a.length; i++) {
                subordinateList.add(a[i]);
               System.out.println(a[i]+"test");
            }

		 return subordinateList;
	}
	public String getusers( Object [] key, Map<String, String> map, String loginID) {
        String user = "";
         System.out.println(loginID+key.length+"---------------");
        for (int x = 0; x < key.length; x++) {
            System.out.println(key[x].toString() + "safsafasfaf  " + map.get(key[x].toString()));
            String na=map.get(key[x].toString());
            System.out.println(na+"$$$$$$$$"+loginID);
            if (na.equalsIgnoreCase(loginID)) {
             System.out.println(key[x].toString()+" **********"+loginID);
                user = user + "" + key[x].toString() + "=" + getusers(key, map,key[x].toString());

            }
        }
          System.out.println(" **********"+user);
        return user;
    }
	
	@SuppressWarnings("unchecked")
	public String findUserNames(int id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("id", id));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<User> users = (List<User>) criteria.list();
		String names="";
		 for (User user : users) {
				System.out.println(user.getFirstName()+"IN NOR=====");
			names+=user.getSsoId();
		}
		return names;
	}
	
	
	@SuppressWarnings("unchecked")
	public String findRefereredUser(int id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("id", id));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<User> users = (List<User>) criteria.list();
		String names="";
		for (User user : users) {
				System.out.println(user.getSsoId()+"IN REF=====");
				names+=user.getSsoId();
			}
			return names;
	}
}
