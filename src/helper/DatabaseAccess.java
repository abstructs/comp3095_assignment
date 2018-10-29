//*********************************************************************************
//* Project: Lentil
//* Assignment: Assignment # 1
//* Author(s): Andrew Wilson, Justin Rolnick, Veronica Cheren, Karina Gorkova, Dindyal Mursingh
//* Student Number: 101069680, 100407074, 100831208, 101032995, 101083659
//* Date: October 29, 2018
//* Description: Loads the mysql driver and gets the connection to the database using credentials.
//*********************************************************************************



package helper;
/****************************************************************************************************
* Description: DatabaseAccess - Example provides access to database
****************************************************************************************************/
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseAccess {
	  private static Connection connect = null;
	  
	  private static String email = "admin";;
	  private static String password = "P@ssword1";
	  private static String database = "COMP3095";

	  	
	  public static Connection connectDataBase() throws Exception {
	    try {
	      // This will load the MySQL driver, each DB has its own driver
	      Class.forName("com.mysql.jdbc.Driver");
	      // Setup the connection with the DB
	      connect = DriverManager
		          .getConnection("jdbc:mysql://localhost:3306/"+database+"?"
		              + "user="+email+"&password="+password + "&serverTimezone=UTC");
	      return connect;
	    } catch (Exception e) {
	      throw e;
	    }
	  }
}