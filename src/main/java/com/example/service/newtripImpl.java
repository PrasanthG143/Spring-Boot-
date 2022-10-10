/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.dao.newtripDao;
import com.example.model.Newtrip;
import com.example.model.User;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author glodeveloper
 */
@Service("newtripService")
@Transactional
public  class newtripImpl implements newtripService {

    @Autowired
    private newtripDao newDao;

    /**
     *
     *
     */
    public Newtrip findBySSO(String sso) {
        Newtrip newtrip = newDao.findBySSO(sso);
        
        return newtrip;
    }

    @Override
    public void Savetrip(Newtrip newtrip) {
        newDao.Savetrip(newtrip);
      
        System.out.println(newtrip + "PRASANTH----");

    }

    public List<Newtrip> findAllTrips() {
        return newDao.findAllTrips();
    }
       public List<Newtrip> findByUserId(String username)
       {
           return newDao.findByUserId(username);
       }

       public List<Newtrip> findTripByTripunique(String tripunique){
          
           return newDao.findTripByTripunique(tripunique);
       }
    
    public Newtrip findById(String id){
        Newtrip newtrip=newDao.findById(id);
        return newtrip;
    }
    
    /**
     *
     * @param newtrip
     */
    public void updateTrip(Newtrip newtrip) {
		Newtrip entity = newDao.findById(newtrip.getTripunique());
		if(entity!=null){
			entity.setId(newtrip.getId());
			entity.setAccountname(newtrip.getAccountname());
                        entity.setEstimatedamount(newtrip.getEstimatedamount());
		
		}
                
	}

}
