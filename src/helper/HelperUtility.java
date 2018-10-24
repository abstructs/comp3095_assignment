package helper;

public class HelperUtility {
	public static Boolean credentialsSet(String email, String password) {
		return email != null && password != null;
	}
}
