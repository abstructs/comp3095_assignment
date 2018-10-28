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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		pw.print(HelperUtility.printHead("Project Title - Register"));
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
			e.printStackTrace();
			errors = "Something went wrong. Please try again.";
		}
		
		if (registered) {
			ConfirmationEmail.send(email, firstName, lastName);
			pw.print("<h1>Registration Complete</h1>");
			pw.print("<div class='container form'>");
			pw.print("Your registration was successful.<br />");
			pw.print("An email has been sent to <i>" + email + "</i>.<br /> Please check your email to verify and confirm.");
			pw.print("<br /><a href='Login.html' class='btn btn-primary right'>Continue</a>");
			pw.print("</div>");
		} else {
			pw.print("<h1>Register</h1>");
			
			pw.print("	<div class='container form'>" + 
					"	<div class='alert alert-warning'>" + errors + "</div>" +
					"	<form method='POST' action='Register'>" + 
					"		<div class='row'>" + 
					"			<div class='col-xs-6'>" + 
					"				<input type='text' class='form-control' placeholder='First Name*' name='firstName' value='" + firstName + "'/>" + 
					"			</div>" + 
					"			<div class='col-xs-6'>" + 
					"				<input type='text' class='form-control' placeholder='Last Name*' name='lastName' value='" + lastName + "'/>" + 
					"			</div>" + 
					"		</div>" + 
					"		<div class='row'>" + 
					"			<div class='col-xs-12'>" + 
					"				<input type='text' class='form-control' placeholder='Address*' name='address' value='" + address + "'/>" + 
					"			</div>" + 
					"		</div>" + 
					"		<div class='row'>" + 
					"			<div class='col-xs-12'>" + 
					"				<input type='text' class='form-control' placeholder='Email*' name='email' value='" + email + "'/>" + 
					"			</div>" + 
					"		</div>" + 
					"		<div class='row'>" + 
					"			<div class='col-xs-6'>" + 
					"				<input type='password' class='form-control' placeholder='Password*' name='password' />" + 
					"			</div>" + 
					"			<div class='col-xs-6'>" + 
					"				<input type='password' class='form-control' placeholder='Confirm Password*' name='passwordConfirm' />" + 
					"			</div>" + 
					"		</div>" + 
					"		<div class='row'>" + 
					"			<div class='col-xs-12'>" + 
					"				<input type='checkbox' name='terms' />" + 
					"				<label for='terms'>I agree to the <a href='Terms.html'>terms of service</a>.*</label>" + 
					"			</div>" + 
					"		</div>" + 
					"		<div class='row'>" + 
					"			<div class='col-xs-6'>" + 
					"				<input type='submit' value='Register' class='btn btn-success' />" + 
					"			</div>" + 
					"			<div class='col-xs-6'>" + 
					"				<a class='btn btn-danger right' href='Login.html' role='button'>Cancel</a>" + 
					"			</div>" + 
					"		</div>" + 
					"	</form>	" + 
					"	</div>");
		}
		pw.print("</body>");
		pw.print("</html>");
	}
}
