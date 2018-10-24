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
			
			return rs.getFetchSize() == 0;
		} catch(SQLException e) {
			throw e;
		}
	}
	
	public boolean validatePassword(String email, String password) {
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT email, password FROM USERS WHERE email=?");
			
			stmt.setString(1, email);
			
			ResultSet rs = stmt.executeQuery();
			
			String databasePassword = (String) rs.getObject("password");
			
			return password.equals(databasePassword);
		} catch(Exception e) {
			return false;
		}
	}
}