package it.unipv.po.oocinema.controllers;

import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.google.zxing.WriterException;

import it.unipv.po.oocinema.model.prenotazione.Prenotazione;
/**
 * Gestisce l'invio dell'e-mail, da parte del cinema, all'utente che ha concluso
 * una prenotazione.
 * 
 * @author Screaming Hairy Armadillo Team
 *
 */
public class EmailController {

	/**
	 * E-mail del cinema.
	 */
	private String email;

	/**
	 * Password dell'e-mail del cinema.
	 */
	private String password;

	/**
	 * Nome del cinema.
	 */
	private String name;

	/**
	 * Ubicazione del cinema.
	 */
	private String location;

	/**
	 * URL del logo del cinema.
	 */
	private String logoURL;

	TicketHandler ticketHandler;
	
	public EmailController(String name, String email, String password, String location, String logoURL) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.location = location;
		this.logoURL = logoURL;
	}

	     
	public Thread sendEmail(Prenotazione prenotazione) throws WriterException, IOException {
		ticketHandler = new TicketHandler(prenotazione);
		Thread emailThread = new Thread() {
			@Override
			public void run() {
		
					// Stabilisce le informazioni sul sender ed il receiver dell'email
					String to = prenotazione.getAcquirente().getUser(); // Receiver email
					String user = email; // Sender email (cinema)
					
					// Configura le proprietà dell'email
					Properties properties = setUpMainProperties(user, password);

					// Genera una nuova sessione mail
					Session session = startNewSession(user, password, properties);

					// Tenta la composizione del messaggio e l'invio dell'email
					createMessageAndSendEmail(session, user, to, prenotazione);
					System.out.println(to + " "+user);
			}
		};
		emailThread.start();
		return emailThread;
	}

	

	
	private void createMessageAndSendEmail(Session session, String user, String to, Prenotazione prenotazione){
		try {
			// Configura le proprietà basilari dell'email
			Message message = createBasicMailProperties(session, user, to, prenotazione);

			// Crea il body dell'email
			BodyPart messageBodyPart1 = createMailBody(prenotazione);

			// Aggiunge, all'email, il report della prenotazione in allegato
			MimeBodyPart messageBodyPart2 = createMailReport(prenotazione);

			// Crea un campo multipart comprendente body e allegato
			addBodyAndReportToMail(message, messageBodyPart1, messageBodyPart2);

			// Invia l'email
			Transport.send(message);
		} catch (MessagingException ex) {
			
		}
	}

	
	private void addBodyAndReportToMail(Message message, BodyPart messageBodyPart1, MimeBodyPart messageBodyPart2)
			throws MessagingException {
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart1);
		multipart.addBodyPart(messageBodyPart2);
		message.setContent(multipart);
	}


	private MimeBodyPart createMailReport(Prenotazione prenotazione) throws MessagingException {
		MimeBodyPart messageBodyPart2 = new MimeBodyPart();
		String filename = ticketHandler.getFile();
		DataSource source = new FileDataSource(filename);
		messageBodyPart2.setDataHandler(new DataHandler(source));
		messageBodyPart2.setFileName("Reservation_" + Long.toString(prenotazione.getId()) + ".pdf");
		return messageBodyPart2;
	}

	
	private BodyPart createMailBody(Prenotazione prenotazione) throws MessagingException {
		BodyPart messageBodyPart1 = new MimeBodyPart();
		messageBodyPart1.setText("Ciao " + prenotazione.getAcquirente().getUser() + " "
				+ ",\n\n" + "grazie per aver scelto il nostro Cinema!\n\n\n"
				+ "In allegato trovi la ricevuta di avvenuto pagamento che conferma il tuo acquisto.\n"
				+ "Stampa la prenotazione e presentala quando verrai a guardare il film.\n\n"
				+ "Ti aspettiamo, buona giornata!\n" + name + "\n\n"
				+ "Non rispondere alla presente e-mail. Messaggio generato automaticamente.\n");
		return messageBodyPart1;
	}

	/**
	 * Imposta le proprietà basilari necessarie per la creazione e invio
	 * dell'e-mail.
	 * 
	 * @param session     sessione e-mail generata per la specifica prenotazione.
	 * @param user        mittente dell'e-mail (cinema).
	 * @param to          destinatario dell'e-mail (spettatore).
	 * @param reservation prenotazione da inviare.
	 * @return il contenitore, che accoglierà: l'oggetto, il body e l'allegato, con
	 *         le proprietà dell'email.
	 * @throws MessagingException se ci sono problemi nella generazione dell'e-mail.
	 */
	private Message createBasicMailProperties(Session session, String user, String to, Prenotazione prenotazione)
			throws MessagingException {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(user));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject("Prenotazione numero " + prenotazione.getId() + " - Ti aspettiamo!");
		return message;
	}

	/**
	 * Genera una nuova sessione e-mail.
	 * 
	 * @param user       user mittente dell'e-mail (cinema).
	 * @param password   password password dell'e-mail del cinema.
	 * @param properties proprietà dell'e-mail.
	 * @return sessione e-mail generata per la specifica prenotazione.
	 */
	private Session startNewSession(String user, String password, Properties properties) {
		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		return session;
	}

	/**
	 * Imposta le proprietà dell'e-mail.
	 * 
	 * @param user     mittente dell'e-mail (cinema).
	 * @param password password dell'e-mail del cinema.
	 * @return le proprietà dell'e-mail.
	 */
	private Properties setUpMainProperties(String user, String password) {
		Properties properties = System.getProperties();
		String host = "smtp.gmail.com";
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.user", user);
		properties.put("mail.smtp.password", password);
		properties.put("mail.smtp.port", "25");
		properties.put("mail.smtp.auth", true);
		
		return properties;
	}

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


