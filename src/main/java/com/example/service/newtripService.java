/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import java.util.List;

import com.example.model.Newtrip;

/**
 *
 * @author glodeveloper
 */
public interface newtripService {   

    Newtrip findBySSO(String sso);

    Newtrip findById(String id);
       
    List<Newtrip> findTripByTripunique(String tripunique);

    void updateTrip(Newtrip newtrip);

    void Savetrip(Newtrip newtrip);

    List<Newtrip> findAllTrips();
    List<Newtrip> findByUserId(String username);


}
