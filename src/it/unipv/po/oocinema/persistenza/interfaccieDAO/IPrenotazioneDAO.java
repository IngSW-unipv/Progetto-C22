package it.unipv.po.oocinema.persistenza.interfaccieDAO;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.prenotazione.Prenotazione;
import it.unipv.po.oocinema.model.acquirenti.Acquirente;

/**
 * Interfaccia che contiene i metodi per gestire la persistenza di dati
 * delle prenotazioni completate. 
 * 
 * @author GoF
 */
public interface IPrenotazioneDAO {
	
	/**
	 * Metodo che restituisce tutte le prenotazioni future effettuate da un cliente. 
	 * 
	 * @param inputAcquirente contiene l'identificativo del cliente di cui si vuole 
	 * 						  conoscere le prenotazioni future.
	 * @return lista delle prenotazioni future.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public ArrayList<Prenotazione> getPrenotazioniFutureByCliente(Acquirente inputAcquirente) throws SQLException;
	
	/**
	 * Metodo usato per rendere persistente una nuova prenotazione.
	 * 
	 * @param inputPrenotazione oggetto che contiene gli attributi della prenotazione
	 * 							da aggiungere.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public void aggiungiPrenotazione(Prenotazione inputPrenotazione) throws SQLException;
	
	/**
	 * Metodo usato per registrare tutti i posti specifici occupati da una prenotazione.
	 * 
	 * @param inputPrenotazione oggetto che contiene gli attributi della prenotazione.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public void occupaPosti(Prenotazione inputPrenotazione) throws SQLException;
	
	public int getNumPostiByPrenotazione(Prenotazione inputPrenotazione) throws SQLException;
}
