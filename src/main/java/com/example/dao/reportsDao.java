/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;

import java.util.List;
import java.util.Map;

import com.example.model.Billupload;
import com.example.model.Newtrip;

/**
 *
 * @author glodeveloper
 */
public interface reportsDao {
    
    public Map<String, List<Map<String, List<Object>>>> getResults(String name,String tripunique);
    public  List<Billupload> getDetails(String tripunique);
    public List<Newtrip> getTrips(String uname);
    public List<Object[]> getDashboard();
    public List<Object[]> getCategoryWise();
    public List<Billupload> getBills(String start,String end);
    
}
