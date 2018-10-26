package helper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

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
}