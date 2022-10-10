/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.mail.Multipart;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author glodeveloper
 */
@Entity
@Table(name = "tem_uploadbill")
public class Billupload {

    @Id
    private int id;

   
    @NotEmpty
    @Column(name = "userid", nullable = false)
    private String userid;
    
    @NotEmpty
    @Column(name = "billpaid", nullable = false)
    private String billpaid;
     
     @Column(name = "latitude", nullable = true)
    private String latitude;
  
    @Column(name = "longitude", nullable = true)
    private String longitude;
     
    @NotEmpty
    @Column(name = "slct", nullable = false)
    private String slct;
   
    @NotEmpty
    @Column(name = "uploadatonside", nullable = false)
    private String uploadatonside;
    
    @Column(name = "members", nullable = true)
   
    private String members;
    
    @Column(name = "distance", nullable = true)      
    private String distance;
    
    @Column(name = "odometer", nullable = true)
    private String odometer;
    
   
    @Column(name = "billuploaddate", nullable = true)
    private String billuploaddate;
    
    @Column(name = "tripunique", nullable = true)
    private String tripunique;
    
    @Column(name = "status", nullable = true)
    private String status;

    @Column(name = "address", nullable = true)
    private String address;
   
    @Column(name = "repair", nullable = true)
    private String repair;
    
    @Column(name = "permittedamount", nullable = true)
    private int permittedamount;
    @Column(name = "type", nullable = true)
    private String type;
    @Column(name = "Tax", nullable = true)
    private int Tax;
    
    @Column(name = "InitialAmount", nullable = true)
    
    private int InitialAmount;
    @Column(name = "fileName", nullable = true)
    private String fileName;

    
    
    private String dateofjourney;
    
  

    public String getDateofjourney() {
		return dateofjourney;
	}

	public void setDateofjourney(String dateofjourney) {
		this.dateofjourney = dateofjourney;
	}

	public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

  
   

    public String getSlct() {
        return slct;
    }

    public void setSlct(String slct) {
        this.slct = slct;
    }

    public String getBillpaid() {
        return billpaid;
    }

    public void setBillpaid(String billpaid) {
        this.billpaid = billpaid;
    }

    public String getBillamount() {
        return billamount;
    }

    public void setBillamount(String billamount) {
        this.billamount = billamount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUploadatonside() {
        return uploadatonside;
    }

    public void setUploadatonside(String uploadatonside) {
        this.uploadatonside = uploadatonside;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getOdometer() {
        return odometer;
    }

    public void setOdometer(String odometer) {
        this.odometer = odometer;
    }

   

    public String getBilluploaddate() {
        return billuploaddate;
    }

    public void setBilluploaddate(String billuploaddate) {
        this.billuploaddate = billuploaddate;
    }

    public String getTripunique() {
        return tripunique;
    }

    public void setTripunique(String tripunique) {
        this.tripunique = tripunique;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRepair() {
        return repair;
    }

    public void setRepair(String repair) {
        this.repair = repair;
    }

    public int getPermittedamount() {
        return permittedamount;
    }

    public void setPermittedamount(int permittedamount) {
        this.permittedamount = permittedamount;
    }

   

    public int getInitialAmount() {
        return InitialAmount;
    }

    public void setInitialAmount(int InitialAmount) {
        this.InitialAmount = InitialAmount;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    String billamount;
    String description;

    @Override
    public String toString() {
        return "Billupload{" + "userid=" + userid + ", billpaid=" + billpaid + ", latitude=" + latitude + ", longitude=" + longitude + ",slct=" + slct + ", uploadatonside=" + uploadatonside + ", members=" + members + ", distance=" + distance + ", odometer=" + odometer + ", billuploaddate=" + billuploaddate + ", tripunique=" + tripunique + ", status=" + status + ", address=" + address + ", repair=" + repair + ", permittedamount=" + permittedamount + ", type=" + type + ", Tax=" + Tax + ", InitialAmount=" + InitialAmount + ", fileName=" + fileName + ", billamount=" + billamount + ", description=" + description + '}';
    }

  
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

   

    public int getTax() {
        return Tax;
    }

    public void setTax(int Tax) {
        this.Tax = Tax;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static String date(){
    	
    	  java.util.Date today = new java.util.Date();
    	     
          //If you print Date, you will get un formatted output
          System.out.println("Today is : " + today);
       
          //formatting date in Java using SimpleDateFormat
          SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
          String date = DATE_FORMAT.format(today);
          System.out.println("Today in dd-MM-yyyy format : " + date);

        return date;
    }
    

}
