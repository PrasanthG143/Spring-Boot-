package com.example.common;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.MailSender;
//import org.springframework.mail.SimpleMailMessage;


public class SMS {
	@Autowired
	//private MailSender crunchifymail;

		public void Sms(){

				try {

				String recipient = "9676412230";

				String message = "Hey... ! Srinivas Hw r u...??";

				String username = "admin";

				String password = "abc123";

				String originator = "";
			//	String requestUrl="http://bhashsms.com/api/sendmsg.php?user=Daiwiksoft&pass=Daiwik2612&sender=daiwik&phone=".$no."&text=".$txt."&priority=ndnd&stype=normal";


				String requestUrl  = "http://bhashsms.com/api/sendmsg.php?user=Daiwiksoft&pass=Daiwik2612&sender=daiwik&phone="+recipient+"&text="+message+"&priority=ndnd&stype=normal"

				/* "username=" + URLEncoder.encode(username, "UTF-8") +

				 "&password=" + URLEncoder.encode(password, "UTF-8") +

				 "&recipient=" + URLEncoder.encode(recipient, "UTF-8") +

				 "&messagetype=SMS:TEXT" +

				 "&messagedata=" + URLEncoder.encode(message, "UTF-8") +

				 "&originator=" + URLEncoder.encode(originator, "UTF-8") +

				 "&serviceprovider=GSMModem1" +

				 "&responseformat=html"*/;

				System.out.println(requestUrl);
				URL url = new URL(requestUrl);

				HttpURLConnection uc = (HttpURLConnection)url.openConnection();

				System.out.println(uc.getResponseMessage());

				uc.disconnect();

				} catch(Exception ex) {

				System.out.println(ex.getMessage());

				}

				}
				/*
				 * public void Email(String fromAddress,String toAddress,String subject,String
				 * body){
				 * 
				 * SimpleMailMessage crunchifyMsg = new SimpleMailMessage();
				 * crunchifyMsg.setFrom(fromAddress); crunchifyMsg.setTo(toAddress);
				 * crunchifyMsg.setSubject(subject); crunchifyMsg.setText(body);
				 * System.out.println("After Calling: =="+crunchifyMsg);
				 * crunchifymail.send(crunchifyMsg); }
				 */
		
		public static void main(String[] args){
			SMS s=new SMS();
			List<Map<String,String>> h=new ArrayList<>();
			 Map<String,String> test=new HashMap<>();
			 test.put("prasanth", "hyderabad");
			 test.put("vamsi", "banglore");
			 
			h.add(0, test);
			
			System.out.println(h.get(0).get("prasanth"));
			
			//s.Sms();
			
		}
		
	
}
