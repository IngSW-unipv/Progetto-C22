package it.unipv.po.oocinema.controllers;

import java.io.FileInputStream;
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
* Classe che implementa il Controller per l'invio dell'email ai clienti
* 
* @author GoF
*/
public class EmailController {

	private static final String PROPERTYUSER = "EMAIL";
	private static final String PROPERTYPSW = "EMAIL_PSW";

	/**
	 * E-mail del cinema.
	 */
	private String user;
	
	/**
	 * Password dell'e-mail del cinema.
	 */
	private String password;

	/**
	 * Messaggio che è scritto nell'email
	 */
	private String messaggio;

	/**
	 * Costruttore del gestore dell'e-mail.
	 * 
	 * @param messaggio messaggio che è scritto nell'email
	 */
	public EmailController(String messaggio) {
		this.messaggio = messaggio;
		Properties p = new Properties(System.getProperties());
		try {
			p.load(new FileInputStream("properties/properties"));
			user = p.getProperty(PROPERTYUSER);
			password = p.getProperty(PROPERTYPSW);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Invia l'e-mail al cliente.
	 * 
	 * @param prenotazione prenotazione da inviare.
	 * @return il Thread utilizzato per l'invio asincrono dell'e-mail
	 *         allo spettatore.
	 * 
	 */
	public Thread sendEmail(Prenotazione prenotazione) {
		try {
			@SuppressWarnings("unused")
			TicketController ticketController = new TicketController(prenotazione);
		} catch (WriterException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread emailThread = new Thread() {
			@Override
			public void run() {
				try {
					
					String to = prenotazione.getAcquirente().getUser(); 

					Properties properties = setUpMainProperties(user, password);

					Session session = startNewSession(user, password, properties);

					createMessageAndSendEmail(session, user, to, prenotazione);
				} catch (Exception exception) {
					System.out.println(exception.getMessage());
				}
			}
		};

		return emailThread;
	}

	/**
	 * Effettua le creazione del messaggio da inviare e invia l'e-mail.
	 * 
	 * @param session     sessione e-mail generata per la specifica prenotazione.
	 * @param user        mittente dell'e-mail (cinema).
	 * @param to          destinatario dell'e-mail (cliente).
	 * @param prenotazione prenotazione da inviare.
	 * 
	 */
	private void createMessageAndSendEmail(Session session, String user, String to, Prenotazione prenotazione){
		try {
			
			Message message = createBasicMailProperties(session, user, to, prenotazione);

			BodyPart messageBodyPart1 = createMailBody(prenotazione);

			MimeBodyPart messageBodyPart2 = createMailReport(prenotazione);

			addBodyAndReportToMail(message, messageBodyPart1, messageBodyPart2);

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
	private void addBodyAndReportToMail(Message message, BodyPart messageBodyPart1, MimeBodyPart messageBodyPart2) throws MessagingException {
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
	private MimeBodyPart createMailReport(Prenotazione prenotazione) throws MessagingException {
		MimeBodyPart messageBodyPart2 = new MimeBodyPart();
		String filename = prenotazione.getTicketPath();
		DataSource source = new FileDataSource(filename);
		messageBodyPart2.setDataHandler(new DataHandler(source));
		messageBodyPart2.setFileName("Prenotazione_" + Integer.toString(prenotazione.getId()) + ".pdf");
		return messageBodyPart2;
	}

	/**
	 * Crea il testo principale (body) dell'email.
	 * 
	 * @param reservation prenotazione da inviare.
	 * @return il body dell'e-mail.
	 * @throws MessagingException se ci sono problemi nella generazione dell'e-mail.
	 */
	private BodyPart createMailBody(Prenotazione prenotazione) throws MessagingException {
		BodyPart messageBodyPart1 = new MimeBodyPart();
		messageBodyPart1.setText("Ciao " + prenotazione .getAcquirente().getNome() + " "
				+  ",\n\n" + messaggio);
		return messageBodyPart1;
	}

	/**
	 * Imposta le proprietà basilari necessarie per la creazione e invio
	 * dell'e-mail.
	 * 
	 * @param session     sessione e-mail generata per la specifica prenotazione.
	 * @param user        mittente dell'e-mail (cinema).
	 * @param to          destinatario dell'e-mail (spettatore).
	 * @param prenotazione prenotazione da inviare.
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
