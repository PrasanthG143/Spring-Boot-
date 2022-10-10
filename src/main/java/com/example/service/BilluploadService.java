/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.model.AjaxResponseBody;
import com.example.model.Billupload;

/**
 *
 * @author glodeveloper
 */


public interface BilluploadService {
 
  Boolean saveBill(Billupload billupload);
   
  Billupload findById(int id);
            
}
