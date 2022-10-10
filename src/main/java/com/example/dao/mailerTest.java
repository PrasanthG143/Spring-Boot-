package com.example.dao;

import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.MailSender;
//import org.springframework.mail.SimpleMailMessage;


/**
 * @author Crunchify.com
 * 
 */
 
public class mailerTest {
 
//	@Autowired
	//private MailSender crunchifymail; // MailSender interface defines a strategy
										// for sending simple mails
	/*
	 * public void crunchifyReadyToSendEmail(String toAddress, String fromAddress,
	 * String subject, String msgBody) {
	 * 
	 * 
	 * System.out.println("to"+toAddress+"from"+fromAddress+"sub"+subject+"BODY"+
	 * msgBody); SimpleMailMessage crunchifyMsg = new SimpleMailMessage();
	 * crunchifyMsg.setFrom(fromAddress); crunchifyMsg.setTo(toAddress);
	 * crunchifyMsg.setSubject(subject); crunchifyMsg.setText(msgBody);
	 * crunchifymail.send(crunchifyMsg);
	 * 
	 * }
	 * 
	 */
	 public void SMS(){
		
	

			try {

			String recipient = "+919667412230";

			String message = " Greetings from Mr. Gupta! Have a nice day!";

			String username = "admin";

			String password = "abc123";

			String originator = "";
		//	String requestUrl="http://bhashsms.com/api/sendmsg.php?user=Daiwiksoft&pass=Daiwik2612&sender=daiwik&phone=".$no."&text=".$txt."&priority=ndnd&stype=normal";


			String requestUrl  = "http://bhashsms.com/api/sendmsg.php?user=Daiwiksoft&pass=Daiwik2612&sender=daiwik&"
					+ "phone="+recipient+"&text="+message+"&priority=ndnd&stype=normal";

			/* "username=" + URLEncoder.encode(username, "UTF-8") +

			 "&password=" + URLEncoder.encode(password, "UTF-8") +

			 "&recipient=" + URLEncoder.encode(recipient, "UTF-8") +

			 "&messagetype=SMS:TEXT" +

			 "&messagedata=" + URLEncoder.encode(message, "UTF-8") +

			 "&originator=" + URLEncoder.encode(originator, "UTF-8") +

			 "&serviceprovider=GSMModem1" +

			 "&responseformat=html";*/

			URL url = new URL(requestUrl);

			HttpURLConnection uc = (HttpURLConnection)url.openConnection();

			System.out.println(uc.getResponseMessage());

			uc.disconnect();

			} catch(Exception ex) {

			System.out.println(ex.getMessage());

			}

			}
	
}