/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.dao.reportsDao;
import com.example.model.Billupload;
import com.example.model.Newtrip;

import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author glodeveloper
 */
@Service("reportService")
@Transactional
public class reportServiceImpl implements reportService{
    @Autowired
    private reportsDao reportDao;
    
    public Map<String, List<Map<String, List<Object>>>> getResults(String name,String tripunique){
        
        Map<String, List<Map<String, List<Object>>>> ls= reportDao.getResults(name,tripunique);
      
        
        return ls;
    }
    public  List<Billupload> getDetails(String tripunique){
        List<Billupload> lss=reportDao.getDetails(tripunique);
        return lss;
        
    }
    public List<Newtrip> getTrips(String uname){
    	List<Newtrip> ls=reportDao.getTrips(uname);
    	
    	return ls;
    }
    public List<Object[]> getDashboard(){
    	List<Object[]> ls=reportDao.getDashboard();
    	
    	return ls;
    }
    public List<Object[]> getCategoryWise(){
    	
    	List<Object[]> ls=reportDao.getCategoryWise();
     	
     	return ls;
    }
    
    
    public List<Billupload> getBills(String start,String end){
    	
List<Billupload> ls=reportDao.getBills(start,end);
     	
     	return ls;
    }
  
    
}
