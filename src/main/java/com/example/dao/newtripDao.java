/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;

import java.util.List;

import com.example.model.Newtrip;
import com.example.model.User;

/**
 *
 * @author glodeveloper
 */
public interface newtripDao {

    Newtrip findBySSO(String sso);

    Newtrip findById(String id);
    
    List<Newtrip>findTripByTripunique(String tripunique);

    List<Newtrip> findAllTrips();
    List<Newtrip> findByUserId(String username);


    void Savetrip(Newtrip newtrip);

}
