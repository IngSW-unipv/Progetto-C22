package it.unipv.po.oocinema.persistenza.interfaccieDAO;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.cinema.Sala;

/**
 * Interfaccia che contiene i metodi per gestire la persistenza di dati
 * delle sale. 
 * 
 * @author GoF
 */
public interface ISalaDAO {
	
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
	public Sala getSalaById(Sala inputSala) throws SQLException;
	
	/**
	 * Metodo che restituisce tutte le sale registrate e i loro attributi. 
	 * 
	 * @return lista delle sale registrate con i loro attributi.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public ArrayList<Sala> getTutteSale() throws SQLException;
	
}
