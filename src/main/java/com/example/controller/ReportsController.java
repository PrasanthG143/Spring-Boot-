/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controller;

import com.example.model.Billupload;
import com.example.model.Newtrip;
import com.example.model.User;
import com.example.service.UserProfileService;
import com.example.service.UserService;
import com.example.service.reportService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author glodeveloper
 */
@MultipartConfig
@Controller
@SessionAttributes("roles")
public class ReportsController {
	@Autowired
	HttpSession httpSession;
   
	@Autowired
    reportService repService;

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    @RequestMapping(value = {"reports"}, method = RequestMethod.GET)
    public String Reports(ModelMap model) {
    	ArrayList<String> aal=new ArrayList<>();
    	ArrayList<String> aalnew=new ArrayList<>();
    	 aal.add(getPrincipal());
    	 aalnew=userService.getSubUsers(getPrincipal());
    	 System.out.println("SIZE======"+aalnew.size()+"========="+aalnew.get(0));
    	 if(aalnew.get(0)!=""){
        for (int s=0; s<aalnew.size();s++) {
        	aal.add(aalnew.get(s));
		}
    	 }
        
        model.addAttribute("loggedinuser",aal);
       
        System.out.println(httpSession.getAttribute("userid")+"__________"+getPrincipal());
        
       
        return "reports";
    }
    
    @RequestMapping(value={"getTrips"},method=RequestMethod.GET)
    @ResponseBody
    List<Newtrip> GetTrips(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	 List<Newtrip> Newtrip=repService.getTrips(request.getParameter("userid"));
    	 System.out.println(Newtrip);
    	return Newtrip;
    }
    @RequestMapping(value="instantBills",method=RequestMethod.GET)
    public String BillsPage(ModelMap model){
    	
    	return "bills";
    }
    
    @RequestMapping(value="bills",method=RequestMethod.GET)
    public String Bills(ModelMap model,HttpServletRequest req){
    	String start=req.getParameter("start");
    	String end=req.getParameter("end");
    	repService.getBills(start,end);
    	model.addAttribute("bills", repService.getBills(start,end));
    	return "bills";
    }

    @RequestMapping(value = {"/getReports"}, method = RequestMethod.GET)
    String getReports(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {

        // System.out.println("------"+request.getParameter("reporttype"));
        System.out.println("UserId" + request.getParameter("userid"));
        String name = request.getParameter("userid");
        String tripunique=request.getParameter("tripunique");
        Map<String, List<Map<String, List<Object>>>> list = new HashMap<>();
        try {

            list = repService.getResults(name,tripunique);
            System.out.println("==="+list);
            //  System.out.println("RESULT"+repService.getResults(request.getParameter("userid")));
        } catch (Exception e) {
            System.out.println("EXCEPtion AT CONTrOller" + e.getMessage());
        }

        model.addAttribute("result", list);
        model.addAttribute("loggedinuser", getPrincipal());
       //out.println(list);
        return "allreports";
    }

    @RequestMapping(value = {"Details"}, method = RequestMethod.GET)
    String GetDetails(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {

        model.addAttribute("loggedinuser", getPrincipal());
        String tripunique = request.getParameter("tripunique");
        List<Billupload> ls = null;
        try {
            ls = repService.getDetails(tripunique);
        } catch (Exception e) {

        }
        System.out.println("WHILE RESEND"+ls);
        model.addAttribute("details", ls);
        return "reportDetails";
    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("======"+principal);
        if (principal instanceof UserDetails) {
        	System.out.println(((UserDetails) principal).getUsername());
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }
}
