package helper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;
import java.nio.charset.Charset;
import org.json.*;

public class HelperUtility {
	public static Boolean credentialsSet(String email, String password) {
		return email != null && password != null;
	}
	public static Boolean formValidator(String email, String password) {
		return email == null || email.trim().equals("") || password == null || password.trim().equals("");
	}
	
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
				+ "<title>" + title + "</title>"
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
	        return false;
	    }
	}
}
