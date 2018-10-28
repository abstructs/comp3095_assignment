package loginServlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		

		
//		Invalid username and/or password combination is provided
//			- occurs when a user enters an invalid username/password combination.
//		A User requests a page that requires a user to be successfully logged in first
//			- occurs when a user explicitly enters the URL of a restricted page without 
//			first authenticating using the Login Servlet.
		
		
		pw.println("<!DOCTYPE html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<title>");
		pw.println("Lab 2");
		pw.println("</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<h2>");
		pw.println("");
		pw.println("</h2>");
		pw.println("</body>");
		pw.println("</html>");
		
		
		// if login button is clicked
		
		//checking if there is input in the forms
		if(helper.HelperUtility.formValidator(email, password)) {
			response.sendRedirect("ErrorLogin.html");
		}
		else if(request.getParameter("login") != null) {
			request.setAttribute("email", email);
			request.setAttribute("password", password);
			RequestDispatcher rd = request.getRequestDispatcher("Authenticate");
			rd.forward(request,response);
		}
	}
}
