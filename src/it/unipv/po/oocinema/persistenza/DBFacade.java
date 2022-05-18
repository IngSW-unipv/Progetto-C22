package it.unipv.po.oocinema.persistenza;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.acquirenti.Acquirente;
import it.unipv.po.oocinema.model.acquirenti.Cassa;
import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.model.cinema.Posto;
import it.unipv.po.oocinema.model.cinema.Proiezione;
import it.unipv.po.oocinema.model.cinema.Sala;
import it.unipv.po.oocinema.model.prenotazione.Prenotazione;
import it.unipv.po.oocinema.persistenza.classiDAO.AcquirenteDAO;
import it.unipv.po.oocinema.persistenza.classiDAO.CinemaInfoDAO;
import it.unipv.po.oocinema.persistenza.classiDAO.FilmDAO;
import it.unipv.po.oocinema.persistenza.classiDAO.OraDAO;
import it.unipv.po.oocinema.persistenza.classiDAO.PostoDAO;
import it.unipv.po.oocinema.persistenza.classiDAO.PrenotazioneDAO;
import it.unipv.po.oocinema.persistenza.classiDAO.ProiezioneDAO;
import it.unipv.po.oocinema.persistenza.classiDAO.SalaDAO;

/**
 * Questa classe contiene tutti i metodi per interfacciare l'applicazione 
 * con il gestore di persistenza dei dati.
 * 
 * @author GoF
 */

public class DBFacade {
	
	static DBFacade instance;
	
	/**
	 * Oggetto attraverso cui è gestita la persistenza dei dati riguardanti
	 * i due tipi di utenti: cassa e cliente. 
	 */
	AcquirenteDAO acquirenteDAO;
	
	/**
	 * Oggetto attraverso cui è gestita la persistenza dei dati riguardanti
	 * le informazioni logistiche del cinema: indirizzo, telefono e email; 
	 * oltre alle credenziali dell'amministratore.
	 */
	CinemaInfoDAO cinemaInfoDAO;
	
	/**
	 * Oggetto attraverso cui è gestita la persistenza dei dati rigurardanti
	 * i film in proiezione. 
	 */
	FilmDAO filmDAO;
	
	/**
	 * Oggetto attraverso cui è gestita la persistenza dei dati rigurardanti
	 * le proiezioni.
	 */
	ProiezioneDAO proiezioneDAO;
	
	/**
	 * Oggetto attraverso cui è gestita la persistenza dei dati rigurardanti
	 * le prenotazioni. 
	 */
	PrenotazioneDAO prenotazioneDAO;
	
	/**
	 * Oggetto attraverso cui è gestita la persistenza dei dati rigurardanti
	 * le sale.
	 */
	SalaDAO salaDAO;
	
	/**
	 * Oggetto attraverso cui è gestita la persistenza dei dati rigurardanti
	 * le fascie orarie in cui possono essere proiettati i film.
	 */
	OraDAO oraDAO;
	
	/**
	 * Oggetto attraverso cui è gestita la persistenza dei dati rigurardanti
	 * i posti prenotati da un acquirente per una specifica proiezione.
	 */
	PostoDAO postoDAO;
	
	/**
	 * Costruttore.
	 */
	private DBFacade() {
		acquirenteDAO = new AcquirenteDAO();
		cinemaInfoDAO= new CinemaInfoDAO();
		filmDAO = new FilmDAO();
		proiezioneDAO = new ProiezioneDAO();
		prenotazioneDAO= new PrenotazioneDAO();
		salaDAO=new SalaDAO();
		oraDAO = new OraDAO();
		postoDAO=new PostoDAO();
	}
	
	public static synchronized DBFacade getInstance() {
		if(instance == null) instance = new DBFacade();
		return instance;
	}
	
	/**
	 * Metodo usato per verificare le credenziali di un user:cliente online
	 * o cassa. 
	 * 
	 * @param inputAcquirente oggetto che contiene username e password che andranno
	 * 						  verificate
	 * @return vero o falso a seconda se l'utente è registrato 
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public boolean login(Acquirente inputAcquirente) throws SQLException {
		return acquirenteDAO.login(inputAcquirente);
	}
	
	/**
	 * Metodo usato per registrare le credenziali e le informazioni personali di un 
	 * cliente online. 
	 * 
	 * @param inputCliente oggetto che contiene tutti gli attirbuti che andrnno
	 * 					   registrati. 
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public void registrazione(Acquirente inputAcquirente) throws SQLException{
		acquirenteDAO.registrazione(inputAcquirente);
	}
	
	/**
	 * Metodo usato dall'amministratore per registrare una nuova cassa.
	 * 
	 * @param inputCassa oggetto che contiene tutti gli attributi che andranno 
	 * 					 registrati.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public void aggiungiCassa(Cassa inputCassa) throws SQLException {
		acquirenteDAO.aggiungiCassa(inputCassa);
	}
	
	/**
	 * Metodo usato dall'amministratore per rimuovere una cassa registrata.
	 * 
	 * @param inputCassa oggetto che contiene l'identificativo della cassa da 
	 * 					 cancellare.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public void rimuoviCassa(Cassa inputCassa) throws SQLException {
		acquirenteDAO.rimuoviCassa(inputCassa);
	}
	
	/**
	 * Metodo che restituisce tutte le casse registrate e i loro attributi,
	 * nel DB c'è un attributo tipo che è 1 per i clienti e 2 per le casse. 
	 * 
	 * @return lista delle casse registrate con i loro identificativi e 
	 * 		   password associate.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public ArrayList<Cassa> getTutteCasse() throws SQLException {
		return acquirenteDAO.getTutteCasse();
	}
	
	/**
	 * Metodo usato in fase di registrazione di un nuovo utente che controlla che l'username 
	 * scelta non sia già registrata.
	 * @param inputCassa oggetto che contiene i dati da registrate 
	 * @return vero se l'username non è già registrato, false se è presente come persistenza. 
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public boolean controllaUser(Acquirente inputAcquirente) throws SQLException{
		return acquirenteDAO.controllaUser(inputAcquirente);
	}
	
	/**
	 * Metodo che restituisce tutti gli attributi di un acquirente dato il suo user. 
	 * 
	 * @param inputAcquirente oggetto che contiene l'identificativo dell'acquirente da 
	 * 						  recuperare.
	 * @return Oggetto cliente con tutti i suoi attributi il cui identificativo è uguale
	 * 		   a quello fornito in input.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public Acquirente getUtentebyUser(Acquirente inputAcquirente) throws SQLException {
		return acquirenteDAO.getUtenteByUser(inputAcquirente);
	}
	
	/**
	 * Metodo usato per recuperare il numero di telefono del cinema memorizzato nel DB.
	 * 
	 * @return stringa che contiene l'indirizzo del cinema
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public String getIndirizzo() throws SQLException{
		return cinemaInfoDAO.getIndirizzo();
	}
	
	/**
	 * Metodo usato per recuperare il numero di telefono del cinema memorizzato nel DB.
	 * 
	 * @return stringa che contiene il numero di telefono del cinema.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public String getTelefono() throws SQLException{
		return cinemaInfoDAO.getTelefono();
	}
	
	/**
	 * Metodo usato per recuperare l'email del cinema memorizzato nel DB.
	 * 
	 * @return stringa che contiene l'email del cinema.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public String getEmail() throws SQLException {
		return cinemaInfoDAO.getEmail();
	}
	
	/**
	 * Metodo usato per verificare le credenziali dell'amministratore. 
	 * 
	 * @param inputAcquirente oggetto che contiene username e password dell'
	 * 						  amministratore che andranno poi verificate.
	 * @return true o false a seconda se le credenziali sono corrette. 
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public boolean loginAmministratore(Acquirente inputAcquirente) throws SQLException {
		return cinemaInfoDAO.loginAmministratore(inputAcquirente);
	}
	
	/**
	 * Metodo usato per recuperare username e password dell'aministratore.
	 * 
	 * @return oggetto Acquirente che contiene le credenziali dell'amministratore.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public Acquirente getAmministratore() throws SQLException{
		return cinemaInfoDAO.getAmministratore();
	}
	
	/**
	 * Metodo usato dall'amministratore per registrare una nuovo film.
	 * 
	 * @param inputFilm oggetto che contiene tutti gli attributi che andranno 
	 * 					 registrati.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public void aggiungiFilm(Film inputFilm) throws SQLException {
		filmDAO.aggiungiFilm(inputFilm);
	}
	
	/**
	 * Metodo usato dall'amministratore per rimuovere un film registrato.
	 * 
	 * @param inputFilm oggetto che contiene l'identificativo del film da 
	 * 					cancellare.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public void rimuoviFilm(Film inputFilm) throws SQLException {
		filmDAO.rimuoviFilm(inputFilm);
	}
	
	/**
	 * Metodo che restituisce tutti i film registrati e i loro attributi. 
	 * 
	 * @return lista dei film registrati con i loro identificativi e 
	 * 		   password associate.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public ArrayList<Film> getTuttiFilm() throws SQLException {
		return filmDAO.getTuttiFilm();
	}
	
	/**
	 * Metodo che restituisce tutti gli attributi di un film dato il suo identificativo. 
	 * 
	 * @param inputFilm oggetto che contiene l'identificativo del film da 
	 * 					recuperare.
	 * @return Oggetto film con tutti i suoi attributi il cui identificativo è uguale
	 * 		   a quello fornito in input.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public Film getFilmbyId(Film inputFilm) throws SQLException{
		return filmDAO.getFilmbyId(inputFilm);
	}
	
	/**
	 * Metodo che restituisce tutti gli attributi di un film dato il suo titolo. 
	 * 
	 * @param inputFilm oggetto che contiene il titolo del film da 
	 * 					recuperare.
	 * @return Oggetto film con tutti i suoi attributi il cui titolo è uguale
	 * 		   a quello fornito in input.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public Film getFilmbyTitolo(Film inputFilm) throws SQLException{
		return filmDAO.getFilmbyTitolo(inputFilm);
	}
	
	/**
	 * Metodo che restituisce tutte le fascie orarie possibili in cui si può
	 * programmare una proiezione. 
	 * 
	 * @return lista di tipo String in cui vi sono elencate tutte le fascie
	 * orarie possibili in cui si può programmare una proiezione. 
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public ArrayList<String> getTutteOre() throws SQLException { 
		return oraDAO.getTutteOre();
	}
	
	/**
	 * Metodo che restituisce tutti i singoli posti prenotati attraverso una 
	 * prenotazione effettuata da un acquirente. 
	 * 
	 * @return lista dei posti prenotati.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public ArrayList<Posto> getTuttiPostiByPrenotazione(Prenotazione inputPrenotazione) throws SQLException{
		return postoDAO.getTuttiPostiByPrenotazione(inputPrenotazione);
	}
	
	/**
	 * Metodo che restituisce tutti i posti liberi di una specifica proiezione selezionata una 
	 * riga. 
	 * 
	 * @return lista dei posti liberi scelta una prenotazione e una riga.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public ArrayList<Integer> getPostiLiberiByRiga(Proiezione inputProiezione, Posto inputPosto) throws SQLException{
		return postoDAO.getPostiLiberiByRiga(inputProiezione, inputPosto);
	}	
	
	/**
	 * Metodo che restituisce tutte le prenotazioni future effettuate da un cliente. 
	 * 
	 * @param inputAcquirente contiene l'identificativo del cliente di cui si vuole 
	 * 						  conoscere le prenotazioni future.
	 * @return lista delle prenotazioni future.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public ArrayList<Prenotazione> getPrenotazioniFutureByCliente(Acquirente inputAcquirente) throws SQLException{
		return prenotazioneDAO.getPrenotazioniFutureByCliente(inputAcquirente);
	}
	
	/**
	 * Metodo usato per rendere persistente una nuova prenotazione specificando anche 
	 * i singoli posti occupati.
	 * 
	 * @param inputPrenotazione oggetto che contiene gli attributi della prenotazione
	 * 							da aggiungere.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public void aggiungiPrenotazione(Prenotazione inputProiezione) throws SQLException{
		prenotazioneDAO.aggiungiPrenotazione(inputProiezione);
		prenotazioneDAO.occupaPosti(inputProiezione);
	}
	
	/**
	 * Metodo che restituisce tutti gli attributi di una proiezione dato il suo identificativo. 
	 * 
	 * @param inputProiezione oggetto che contiene l'identificativo della proiezione da 
	 * 					      recuperare.
	 * @return Oggetto Proiezione con tutti i suoi attributi il cui identificativo è uguale
	 * 		   a quello fornito in input.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	
	public int getNumPostiByPrenotazione(Prenotazione inputPrenotazione) throws SQLException {
		return prenotazioneDAO.getNumPostiByPrenotazione(inputPrenotazione);
	}
	
	public Proiezione getProiezioneById(Proiezione inputProiezione) throws SQLException{
		return proiezioneDAO.getProiezioneById(inputProiezione);
	}
	
	/**
	 * Metodo usato dall'amministratore per registrare una nuova proiezione.
	 * 
	 * @param inputProiezione oggetto che contiene tutti gli attributi che andranno 
	 * 					 	  registrati.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public void aggiungiProiezione(Proiezione inputProiezione) throws SQLException {
		 proiezioneDAO.aggiungiProiezione(inputProiezione);
	}
	
	/**
	 * Metodo usato dall'amministratore per rimuovere una proiezione registrata.
	 * 
	 * @param inputProiezione oggetto che contiene l'identificativo della proiezione da 
	 * 					      cancellare.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public void rimuoviProiezione(Proiezione inputProiezione) throws SQLException {
		proiezioneDAO.rimuoviProiezione(inputProiezione);
	}
	
	/**
	 * Metodo che restituisce tutte le proiezioni future programmate. 
	 * 
	 * @return lista delle proiezioni future programmate con tutti i loro 
	 * 		   attributi.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public ArrayList<Proiezione> getTutteProiezioniFuture() throws SQLException {
		return proiezioneDAO.getTutteProiezioniFuture();
	}
	
	/**
	 * Metodo che restituisce la proiezione selezionando film, data e ora;
	 * non è possibile programmare lo stesso film contemporaneamente in due sale 
	 * diverse. 
	 * 
	 * @param inputProiezione oggetto che contiene il film, la data e
	 * 						  l'ora scelti. 
	 * @return oggetto Proiezione i cui attributi film, ora e data corrispondono a  
	 * 		   quelli da input.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public Proiezione getProiezioneByFilmGiornoOra(Proiezione inputProiezione) throws SQLException{ //possibilita di cambiare e togliere questo restituendo un array di proiezioni al metodo sotto
		return proiezioneDAO.getProiezioneByFilmGiornoOra(inputProiezione);
	}
	
	/**
	 * Metodo che restituisce tutti i giorni futuri in cui un film sarà 
	 * priettato.
	 * 
	 * @param inputFilm oggetto che contiene il film per il quale si vuole conoscere
	 * 					i gironi di proiezione. 
	 * @return lista di tipo String contenente tutti i giorni in cui il film scelto viene
	 * 		   proiettato. 
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public ArrayList<String> getGiorniByFilm(Film inputFilm) throws SQLException{ 
		return proiezioneDAO.getGiorniByFilm(inputFilm);
	}
	
	/**
	 * Metodo che restituisce tutte le ore in cui un film sarà priettato scelto un giorno
	 * specifico.
	 * 
	 * @param inputProiezione oggetto che contiene il film e il giorno per cui si vuole conoscere
	 * 						  gli orari di proiezione. 
	 * @return lista di tipo String contenente tutte le ore in cui il film scelto viene
	 * 		   proiettato nel giorno selezionato. 
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public ArrayList<String> getOreByProiezione(Proiezione inputProiezione) throws SQLException{ 
		return proiezioneDAO.getOreByProiezione(inputProiezione);
	}
	
	/**
	 * Metodo che restituisce il numero di proiezioni programmate dato l'identificativo di 
	 * un film. 
	 * 
	 * @param inputFilm oggetto che contiene l'identificativo del film di cui contare le 
	 * 		  proiezioni.
	 * @return Numero di proiezioni programmate per il film fornito in input.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public int getNumProiezioniByFilm(Film inputFilm) throws SQLException {
		return proiezioneDAO.getNumProiezioniByFilm(inputFilm);
	}
	
	/**
	 * Metodo che restituisce tutti gli attributi di una sala dato il suo identificativo. 
	 * 
	 * @param inputSala oggetto che contiene l'identificativo della sala da 
	 * 					recuperare.
	 * @return Oggetto sala con tutti i suoi attributi il cui identificativo è uguale
	 * 		   a quello fornito in input.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public Sala getSalaById(Sala inputSala) throws SQLException{
		return salaDAO.getSalaById(inputSala);
	}
	
	/**
	 * Metodo che restituisce tutte le sale registrate e i loro attributi. 
	 * 
	 * @return lista delle sale registrate con i loro attributi.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public ArrayList<Sala> getTutteSale() throws SQLException {
		return salaDAO.getTutteSale();
	}

	
	public void initializeDB() throws SQLException, IOException {
		Connection conn = null; conn = MySQLConnectionFactory.connect(conn); 
	     
		Statement st1 = conn.createStatement();
		  
		st1.execute("SET SQL_SAFE_UPDATES = 0;"); 
		st1.executeUpdate("DELETE FROM cinema_info;");
		st1.executeUpdate("DELETE FROM posto;");
		st1.executeUpdate("DELETE FROM prenotazione;");
		st1.executeUpdate("DELETE FROM proiezione;");
		st1.executeUpdate("DELETE FROM film;");
		st1.executeUpdate("DELETE FROM sala;");
		st1.executeUpdate("DELETE FROM ora;");
		
		st1.executeUpdate("INSERT INTO `oocinema`.`cinema_info` (`nome`, `telefono`, `user`, `email`, `password`, `indirizzo`) "
				+ "VALUES ('OOCINEMA - PAVIA', '0382 - 000000', 'admin', 'oocinema.project@gmail.com', 'admin', 'Via Ferrata 5 - 27100 - Italia');");
		st1.executeUpdate("INSERT INTO `oocinema`.`ora` (`ora`) VALUES ('18:00');");
		st1.executeUpdate("INSERT INTO `oocinema`.`ora` (`ora`) VALUES ('19:30');");
		st1.executeUpdate("INSERT INTO `oocinema`.`ora` (`ora`) VALUES ('21 :00');");
		st1.executeUpdate("INSERT INTO `oocinema`.`sala` (`id`, `n_righe`, `n_col`) VALUES (1, 15, 20);");
		st1.executeUpdate("INSERT INTO `oocinema`.`sala` (`id`, `n_righe`, `n_col`) VALUES (2, 15, 20);");
		st1.executeUpdate("INSERT INTO `oocinema`.`sala` (`id`, `n_righe`, `n_col`) VALUES (3, 10, 25);");
		MySQLConnectionFactory.closeConnection(conn);
		 
	}
	
	public String getQuery() throws IOException {
		String query = "";
		String line;
		FileReader f = new FileReader("src/it/unipv/po/oocinema/persistenza/initializeDB.sql");
	    BufferedReader b = new BufferedReader(f);
	    
	    while(true) {
	        line = b.readLine();
	        if(line == null)
	          break;
	        query = query + " "+line;
	      }
	    
	    b.close();
		return query;
		
	}

	public char getTipoByAcquirente(Acquirente acquirente) throws SQLException{
		return acquirenteDAO.getTipoByAcquirente(acquirente);
	}
	
}
