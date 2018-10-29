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
import helper.HelperUtility;
import helper.SQLHelper;

@WebServlet("/Authenticate")
public class Authenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Authenticate() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(request.getAttribute("logout") != null) {
			session.invalidate();
			response.sendRedirect("Login.html");
			return;
		}
		
		String email = (String) request.getAttribute("email");
		String password = (String) request.getAttribute("password");
		
		
		if(!HelperUtility.credentialsSet(email, password)) {
			 response.sendRedirect("ErrorLogin.html");
			return;
		}
		
		SQLHelper sqlHelper;
		
		try {
			sqlHelper = new SQLHelper();
			if(!sqlHelper.emailExists(email) || !sqlHelper.validatePassword(email, password)) {
				response.sendRedirect("ErrorLogin.html");
				return;
			}
			
			session.setAttribute("email", email);
			session.setAttribute("name", sqlHelper.getFirstName(email));
			response.sendRedirect("Dashboard");
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
