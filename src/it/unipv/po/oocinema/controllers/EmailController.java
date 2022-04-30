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

	/**
	 * Costruttore del gestore dell'e-mail.
	 * 
	 * @param name     nome del cinema.
	 * @param email    e-mail del cinema.
	 * @param password password dell'e-mail del cinema.
	 * @param location ubicazione del cinema.
	 * @param logoURL  URL del logo del cinema (iconfinder.com).
	 */
	public EmailController() {
	
		this.password = "Password2021!";
	}

	/**
	 * Invia l'e-mail allo spettatore.
	 * 
	 * @param reservation prenotazione da inviare.
	 * @return il Thread, già avviato, utilizzato per l'invio asincrono dell'e-mail
	 *         allo spettatore.
	 * @throws HandlerException se ci fosse un problema nella spedizione del report
	 *                          o nella sua generazione.
	 */
	public Thread sendEmail(Prenotazione prenotazione) {
		try {
			TicketController ticketController = new TicketController(prenotazione);
		} catch (WriterException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread emailThread = new Thread() {
			@Override
			public void run() {
				try {
					// Prima di inviare l'email si verifica che il report sia già stato generato,
					// se non è ancora stato generato lo genero
					

					// Stabilisce le informazioni sul sender ed il receiver dell'email
					String to = prenotazione.getAcquirente().getUser(); // Receiver email
					String user = "oocinema.project@gmail.com"; // Sender email (cinema)

					// Configura le proprietà dell'email
					Properties properties = setUpMainProperties(user, password);

					// Genera una nuova sessione mail
					Session session = startNewSession(user, password, properties);

					// Tenta la composizione del messaggio e l'invio dell'email
					createMessageAndSendEmail(session, user, to, prenotazione);
				} catch (Exception exception) {
					System.out.println(exception.getMessage());
				}
			}
		};
		emailThread.start();
		return emailThread;
	}

	/**
	 * Effettua le creazione del messaggio da inviare e invia l'e-mail.
	 * 
	 * @param session     sessione e-mail generata per la specifica prenotazione.
	 * @param user        mittente dell'e-mail (cinema).
	 * @param to          destinatario dell'e-mail (spettatore).
	 * @param reservation prenotazione da inviare.
	 * @throws HandlerException se ci fosse un problema nella spedizione del report
	 *                          o nella sua generazione.
	 */
	private void createMessageAndSendEmail(Session session, String user, String to, Prenotazione reservation)
			{
		try {
			// Configura le proprietà basilari dell'email
			Message message = createBasicMailProperties(session, user, to, reservation);

			// Crea il body dell'email
			BodyPart messageBodyPart1 = createMailBody(reservation);

			// Aggiunge, all'email, il report della prenotazione in allegato
			MimeBodyPart messageBodyPart2 = createMailReport(reservation);

			// Crea un campo multipart comprendente body e allegato
			addBodyAndReportToMail(message, messageBodyPart1, messageBodyPart2);

			// Invia l'email
			Transport.send(message);
		} catch (MessagingException ex) {
			}
	}

	/**
	 * Aggiunge all'e-mail il body ed il report in allegato.
	 * 
	 * @param message          contenitore con le proprietà dell'email.
	 * @param messageBodyPart1 body dell'e-mail.
	 * @param messageBodyPart2 allegato dell'e-mail.
	 * @throws MessagingException se ci sono problemi nella generazione dell'e-mail.
	 */
	private void addBodyAndReportToMail(Message message, BodyPart messageBodyPart1, MimeBodyPart messageBodyPart2)
			throws MessagingException {
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart1);
		multipart.addBodyPart(messageBodyPart2);
		message.setContent(multipart);
	}

	/**
	 * Crea l'allegato dell'e-mail. Allega il report della prenotazione.
	 * 
	 * @param reservation prenotazione da inviare.
	 * @return il report allegato all'email.
	 * @throws MessagingException se ci sono problemi nella generazione dell'e-mail.
	 */
	private MimeBodyPart createMailReport(Prenotazione reservation) throws MessagingException {
		MimeBodyPart messageBodyPart2 = new MimeBodyPart();
		String filename = reservation.getTicketPath();
		DataSource source = new FileDataSource(filename);
		messageBodyPart2.setDataHandler(new DataHandler(source));
		messageBodyPart2.setFileName("Reservation_" + Long.toString(reservation.getId()) + ".pdf");
		return messageBodyPart2;
	}

	/**
	 * Crea il testo principale (body) dell'email.
	 * 
	 * @param reservation prenotazione da inviare.
	 * @return il body dell'e-mail.
	 * @throws MessagingException se ci sono problemi nella generazione dell'e-mail.
	 */
	private BodyPart createMailBody(Prenotazione reservation) throws MessagingException {
		BodyPart messageBodyPart1 = new MimeBodyPart();
		messageBodyPart1.setText("Ciao " + reservation.getAcquirente().getNome() + " "
				+  ",\n\n" + "grazie per aver scelto il nostro Cinema!\n\n\n"
				+ "In allegato trovi la ricevuta di avvenuto pagamento che conferma il tuo acquisto.\n"
				+ "Stampa la prenotazione e presentala quando verrai a guardare il film.\n\n"
				+ "Ti aspettiamo, buona giornata!\n" + "\n\n"
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
	private Message createBasicMailProperties(Session session, String user, String to, Prenotazione reservation)
			throws MessagingException {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(user));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject("Prenotazione numero " + reservation.getId() + " - Ti aspettiamo!");
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
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.user", user);
		properties.put("mail.smtp.password", password);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
		properties.put("mail.smtp.auth", "true");
		return properties;
	}

}
