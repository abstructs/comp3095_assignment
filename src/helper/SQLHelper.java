package helper;

import java.sql.*;

public class SQLHelper {
	public static boolean emailExists(Connection connection, String email) {
		//TODO: query database for email (case insensitive)
		return false;
	}
	
	public static boolean passwordsMatch(String password, String passwordConfirm) {
		return password.equals(passwordConfirm);
	}
}
