//*********************************************************************************
//* Project: Lentil
//* Assignment: Assignment # 1
//* Author(s): Andrew Wilson, Justin Rolnick, Veronica Cheren, Karina Gorkova, Dindyal Mursingh
//* Student Number: 101069680, 100407074, 100831208, 101032995, 101083659
//* Date: October 29, 2018
//* Description: Takes username and password as attributes and uses SQLHelper to validate that the information the user entered in is correct.
//*********************************************************************************



package loginServlets;

import java.io.IOException;
import helper.SQLHelper;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helper.HelperUtility;

@WebServlet("/Authenticate")
public class Authenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Authenticate() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(request.getParameter("logout") != null) {
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
