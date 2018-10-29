package helper;

import java.sql.*;
import helper.DatabaseAccess;
import helper.HelperUtility;

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
			
			// passwords are hashed on registration, in a real app the salt would of course be 
			// kept in a safe place.
			String passwordHash = HelperUtility.sha256(password, "salt");
			String databasePasswordHash = (String) rs.getObject("password");
			
			return databasePasswordHash.equals(passwordHash);
		} catch(Exception e) {	
			e.printStackTrace();
			return false;
		}
	}
		
	public boolean registerUser(String firstName, String lastName, String address, String email, String password) {
		try {
			PreparedStatement ps;
			String query = "insert into users(firstName,lastName,address,email,password,role) values (?,?,?,?,?,?)";
			ps = connection.prepareStatement(query);
			
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setString(3, address);
			ps.setString(4, email);
			// passwords are hashed on registration, in a real app the salt would of course be 
			// kept in a safe place.
			ps.setString(5, HelperUtility.sha256(password, "salt"));
			ps.setString(6, "user");
			
			 
			return ps.executeUpdate() == 1;
		 
		
		} catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}
}
