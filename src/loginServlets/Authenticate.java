package loginServlets;

import java.io.IOException;
import helper.SQLHelper;


import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helper.DatabaseAccess;
import helper.HelperUtility;;

@WebServlet("/Authenticate")
public class Authenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Authenticate() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		SQLHelper sqlhelper;
		try {
			sqlhelper = new SQLHelper();
			pw.println("connected!");
		} catch(Exception e) {
			pw.println(e);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		
		String email = (String)request.getAttribute("email");
		String password = (String)request.getAttribute("password");
		
		if(!HelperUtility.credentialsSet(email, password)) {
			pw.println("credentials not set");
//			response.sendRedirect("ErrorLogin.html");
			return;
		}
		
		try {
			Connection db = helper.DatabaseAccess.connectDataBase();
					
			PreparedStatement stmt = db.prepareStatement("SELECT * FROM USERS WHERE email=?");

			stmt.setString(1, email);
					
			ResultSet rs = stmt.executeQuery();
			
			if(!rs.next()) {
				pw.println("couldnt find email");
				return;
			}
			
			
			String databasePassword = (String) rs.getObject("password");
			
			if(!password.equals(databasePassword)) {
				pw.println("Password is incorrect");
				return;
			}
			
			//Create a session
			HttpSession session = request.getSession();
			session.setAttribute("email", email);
			response.sendRedirect("Dashboard.html");
			
			rs.close();
			db.close();
		} catch (Exception e){
			pw.println(e);
			
		}
		 
	}

}
