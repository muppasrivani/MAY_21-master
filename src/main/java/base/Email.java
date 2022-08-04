package base;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	
	private static String From = "ramuntech@gmail.com";
	private static String To ="goggery@gmail.com";
	private static String CC="";
	private static String host="localhost";
	
	private static void sendMail(String subject, String body) throws Exception{
		
		//try {
			Properties prop = System.getProperties();
			prop.put("mail.smtp.host", "smtp.mailtrap.io");
			prop.put("mail.smp.port", "25");
			//prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");
			//prop.put("mail.smtp.auth", true);
			prop.put("mail.smtp.starttls.enable","true");
			Session session = Session.getDefaultInstance(prop);
			
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(From));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(To));
			message.setSubject(subject);
			message.setText(body);
			
			Transport.send(message);
		/*
		 * } catch(Exception ex) { System.out.println("Unable to send Email"); }
		 */		
		
	}
	
	public static void main(String[] arg) throws Exception {
		sendMail("Automation results", "Completed automation execution");
	}

}
