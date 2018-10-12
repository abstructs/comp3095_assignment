package registrationServlets;

import helper.ValidateRegistration;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Register() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		pw.print("<!DOCTYPE html>");
		pw.print("<html>");
		pw.print("<head>");
		pw.print("<meta charset='UTF-8'>");
		pw.print("<title>Project Title - Registration</title>");
		pw.print("</head>");
		pw.print("<body>");

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("passwordConfirm");
		String terms = request.getParameter("terms");
		
		String errors = ValidateRegistration.getErrors(firstName, lastName, address, email, password, passwordConfirm, terms);
		
		if (errors.equals("")) {
			/* TODO: create user entry in database 
			 * TODO: ValidateRegistration.sendEmail(email)
			 * TODO: css 'registered' class
			 * TODO: change form to <a> and style as button
			 */
			
				pw.print("<p class='registered'>");
				pw.print("Your registration was successful.\n");
				pw.print("An email has been sent to " + email + ". Please check your email to verify and confirm.");
				pw.print("<form action='Dashboard'>");
				pw.print("	<input type='submit' value='Continue' />");
				pw.print("</form>");
			
			/*
				pw.println("<script type='text/javascript'>");  
				pw.println("alert('"
						+ "Your registration was successful.\n"
						+ "An email has been sent to " + email + ".\n"
						+ "Please check your email to verify and confirm."
						+ "');");  
				pw.println("</script>");
				response.sendRedirect("Dashboard");
			*/
		} else {
			//using response.sendRedirect("InvalidRegistration") would require using getErrors() again on the InvalidRegistration servlet
			//should the form be autofilling password & terms agreement?
			//TODO: css 'error' class
			pw.print("<h1>Registration</h1>");
			
			pw.print("<p class='error'>");
			pw.print(errors);
			pw.print("</p>");
			
			pw.print("	<form method='POST' action='Register'>\n" + 
					"		<label for='firstName'>First name *</label>\n" + 
					"		<input type='text' name='firstName' value='" + firstName + "' />\n" + 
					"		<br />\n" + 
					"		<label for='lastName'>Last name *</label>\n" + 
					"		<input type='text' name='lastName' value='" + lastName + "' />\n" + 
					"		<br />\n" + 
					"		<label for='address'>Address *</label>\n" + 
					"		<input type='text' name='address' value='" + address + "' />\n" + 
					"		<br />\n" + 
					"		<label for='email'>Email *</label>\n" + 
					"		<input type='text' name='email' value='" + email + "' />\n" + 
					"		<br />\n" + 
					"		<label for='password'>Password *</label>\n" + 
					"		<input type='password' name='password' />\n" + 
					"		<br />\n" + 
					"		<label for='passwordConfirm'>Confirm password *</label>\n" + 
					"		<input type='password' name='passwordConfirm' />\n" + 
					"		<br />\n" + 
					"		<input type='checkbox' name='terms' />\n" + 
					"		<label for='terms'>I agree to the terms of service.</label>\n" + 
					"		<br />\n" + 
					"		<input type='submit' value='Register' />\n" + 
					"		<br />\n" + 
					"		<a href='Login'>Cancel</a>\n" + 
					"	</form>");
			pw.print("</body>");
			pw.print("</html>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
