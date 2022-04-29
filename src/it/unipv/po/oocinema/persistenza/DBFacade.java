package it.unipv.po.oocinema.persistenza;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.acquirenti.Acquirente;
import it.unipv.po.oocinema.model.acquirenti.Cassa;
import it.unipv.po.oocinema.model.acquirenti.Cliente;
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
	
	/**
	 * Oggetto attraverso cui è gestita la persistenza dei dati riguardanti
	 * i tre tipi di utenti: amministratore, cassa e cliente. 
	 */
	AcquirenteDAO acquirenteDAO;
	
	/**
	 * Oggetto attraverso cui è gestita la persistenza dei dati riguardanti
	 * le informazioni logistiche del cinema: indirizzo, telefono e email.
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
	 * 
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public DBFacade() {
		acquirenteDAO = new AcquirenteDAO();
		cinemaInfoDAO= new CinemaInfoDAO();
		filmDAO = new FilmDAO();
		proiezioneDAO = new ProiezioneDAO();
		prenotazioneDAO= new PrenotazioneDAO();
		salaDAO=new SalaDAO();
		oraDAO = new OraDAO();
		postoDAO=new PostoDAO();
	}
	
	/**
	 * Metodo usato per verificare le credenziali di un generico user:cliente online
	 * cassa o amministratore. 
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
	public void registrazione(Cliente inputCliente) throws SQLException{
		acquirenteDAO.registrazione(inputCliente);
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
	
	public boolean loginAdmin(Acquirente inputAcquirente) throws SQLException {
		return cinemaInfoDAO.loginAdmin(inputAcquirente);
	}

	public Acquirente getAdmin() throws SQLException{
		return cinemaInfoDAO.getAdmin();
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
	
	public void aggiungiProiezione(Proiezione inputProiezione) throws SQLException {
		 proiezioneDAO.aggiungiProiezione(inputProiezione);
	}
	
	public void rimuoviProiezione(Proiezione inputProiezione) throws SQLException {
		proiezioneDAO.rimuoviProiezione(inputProiezione);
	}
	
	public Proiezione getProiezioneById(Proiezione inputProiezione) throws SQLException{
		return proiezioneDAO.getProiezioneById(inputProiezione);
	}
	
	public ArrayList<Proiezione> getTutteProiezioniFuture() throws SQLException {
		return proiezioneDAO.getTutteProiezioniFuture();
	}
	
	public Proiezione getProiezioneByFilmGiornoOra(Proiezione inputProiezione) throws SQLException{ //possibilita di cambiare e togliere questo restituendo un array di proiezioni al metodo sotto
		return proiezioneDAO.getProiezioneByFilmGiornoOra(inputProiezione);
	}
	
	public ArrayList<String> getOreByProiezione(Proiezione inputProiezione) throws SQLException{ 
		return proiezioneDAO.getOreByProiezione(inputProiezione);
	}
	
	public ArrayList<String> getGiorniByFilm(Film inputFilm) throws SQLException{ 
		return proiezioneDAO.getGiorniByFilm(inputFilm);
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
	
	public ArrayList<Sala> getTutteSale() throws SQLException {
		return salaDAO.getTutteSale();
	}
	
	public Sala getSalaById(Sala inputSala) throws SQLException{
		return salaDAO.getSalaById(inputSala);
	}

	public ArrayList<String> getTutteOre() throws SQLException { 
		return oraDAO.getTutteOre();
	}

	/*
	 * public ArrayList<Posto> getRigheLibere(Proiezione inputProiezione) throws
	 * SQLException{ //riga e non fila cambiare nel database return
	 * postoDAO.getRigheLibere(inputProiezione); }
	 */
	
	public ArrayList<Integer> getPostiLiberiByRiga(Proiezione inputProiezione, Posto inputPosto) throws SQLException{
		return postoDAO.getPostiOccupatiByRiga(inputProiezione, inputPosto);
	}	
}
