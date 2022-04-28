package it.unipv.po.oocinema.persistenza.interfaccieDAO;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.cinema.Film;

public interface IFilmDAO {
	
	/**
	 * Metodo usato dall'amministratore per registrare una nuovo film.
	 * 
	 * @param inputFilm oggetto che contiene tutti gli attributi che andranno 
	 * 					 registrati.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public void aggiungiFilm(Film inputFilm) throws SQLException;
	
	/**
	 * Metodo usato dall'amministratore per rimuovere un film registrato.
	 * 
	 * @param inputFilm oggetto che contiene l'identificativo del film da 
	 * 					cancellare.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public void rimuoviFilm(Film inputFilm) throws SQLException;
	
	/**
	 * Metodo che restituisce tutti i film registrati e i loro attributi. 
	 * 
	 * @return lista dei film registrati con i loro identificativi e 
	 * 		   password associate.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public ArrayList<Film> getTuttiFilm() throws SQLException;
	
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
	public Film getFilmbyId(Film inputFilm) throws SQLException;
	
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
	public Film getFilmbyTitolo(Film inputFilm) throws SQLException; //può essercene più di uno?
	
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
	public int getNumProiezioniByFilm(Film inputFilm) throws SQLException; //non va in proiezioneDAO?
}
