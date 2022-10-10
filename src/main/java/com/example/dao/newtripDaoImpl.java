/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.model.Newtrip;


/**
 *
 * @author glodeveloper
 */
@Repository("newtripDao")
public class newtripDaoImpl extends AbstractDao<Integer, Newtrip> implements newtripDao {

	static final Logger logger = LoggerFactory.getLogger(newtripDaoImpl.class);
	
	

        @SuppressWarnings("deprecation")
		@Override
	public void Savetrip(Newtrip newtrip) {
            
            Date date=new Date();
             newtrip.setTripunique(newtrip.getTripname()+"-"+date.getTime()/1000);
             newtrip.setMonthwise(date.getMonth()+"/"+date.getYear());
		persist(newtrip);
	}
        public Newtrip findBySSO(String sso) {
		logger.info("SSO : {}", sso);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("tripunique", sso));
		Newtrip newtrip = (Newtrip)crit.addOrder(Order.asc("id"));
		
		return newtrip;
	}
         public Newtrip findById(String trip) {
		logger.info("tripunique : {}", trip);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("tripunique", trip));
		Newtrip newtrip = (Newtrip)crit.uniqueResult();
		           System.out.println("ERREEEEE"+newtrip);
		return newtrip;
	}
         
         public List <Newtrip>findTripByTripunique(String trip){
             
                logger.info("tripunique : {}", trip);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("tripunique", trip));
		List<Newtrip> newtrip = (List<Newtrip>) crit.list();
		return newtrip;
             
         }
         
          public List<Newtrip>  findByUserId(String  username) {
		logger.info("username : {}", username);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("userid", username));
		List<Newtrip> newtrip = (List<Newtrip>) crit.list();
		
		return newtrip;
	}
         
         
        @SuppressWarnings("unchecked")
	public List<Newtrip> findAllTrips() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Newtrip> newtrip = (List<Newtrip>) criteria.list();
		
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}*/
		return newtrip;
	}
        


	
}
    

