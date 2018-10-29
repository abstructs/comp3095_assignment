//*********************************************************************************
//* Project: Lentil
//* Assignment: Assignment # 1
//* Author(s): Andrew Wilson, Justin Rolnick, Veronica Cheren, Karina Gorkova, Dindyal Mursingh
//* Student Numbers: 101069680, 100407074, 100831208, 101032995, 101083659
//* Date: October 29, 2018
//* Description: Handles rendering the html for the Dashboard, redirects the users to login if they are not logged in.
//*********************************************************************************



package dashboardServlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helper.HelperUtility;

@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Dashboard() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		
		if(!HelperUtility.isAuthenticated(session)) {
			response.sendRedirect("Login.html");
			return;
		}
		
		PrintWriter pw = response.getWriter();
		
	
		
		pw.print("<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"UTF-8\">\r\n" + 
				"<title>Lentil - Dashboard</title>\r\n" + 
				"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\r\n" + 
				"<link rel=\"stylesheet\" href=\"dashboardStyles.css\">\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"	<nav class=\"navbar navbar-light bg-light navbar-static-top\">\r\n" + 
				"	  	<div class=\"navbar-brand\">\r\n" + 
				"	  		Lentil\r\n" + 
				"	  	</div>\r\n" + 
				"	  <div class=\"container\" id=\"navbarSupportedContent\">\r\n" + 
				"	    <ul class=\"nav nav-tabs navbar-nav\">\r\n" + 
				"	      <li class=\"nav-item\"><a href=\"tab1\">Tab 1</a></li>\r\n" + 
				"	      <li class=\"nav-item\"><a href=\"tab2\">Tab 2</a></li>\r\n" + 
				"	      <li class=\"nav-item\"><a href=\"tab3\">Tab 3</a></li>\r\n" + 
				"          <li class=\"nav-item\"><a href=\"tab4\">Tab 4</a></li>\r\n" +
				"        </ul>\r\n" +
				"        <ul class=\"nav navbar-nav navbar-right\">\r\n" + 
				"          <li class=\"name nav-item\">Welcome, " + session.getAttribute("name") + "!</li>\r\n" + 
				"	      <li class=\"nav-item btn-logout\">\r\n" + 
				"	     	<form method=\"POST\" action=\"Authenticate\" class=\"form-inline\">\r\n" + 
				"	     	<input type=\"hidden\" name=\"logout\" />\r\n" + 
				"	      	<button class=\"btn btn-primary\" type=\"submit\">Log Out</button>\r\n" + 
				"	    	</form>\r\n" + 
				"	      </li>\r\n" + 
				"        </ul>" +
				"	  </div>\r\n" + 
				"	</nav>\r\n" + 
				"	<div class=\"row\">\r\n" + 
				"		<div class=\"col-xs-6\">\r\n" + 
				"			<div class=\"future\">\r\n" + 
				"				Future Enhancement\r\n" + 
				"			</div>\r\n" + 
				"		</div>\r\n" + 
				"		<div class=\"col-xs-6\">\r\n" + 
				"			<div class=\"future\">\r\n" + 
				"				Future Enhancement\r\n" + 
				"			</div>\r\n" + 
				"		</div>\r\n" + 
				"	</div>\r\n" + 
				"	<div class=\"row\">\r\n" + 
				"		<div class=\"col-xs-12\">\r\n" + 
				"			<div class=\"future\">\r\n" + 
				"				Future Enhancement\r\n" + 
				"			</div>\r\n" + 
				"		</div>\r\n" + 
				"	</div>\r\n" + 
				"</body>\r\n" + 
				"</html>\r\n"
				);
	}
}
