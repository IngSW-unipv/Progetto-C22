package it.unipv.po.oocinema.persistenza.interfaccieDAO;

import java.sql.SQLException;

import it.unipv.po.oocinema.model.acquirenti.Acquirente;

/**
 * Interfaccia che contiene i metodi per gestire la persistenza dei dati 
 * dati logistici del cinema: indirizzo, telefono e email; 
 * oltre alle credenziali dell'amministratore.  
 * 
 * @author GoF
 */
public interface ICinemaInfoDAO {
	
	/**
	 * Metodo usato per recuperare il numero di telefono del cinema memorizzato nel DB.
	 * 
	 * @return stringa che contiene l'indirizzo del cinema.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public String getIndirizzo() throws SQLException;
	
	/**
	 * Metodo usato per recuperare il numero di telefono del cinema memorizzato nel DB.
	 * 
	 * @return stringa che contiene il numero di telefono del cinema.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public String getTelefono() throws SQLException;
	
	/**
	 * Metodo usato per recuperare l'email del cinema memorizzato nel DB.
	 * 
	 * @return stringa che contiene l'email del cinema.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public String getEmail() throws SQLException;
	
	public boolean loginAdmin(Acquirente inputAcquirente) throws SQLException;

	public Acquirente getAdmin() throws SQLException;
}
