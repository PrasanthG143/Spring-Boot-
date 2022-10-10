package com.example.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddressByLatLng {

		// TODO Auto-generated method stub

		
		public static String getAddressByLatLng(String lat, String lng) {
	        try {
	            URL url = new URL("http://maps.glovision.co/nominatim/reverse?format=json&limit=1&accept-language=en&addressdetails=1&zoom=18&email=&lat=" + lat + "&lon=" + lng);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	            conn.setRequestMethod("GET");
	            //conn.setRequestProperty("Accept", "application/json");

	            if (conn.getResponseCode() != 200) {
	                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
	            }
	            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

	            String output = "", full = "";
	            while ((output = br.readLine()) != null) {
	                // System.out.println("jhhhhhhhhhhhhhhhhhdsajfhdsaj"+output);
	                full += output;
	            }

	            int indxs = full.indexOf("display_name");
	            int indxe = full.indexOf("address");

	            String address = full.substring(indxs + 15, indxe - 3);
	            return address;
	        } catch (Exception es) {
	            return "";
	        }
	    
	}

}
