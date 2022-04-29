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
	public void aggiungiProiezione(Proiezione inputProiezione) throws SQLException;
	public void rimuoviProiezione(Proiezione inputProiezione) throws SQLException;
	public Proiezione getProiezioneById(Proiezione inputProiezione) throws SQLException;
	public ArrayList<Proiezione> getTutteProiezioniFuture() throws SQLException;
	public Proiezione getProiezioneByFilmGiornoOra(Proiezione inputProiezione) throws SQLException; //non è possibile avere lo stesso film contemporaneamente in due sale diverse
	public ArrayList<String> getGiorniByFilm(Film inputFilm) throws SQLException;
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
