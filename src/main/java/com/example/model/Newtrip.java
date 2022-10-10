/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author glodeveloper
 */
@Entity(name="Newtrip")
@Table(name = "tem_newtrip")

public class Newtrip implements Serializable{
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotEmpty
    @Column(name = "userid", unique = true, nullable = false)
    private String userid;

    @NotEmpty
    @Column(name = "tripname", nullable = false)
    private String tripname;

    @NotEmpty
    @Column(name = "designation", nullable = false)
    private String designation;

    @NotEmpty
    @Column(name = "source", nullable = false)
    private String source;

    @NotEmpty
    @Column(name = "estimatedamount", nullable = false)
    private String estimatedamount;
    
    @NotEmpty
    @Column(name = "destination", nullable = false)
    private String destination;
    
    @NotEmpty
    @Column(name = "noofnights", nullable = false)
    private String noofnights;
    
    @NotEmpty
    @Column(name = "accountname", nullable = false)
    private String accountname;
    
    @NotEmpty
    @Column(name = "dateofjourney", nullable = false)
    private String dateofjourney;
    
    @NotEmpty
    @Column(name = "endofjourney", nullable = false)
    private String endofjourney;
    
    public int getBillamount() {
		return billamount;
	}

	public void setBillamount(int billamount) {
		this.billamount = billamount;
	}

	private int billamount;
    
   
    
    String  status, advanceamount, assigngroup, tripunique, tripstatus, paymentstatus, tripclosetime, settleamount, monthwise;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMonthwise() {
        return monthwise;
    }

    public void setMonthwise(String monthwise) {
        this.monthwise = monthwise;
    }

    public String getEndofjourney() {
        return endofjourney;
    }

    public void setEndofjourney(String endofjourney) {
        this.endofjourney = endofjourney;
    }

    public String getSettleamount() {
        return settleamount;
    }

    public void setSettleamount(String settleamount) {
        this.settleamount = settleamount;
    }

    public String getTripstatus() {
        return tripstatus;
    }

    public void setTripstatus(String tripstatus) {
        this.tripstatus = tripstatus;
    }

    public String getPaymentstatus() {
        return paymentstatus;
    }

    public void setPaymentstatus(String paymentstatus) {
        this.paymentstatus = paymentstatus;
    }

    public String getTripclosetime() {
        return tripclosetime;
    }

    public void setTripclosetime(String tripclosetime) {
        this.tripclosetime = tripclosetime;
    }

    public String getTripunique() {
        return tripunique;
    }

    public void setTripunique(String tripunique) {
        this.tripunique = tripunique;
    }

    public String getAssigngroup() {
        return assigngroup;
    }

    public void setAssigngroup(String assigngroup) {
        this.assigngroup = assigngroup;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTripname() {
        return tripname;
    }

    public void setTripname(String tripname) {
        this.tripname = tripname;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getEstimatedamount() {
        return estimatedamount;
    }

    public void setEstimatedamount(String estimatedamount) {
        this.estimatedamount = estimatedamount;
    }

    public String getNoofnights() {
        return noofnights;
    }

    public void setNoofnights(String noofnights) {
        this.noofnights = noofnights;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getDateofjourney() {
        return dateofjourney;
    }

    public void setDateofjourney(String dateofjourney) {
        this.dateofjourney = dateofjourney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdvanceamount() {
        return advanceamount;
    }

    public void setAdvanceamount(String advanceamount) {
        this.advanceamount = advanceamount;
    }
    public MultipartFile getPath1() {
		return path1;
	}

	public void setPath1(MultipartFile path1) {
		this.path1 = path1;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Transient
    @Column(name="path1",nullable=true)
    private MultipartFile path1;
   
	@Column(name="path",nullable=true)
    private String path;
    
    
    
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        //   result = prime * result + ((ssoId == null) ? 0 : ssoId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Newtrip)) {
            return false;
        }
        Newtrip other = (Newtrip) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (tripname == null) {
            if (other.tripname != null) {
                return false;
            }
        } else if (!tripname.equals(other.tripname)) {
            return false;
        }
        return true;
    }

    /*
	 * DO-NOT-INCLUDE passwords in toString function.
	 * It is done here just for convenience purpose.
     */
    
       // System.out.println(new Timestamp(date.getTime()));
        //System.out.println(timestamp.getTime());
    
    //Timestamp timestamp = new Timestamp(System.currentTimeMillis());
       
    @Override
    public String toString() {
    //    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
      //   tripunique=userid+"-"+timestamp.getTime();
        return "tem_newtrip [  userid=" + userid + ", tripname=" + tripname
                + ", source=" + source + ", destination=" + destination
                + ", designation=" + designation + ", estimatedamount=" + estimatedamount
                + ", advanceamount=" + advanceamount + ", tripstatus=" + tripstatus
                + ", status=" + status + ", settleamount=" + settleamount
                + ", tripunique=" +tripunique 
                + ", accountname=" + accountname + ", dateofjourney=" + dateofjourney
                + ", assigngroup=" + assigngroup + ", noofnights=" +noofnights
                + ", endofjourney=" + endofjourney + "]";
    }

}


