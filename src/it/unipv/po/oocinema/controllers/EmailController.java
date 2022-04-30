package it.unipv.po.oocinema.controllers;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import it.unipv.po.oocinema.model.prenotazione.Prenotazione;
/**
 * Gestisce l'invio dell'e-mail, da parte del cinema, all'utente che ha concluso
 * una prenotazione.
 * 
 * @author Screaming Hairy Armadillo Team
 *
 */
public class EmailController {
	
	public static void sendEmail(Prenotazione prenotazione) throws Exception {
		Properties properties = System.getProperties();
		
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
		
		String myEmail = "oocinema.project@gmail.com";
		String password = "Password2021!";
		
		String to = prenotazione.getAcquirente().getUser();
		
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(myEmail, password);
			}
		});
		
		Message message = null;
		try {
			message = prepareMessage(session, myEmail, to, prenotazione);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Transport.send(message);
	}

	private static Message prepareMessage(Session session, String myEmail, String to, Prenotazione prenotazione) {
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(myEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Prenotazione numero "+prenotazione.getId());
			message.setText("Ciao " + prenotazione.getAcquirente().getUser() + " "
					+ ",\n\n" + "grazie per aver scelto il nostro Cinema!\n\n\n"
					+ "In allegato trovi la ricevuta di avvenuto pagamento che conferma il tuo acquisto.\n"
					+ "Stampa la prenotazione e presentala quando verrai a guardare il film.\n\n"
					+ "Ti aspettiamo, buona giornata!\n" +prenotazione.getAcquirente().getNome() + "\n\n"
					+ "Non rispondere alla presente e-mail. Messaggio generato automaticamente.\n");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


