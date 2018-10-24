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
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(!HelperUtility.credentialsSet(email, password)) {
			response.sendRedirect("./Login");
			return;
		}
		
		try {
			Connection db = helper.DatabaseAccess.connectDataBase();
					
			PreparedStatement stmt = db.prepareStatement("SELECT email, password FROM USERS WHERE email=?");
			
			stmt.setString(1, email);
					
			ResultSet rs = stmt.executeQuery();
			
			if(rs.getFetchSize() == 0) {
				pw.println("Couldn't find user with that email");
				return;
			}
			
			String databasePassword = (String) rs.getObject("password");
			
			if(!password.equals(databasePassword)) {
				pw.println("Password is incorrect");
				return;
			}
			
			// TODO: Set session here.
			
			response.sendRedirect("./Dashboard");
			
			rs.close();
			db.close();
		} catch (Exception e){
			pw.println(e);
			
		}
		 
	}

}
