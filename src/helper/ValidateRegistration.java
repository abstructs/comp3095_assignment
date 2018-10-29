//*********************************************************************************
//* Project: Lentil
//* Assignment: Assignment # 1
//* Author(s): Andrew Wilson, Justin Rolnick, Veronica Cheren, Karina Gorkova, Dindyal Mursingh
//* Student Number: 101069680, 100407074, 100831208, 101032995, 101083659
//* Date: October 29, 2018
//* Description: The helper class for registering a user and sending the confirmation email.
//*********************************************************************************



package helper;

import helper.SQLHelper;
import java.sql.Connection;
import java.sql.SQLException;

public class ValidateRegistration {
	
	public static boolean isEmpty(String field) {
		return field == null || field.trim().equals("");
	}
	
	public static boolean isAlpha(String field) {
		//matches [at least 1 letter]
		return field.matches("^[A-Za-z]+$");
	}
	
	public static boolean validAddress(String address) {
		//matches 	[1 to 5 numbers] [space]
		//			[2 to 30 letters] [optional period]
		return address.matches("^\\d{1,5}\\s[a-zA-Z]{2,30}(\\s[a-zA-Z]{2,15}.?)?$");
	}
	
	public static boolean validEmail(String email) {
		//matches 	[at least 1 letter, number, underscore, or period]
		//			[literal @] [at least 1 letter] 
		//			[literal period] [2 to 3 letters]
		return email.matches("^[0-9a-zA-Z_.]+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$");
	}
	
	public static boolean validPassword(String password) {
		//matches	[6 to 12 characters including a special character and uppercase letter]
		return password.matches("^(?=.*[!@#\\$%\\^&\\?])(?=.*[A-Z]).{6,12}$");
	}
	
	public static boolean passwordsMatch(String password, String passwordConfirm) {
		return password.equals(passwordConfirm);
	}
	
	public static boolean agreedToTerms(String terms) {
		return terms != null;
	}
	
	public static String getErrors(Connection connection, String firstName, String lastName, String address, String email, String password, String passwordConfirm, String terms) {
		String errorMessage = "";
		
		SQLHelper sqlHelper = new SQLHelper(connection);
				
		if (isEmpty(firstName)) {
			errorMessage += "First name is required.<br />";
		} else if (!isAlpha(firstName)) {
			errorMessage += "First name may only include letters.<br />";
		}

		if (isEmpty(lastName)) {
			errorMessage += "Last name is required.<br />";
		} else if (!isAlpha(lastName)) {
			errorMessage += "Last name may only include letters.<br />";
		}
		
		if (isEmpty(address)) {
			errorMessage += "Address is required.<br />";
		} else if (!validAddress(address)) {
			errorMessage += "You must enter a valid address.<br />";
		}
		
		if (isEmpty(email)) {
			errorMessage += "Email is required.<br />";
		} else if (!validEmail(email)) {
			errorMessage += "You must enter a valid email.<br />";
		} else {
			try {
				if(sqlHelper.emailExists(email)) {
					errorMessage += "Email already exists.<br />";
				}
			} catch(SQLException e) {
				System.out.println("SQL Exception occured");
			}
		}
		
		if (isEmpty(password)) {
			errorMessage += "Password is required.<br />";
		} else if (isEmpty(passwordConfirm)) {
			errorMessage += "Please confirm your password.<br />";
		} else if (!validPassword(password)) {
			errorMessage += "Password must be between 6 - 12 characters and include a special character and an uppercase letter.<br />";
		} else if (!passwordsMatch(password, passwordConfirm)) {
			errorMessage += "Passwords do not match.<br />";
		}
		
		if(!agreedToTerms(terms)) {
			errorMessage += "You must agree to the terms of service.<br />";
		}
		
		return errorMessage;
	}
	
	public static void sendConfirmation(String email, String firstName, String lastName) {
		ConfirmationEmail.send(email, firstName, lastName);
	}
}
