package helper;

import java.util.Properties;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;
import javax.servlet.http.HttpSession;

public class ConfirmationEmail {
	public static void send(String email, String firstName, String lastName) {
		final String username = "comp3095@gmail.com";
		final String password = "javaproject";
		final String msgSubject = "Confirm your registration at [Project Name]";
		String name = firstName.substring(0,1).toUpperCase() + firstName.substring(1) + " " +
						lastName.substring(0,1).toUpperCase() + lastName.substring(1);
		
		final String msgBody = "<h3>Hi, " + name + "!</h3>"
				+ "You have successfully created an account at [Project Name] with email <i>" + email + "</i>.<br />" 
				+ "Please click <a href='http://localhost:8080/comp3095_assignment/Login'>here</a> to log in to your dashboard.";
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props, 
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			}
		);
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, 
					InternetAddress.parse(email));
			message.setSubject(msgSubject);
			message.setContent(msgBody, "text/html; charset=utf-8");
			Transport.send(message);
		} catch(MessagingException e) {
			e.printStackTrace();
		}
	}
}