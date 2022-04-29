package it.unipv.po.oocinema.persistenza.interfaccieDAO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Interfaccia che contiene un metodo usato per recuperare le 
 * fascie orarie predefinite. 
 * 
 * @author GoF
 */
public interface IOraDAO {
	
	/**
	 * Metodo che restituisce tutte le fascie orarie possibili in cui si può
	 * programmare una proiezione. 
	 * 
	 * @return lista di tipo String in cui vi sono elencate tutte le fascie
	 * orarie possibili in cui si può programmare una proiezione. 
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public ArrayList<String> getTutteOre() throws SQLException;
}
