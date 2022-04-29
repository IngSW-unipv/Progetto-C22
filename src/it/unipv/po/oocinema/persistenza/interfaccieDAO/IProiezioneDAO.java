package it.unipv.po.oocinema.persistenza.interfaccieDAO;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.model.cinema.Proiezione;

/**
 * Interfaccia che contiene i metodi per gestire la persistenza di dati 
 * delle proiezioni. 
 * 
 * @author GoF
 */
public interface IProiezioneDAO {
	
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
	public Proiezione getProiezioneById(Proiezione inputProiezione) throws SQLException;
	
	/**
	 * Metodo usato dall'amministratore per registrare una nuova proiezione.
	 * 
	 * @param inputProiezione oggetto che contiene tutti gli attributi che andranno 
	 * 					 	  registrati.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public void aggiungiProiezione(Proiezione inputProiezione) throws SQLException;
	
	/**
	 * Metodo usato dall'amministratore per rimuovere una proiezione registrata.
	 * 
	 * @param inputProiezione oggetto che contiene l'identificativo della proiezione da 
	 * 					      cancellare.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public void rimuoviProiezione(Proiezione inputProiezione) throws SQLException;
	
	/**
	 * Metodo che restituisce tutte le proiezioni future programmate. 
	 * 
	 * @return lista delle proiezioni future programmate con tutti i loro 
	 * 		   attributi.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public ArrayList<Proiezione> getTutteProiezioniFuture() throws SQLException;
	
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
	public Proiezione getProiezioneByFilmGiornoOra(Proiezione inputProiezione) throws SQLException; //non è possibile avere lo stesso film contemporaneamente in due sale diverse
	
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
	public ArrayList<String> getGiorniByFilm(Film inputFilm) throws SQLException;
	
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
	public ArrayList<String> getOreByProiezione(Proiezione inputProiezione) throws SQLException;
	
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
