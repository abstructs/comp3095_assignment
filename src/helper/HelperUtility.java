package helper;

public class HelperUtility {
	public static Boolean credentialsSet(String email, String password) {
		return email != null && password != null;
	}
	
	
	public static Boolean formValidator(String email, String password) {
		return email == null || email.trim().equals("") || password == null || password.trim().equals("");
	}
}
