package model;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import java.util.Properties;

/* Classe che si occupa di mandare le email di notifica 
 * @author Federico Guida*/
public class EmailService {
	public static void sendMail(String to,String subject,
			String body, boolean bodyIsHTML, ServletContext sc){
		try {
			// 1 - get a mail session
			Properties props = new Properties();
			props.put("mail.transport.protocol", "smtps");
			props.put("mail.smtps.host", sc.getInitParameter("host"));
			props.put("mail.smtps.port", sc.getInitParameter("port"));
			props.put("mail.smtps.auth", "true");
			props.put("mail.smtps.quitwait", "false");
			Session session = Session.getDefaultInstance(props);
			
			// 2 - create a message
			Message message = new MimeMessage(session);
			message.setSubject(subject);
			if (bodyIsHTML) {
				message.setContent(body, "text/html");
			} else {
				message.setText(body);
			}
			
			// 3 - address the message
			Address fromAddress = new InternetAddress(sc.getInitParameter("user"));
			Address toAddress = new InternetAddress(to);
			message.setFrom(fromAddress);
			message.setRecipient(Message.RecipientType.TO, toAddress);
			
			
			// 4 - send the message
			Transport transport = session.getTransport();
			transport.connect(sc.getInitParameter("user"), sc.getInitParameter("pass"));
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (NullPointerException|MessagingException e ) {
			sc.log(
				"Unable to send email. \n"
			    + "Here is the email you tried to send: \n"
				+ "=======================================\n"
			    + "TO: " + to + "\n"
			    + "FROM: " + sc.getInitParameter("user") + "\n"
			    + "SUBJECT: " + subject + "\n"
			    + "\n"
			    + body + "\n\n");
		}
	}
}

