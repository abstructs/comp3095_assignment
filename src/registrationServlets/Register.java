package registrationServlets;

import helper.*;

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

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		pw.print(HelperUtility.printHead("Project Title - Registration"));
		pw.print("<body>");

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("passwordConfirm");
		String terms = request.getParameter("terms");
		
		String errors;
		boolean registered = false;

		try {
			SQLHelper sqlhelper = new SQLHelper();
			errors = ValidateRegistration.getErrors(sqlhelper.getConnection(), firstName, lastName, address, email, password, passwordConfirm, terms);			
			if (errors.equals("")) {
				registered = sqlhelper.registerUser(firstName, lastName, address, email, password);
			}
		} catch(Exception e) {
			errors = "Something went wrong. Please try again.";
		}
		
		if (registered) {
			ConfirmationEmail.send(email, firstName, lastName);
			pw.print("<h1>Registration Complete</h1>");
			pw.print("<div class='container'>");
			pw.print("Your registration was successful.\n");
			pw.print("An email has been sent to <i>" + email + "</i>. Please check your email to verify and confirm.");
			pw.print("<br /><a href='Login' class='btn btn-primary'>Continue</a>");
			pw.print("</div>");
		} else {
			pw.print("<h1>Registration</h1>");
			
			pw.print("<div class='alert alert-warning'>" + errors + "</div>");
			
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
					"		<label for='terms'>I agree to the <a href='terms.html'>terms of service</a>.</label>\n" + 
					"		<br />\n" + 
					"		<input type='submit' value='Register' />\n" + 
					"		<br />\n" + 
					"		<a href='Login'>Cancel</a>\n" + 
					"	</form>");
		}
		pw.print("</body>");
		pw.print("</html>");
	}
}
