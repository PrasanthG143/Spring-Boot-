/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.AjaxResponseBody;
import com.example.model.Billupload;
import com.example.model.Newtrip;

/**
 *
 * @author glodeveloper
 */
@Repository("billUploadDao")
public class billuploadDaoImpl extends AbstractDao<Integer, Billupload> implements billUploadDao {

	static final Logger logger = LoggerFactory.getLogger(newtripDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
    public Boolean saveBill(Billupload bill) {

        Boolean status = true;
        System.out.println("Savin of BILL ----------------" + bill.getBillamount());
        
        Session session = this.sessionFactory.getCurrentSession();
        try {
      
        	Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Newtrip.class);
            criteria.add(Restrictions.like("tripunique", bill.getTripunique()));
            Newtrip emp=  (Newtrip) criteria.uniqueResult();
        	
        	
//        	Newtrip emp = (Newtrip) session.load(Newtrip.class, new String(bill.getTripunique()));
 		System.out.println("Newtrip object loaded. " + emp);
 		// update example
 		int amt=Integer.parseInt(bill.getBillamount());
    		emp.setBillamount(amt);
        session.persist(bill);
 		session.update(emp);

         status = true;
        } catch (Exception e) {
            status = false;
            System.out.println("MESSAGE"+e.getMessage());
        }
        return status;
    }

	public Billupload findById(int sso) {
		logger.info("SSO : {}", sso);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("userid", sso));
		Billupload bill = (Billupload) crit.addOrder(Order.asc("id"));

		return bill;
	}

}
