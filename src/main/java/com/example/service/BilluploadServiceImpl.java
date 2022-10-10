/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.dao.AbstractDao;
import com.example.dao.billUploadDao;
import com.example.dao.newtripDaoImpl;
import com.example.model.AjaxResponseBody;
import com.example.model.Billupload;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("BilluploadService")
@Transactional
public class BilluploadServiceImpl extends AbstractDao<Integer, Billupload> implements BilluploadService {

    static final Logger logger = LoggerFactory.getLogger(newtripDaoImpl.class);
    @Autowired(required = true)
    private billUploadDao billDao;

    @Override
    public Boolean saveBill(Billupload billupload) {
        Boolean status = true;
        try {

            billDao.saveBill(billupload);
            status = true;
        } catch (Exception e) {
            status = false;
        }
        return status;
    }

    public Billupload findById(int id) {
        logger.info("ID : {}", id);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        Billupload bill = (Billupload) crit.uniqueResult();

        return bill;
    }

}
