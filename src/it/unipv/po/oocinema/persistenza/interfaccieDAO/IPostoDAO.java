package it.unipv.po.oocinema.persistenza.interfaccieDAO;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.cinema.Posto;
import it.unipv.po.oocinema.model.cinema.Proiezione;
import it.unipv.po.oocinema.model.prenotazione.Prenotazione;

/**
 * Interfaccia che contiene i metodi per gestire la persistenza di dati 
 * dei posti occupati per una specifica prenotazione. 
 * 
 * @author GoF
 */
public interface IPostoDAO {
	
	/**
	 * Metodo che restituisce tutti i singoli posti prenotati attraverso una 
	 * prenotazione effettuata da un acquirente. 
	 * 
	 * @return lista dei posti prenotati.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public ArrayList<Posto> getTuttiPostiByPrenotazione(Prenotazione inputPrenotazione) throws SQLException;
	
	//public ArrayList<Posto> getRigheLibere(Proiezione inputProiezione) throws SQLException;
	
	/**
	 * Metodo che restituisce tutti i posti liberi di una specifica proiezione selezionata una 
	 * riga. 
	 * 
	 * @return lista dei posti liberi scelta una prenotazione e una riga.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public ArrayList<Integer> getPostiLiberiByRiga(Proiezione inputProiezione, Posto inputPosto) throws SQLException;
}
