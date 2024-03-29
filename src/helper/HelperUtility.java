//*********************************************************************************
//* Project: Lentil
//* Assignment: Assignment # 1
//* Author(s): Andrew Wilson, Justin Rolnick, Veronica Cheren, Karina Gorkova, Dindyal Mursingh
//* Student Number: 101069680, 100407074, 100831208, 101032995, 101083659
//* Date: October 29, 2018
//* Description: The class that handles logic and computation to reduce redundancy and keep the servlets lightweight.
//*********************************************************************************



package helper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import org.json.*;

import javax.servlet.http.HttpSession;

public class HelperUtility {
	
	public static boolean isAuthenticated(HttpSession session) {
		return session.getAttribute("email") != null;
	}
	
	public static Boolean credentialsSet(String email, String password) {
		return email != null && password != null;
	}
	public static Boolean formValidator(String email, String password) {
		return email == null || email.trim().equals("") || password == null || password.trim().equals("");
	}
	
	// converts a string to a sha256 hash
	public static String sha256(String password, String salt) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			
			return new String(digest.digest(password.concat(salt).getBytes(StandardCharsets.UTF_8)));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String printHead(String title) {
		return("<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<meta charset='UTF-8'>"
				+ "<title>Lentil - " + title + "</title>"
				+ "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>"
				+ "<link rel='stylesheet' href='styles.css'>"
				+ "</head>");
	}

	public static boolean isCaptchaValid(String secretKey, String response) {
	    try {
	        String url = "https://www.google.com/recaptcha/api/siteverify?"
	                + "secret=" + secretKey
	                + "&response=" + response;
	        
	        InputStream res = new URL(url).openStream();
	        BufferedReader rd = new BufferedReader(new InputStreamReader(res, Charset.forName("UTF-8")));
	        StringBuilder sb = new StringBuilder();
	        int cp;
	       
	        while ((cp = rd.read()) != -1) {
	            sb.append((char) cp);
	        }
	        String jsonText = sb.toString();
	        res.close();

	        JSONObject json = new JSONObject(jsonText);
	        
	        return json.getBoolean("success");
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return false;
	    }
	}
}
