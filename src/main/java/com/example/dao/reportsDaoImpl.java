/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;

import java.io.Serializable;
import static java.lang.Math.E;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.model.Billupload;
import com.example.model.Newtrip;

/**
 *
 * @author glodeveloper
 */
@Repository("reportsDao")
public class reportsDaoImpl extends AbstractDao<Serializable, Object> implements reportsDao {

	static final Logger logger = LoggerFactory.getLogger(reportsDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	HttpSession httpSession;

	public Map<String, List<Map<String, List<Object>>>> getResults(String name, String tripunique) {
		Session session = sessionFactory.openSession();

		Object[] a = null;
		List<Newtrip> newtrips = null;
		List<String> billdetails = null;
		Map<Newtrip, Object[]> result = new HashMap<>();
		List<Object[]> bill = null;

		Map<String, List<Map<String, List<Object>>>> finalResult = new HashMap<>();
		Map<String, Object> finalResult1 = new HashMap<>();
		Map<String, Object> finalResult2 = new HashMap<>();

		int cmpsum = 0;
		int selfsum = 0;
		String temquery = "";
		String groups = "glovision";
		List<String> al = new ArrayList<>();

		al.add("glovision");
		if (name == "" || name == null) {
			name = (String) httpSession.getAttribute("userid");
		}
		System.out.println("TripID" + tripunique + "=======" + name);

		try {
			// javax.persistence.Query query = em.createQuery("select person
			// from Person person where person.name = :name");
			if (tripunique == "select" || tripunique.equalsIgnoreCase("select")) {
				System.out.println("If Condition" + tripunique);
				temquery = "from Newtrip where userid='" + name + "'";
				System.out.println("Query" + temquery);
			} else {
				temquery = "from Newtrip where userid='" + name + "' and tripunique='" + tripunique + "'";

			}
			org.hibernate.Query newInfoQuery = session.createQuery(temquery);
			newtrips = (List<Newtrip>) newInfoQuery.list();
			// result.add(newtrips);
			System.out.println("ADDDED" + newtrips.size());

			List<Map<String, List<Object>>> trips = new ArrayList<>();
			for (int i = 0; i < newtrips.size(); i++) {

				// System.out.println("Trips=====" + newtrips.toArray()[i]);
				Newtrip s = (Newtrip) newtrips.get(i);

				List<Object> info = new ArrayList();
				info.add(s.getUserid());
				info.add(s.getDateofjourney());
				info.add(s.getAdvanceamount());
				info.add(s.getNoofnights());
				info.add(s.getStatus());
				info.add(s.getPaymentstatus());
				info.add(s.getTripstatus());
				info.add(s.getTripunique());
				info.add(s.getSettleamount());
				info.add(s.getAssigngroup());
				info.add(s.getTripname());
				info.add(s.getBillamount());
				System.out.println("TRIP NAME" + s.getTripname());

				String totbillquery = "select sum(billamount),billpaid from Billupload where userid = '" + name
						+ "' and tripunique ='" + s.getTripunique() + "' group by billpaid";
				org.hibernate.Query totbillQuery = session.createQuery(totbillquery);

				bill = (List<Object[]>) totbillQuery.list();

				if (bill.size() > 0) {

					for (Object[] values : bill) {

						System.out.println("BILL AMOUNT:" + (String) values[0] + ", TYPE:" + (String) values[1] + "");
						String billpaid = (String) values[1];
						String x=(String)values[0];
						try{
						
						if (billpaid == "Company Paid") {
						//	cmpsum += Integer.parseInt((String) values[0]);
							cmpsum +=Integer.parseInt(x);
							System.out.println("CMP" + cmpsum);
						} else {
							selfsum += Integer.parseInt(x);
							System.out.println("sesum" + selfsum);
						}
						} catch(NumberFormatException ex){ // handle your exception
						    System.out.println("exception is"+ex.getMessage());
						}
					}
				}

				info.add(cmpsum);// adding additionally to Trip
				info.add(selfsum);

				Map<String, List<Object>> billmap = new HashMap<String, List<Object>>();

				billmap.put("info", info);
				// billmap.put(s.getTripunique(), bill);
				trips.add(billmap);
				selfsum = 0;
				cmpsum = 0;

			}
			finalResult.put(name, trips);
			// newtrips=newtripInfo;
		} catch (RuntimeException re) {

			re.printStackTrace();

			throw re;
		}

		System.out.println("While Return" + finalResult);
		return finalResult;

	}

	public List<Billupload> getDetails(String tripunique) {
		List<Billupload> ls = null;
		Session session = sessionFactory.openSession();
		try {
			String temquery = "from Billupload where tripunique='" + tripunique + "'";
			org.hibernate.Query newInfoQuery = session.createQuery(temquery);
			ls = (List<Billupload>) newInfoQuery.list();
			System.out.println("DETAILS" + ls);
			// result.add(newtrips);
		} catch (Exception e) {
			System.out.println("EXCEPTION IN DAO" + e.getMessage());
		}
		return ls;

	}

	public List<Newtrip> getTrips(String uname) {
		List<Newtrip> ls = null;
		Session session = sessionFactory.openSession();
		try {
			String temquery = "from Newtrip where userid='" + uname + "'";
			org.hibernate.Query newInfoQuery = session.createQuery(temquery);
			ls = (List<Newtrip>) newInfoQuery.list();
			System.out.println("DETAILS" + ls);
			// result.add(newtrips);
		} catch (Exception e) {
			System.out.println("EXCEPTION IN DAO" + e.getMessage());
		}
		return ls;

	}

	public List<Object[]> getDashboard() {
		List<Object[]> listResult = null;
		String name = (String) httpSession.getAttribute("userid");
		Session session = sessionFactory.openSession();
		try {
			String temquery = "select sum(advanceamount),sum(billamount),monthwise from  Newtrip where userid='" + name
					+ "' group by monthwise";
			System.out.println(temquery);

			org.hibernate.Query newInfoQuery = session.createQuery(temquery);
			listResult = newInfoQuery.list();
			System.out.println("DETAILS" + listResult);
			// result.add(newtrips);
		} catch (Exception e) {
			System.out.println("EXCEPTION IN DAO" + e.getMessage());
		}
		return listResult;

	}

	public List<Object[]> getCategoryWise() {
		String name = (String) httpSession.getAttribute("userid");
		List<Object[]> listResult = null;
		Session session = sessionFactory.openSession();
		try {
			String temquery = "select  slct,sum(billamount) from Billupload where userid='" + name + "' group by slct";
			System.out.println(temquery);
			org.hibernate.Query newInfoQuery = session.createQuery(temquery);
			// ls = (List<String>) newInfoQuery.list();
			listResult = newInfoQuery.list();
			/*
			 * for (Object[] aRow : listResult) { String sum = (String) aRow[0];
			 * String category = (String) aRow[1]; System.out.println(category +
			 * " - " + sum);
			 * 
			 * }
			 */

			// result.add(newtrips);
		} catch (Exception e) {
			System.out.println("EXCEPTION IN DAO" + e.getMessage());
		}
		return listResult;

	}

	public List<Billupload> getBills(String start, String end) {
		String name = (String) httpSession.getAttribute("userid");
		List<Billupload> listResult = null;
		Session session = sessionFactory.openSession();
		try {
			String temquery = "from Billupload where userid='" + name + "' and  dateofjourney between '" + start
					+ "' and '" + end + "'";
			System.out.println(temquery);
			org.hibernate.Query newInfoQuery = session.createQuery(temquery);
			// ls = (List<String>) newInfoQuery.list();
			listResult = newInfoQuery.list();
			// result.add(newtrips);
		} catch (Exception e) {
			System.out.println("EXCEPTION IN DAO" + e.getMessage());
		}

		return listResult;
	}

}
