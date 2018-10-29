//*********************************************************************************
//* Project: Lentil
//* Assignment: Assignment # 1
//* Author(s): Andrew Wilson, Justin Rolnick, Veronica Cheren, Karina Gorkova, Dindyal Mursingh
//* Student Number: 101069680, 100407074, 100831208, 101032995, 101083659
//* Date: October 29, 2018
//* Description: The helper class for interacting with the SQL database, also handles database queries and connections.
//*********************************************************************************


package helper;

import java.sql.*;
import helper.DatabaseAccess;

public class SQLHelper {
	
	private Connection connection;
	
	public Connection getConnection() {
		return this.connection;
	}
	
	public SQLHelper(Connection connection) {
		this.connection = connection;
	}
	
	public void finalize() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public SQLHelper() throws Exception {
		try {
			this.connection = DatabaseAccess.connectDataBase();
		} catch(Exception e) {
			throw e;
		}
	}
	
	public boolean emailExists(String email) throws SQLException {
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT email, password FROM USERS WHERE email=?");
			
			stmt.setString(1, email);
					
			ResultSet rs = stmt.executeQuery();
			
			return rs.next();
		} catch(SQLException e) {
			throw e;
		}
	}
	
	public boolean validatePassword(String email, String password) {
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT email, password FROM USERS WHERE email=?");
			
			stmt.setString(1, email);
			
			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			
			// passwords are hashed on registration, in a real application the salt would of course be 
			// kept in a safe place.
			// String passwordHash = HelperUtility.sha256(password, "salt");
			// String databasePasswordHash = (String) rs.getObject("password");
			// return databasePasswordHash.equals(passwordHash);
			
			String databasePassword = (String) rs.getObject("password");
			
			return databasePassword.equals(password);
			
		} catch(Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
	
		
	public String getFirstName(String email) {
		try {
		PreparedStatement stmt = connection.prepareStatement("SELECT firstname FROM USERS WHERE email=?");
		
		stmt.setString(1, email);
		
		ResultSet rs = stmt.executeQuery();
		
		rs.next();
		
		String firstName = (String) rs.getString("firstname");
		
		return firstName;
		
		} catch(Exception e) {	
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean registerUser(String firstName, String lastName, String address, String email, String password) throws SQLException {

		try {
			PreparedStatement ps;
			String query = "insert into users(firstName,lastName,address,email,password,role) values (?,?,?,?,?,?)";
			ps = connection.prepareStatement(query);
			
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setString(3, address);
			ps.setString(4, email);
			// passwords are hashed on registration, in a real application the salt would of course be 
			// kept in a safe place.
			// ps.setString(5, HelperUtility.sha256(password, "salt"));
			ps.setString(5, password);
			ps.setString(6, "user");
			 
			return ps.executeUpdate() == 1;
		 
		} catch(SQLException e){
			e.printStackTrace();
			throw e;
		}
	}
}
