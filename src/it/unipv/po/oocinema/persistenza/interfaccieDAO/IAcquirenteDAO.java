package it.unipv.po.oocinema.persistenza.interfaccieDAO;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.acquirenti.Acquirente;
import it.unipv.po.oocinema.model.acquirenti.Cassa;
import it.unipv.po.oocinema.model.acquirenti.Cliente;

/**
 * Interfaccia che contiene i metodi per gestire la persistenza di dati degli 
 * acquirenti, online e fisici, e dell'amministratore. 
 * 
 * @author GoF
 */

public interface IAcquirenteDAO {
	
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
	public boolean login(Acquirente inputAcquirente) throws SQLException;
	
	/**
	 * Metodo usato per registrare le credenziali e le informazioni personali di un 
	 * cliente online. 
	 * 
	 * @param inputCliente oggetto che contiene tutti gli attirbuti che andrnno
	 * 					   registrati. 
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public void registrazione(Cliente inputCliente) throws SQLException;
	
	/**
	 * Metodo usato dall'amministratore per registrare una nuova cassa.
	 * 
	 * @param inputCassa oggetto che contiene tutti gli attributi che andranno 
	 * 					 registrati.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public void aggiungiCassa(Cassa inputCassa) throws SQLException;
	
	/**
	 * Metodo usato dall'amministratore per rimuovere una cassa registrata.
	 * 
	 * @param inputCassa oggetto che contiene l'identificativo della cassa da 
	 * 					 cancellare.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public void rimuoviCassa(Cassa inputCassa) throws SQLException;
	
	/**
	 * Metodo che restituisce tutte le casse registrate e i loro attributi. 
	 * 
	 * @return lista delle casse registrate con i loro identificativi e 
	 * 		   password associate.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public ArrayList<Cassa> getTutteCasse() throws SQLException;
	
	/**
	 * Metodo che restituisce la tipologia di un utente tra: cliente online, cassa e 
	 * amministratore
	 * 
	 * @param inputCassa oggetto che contiene l'identificativo dell'utente.
	 * @return numero intero: 0 per l'amministratore, 1 per il cliente e 2 per la cassa. 
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public int getTipoByUser(Acquirente inputAcquirente) throws SQLException;
	
	/**
	 * Metodo usato in fase di registrazione di un nuovo utente che controlla che l'username 
	 * scelta non sia già registrata.
	 * @param inputCassa oggetto che contiene i dati da registrate 
	 * @return vero se l'username non è già registrato, false se è presente come persistenza. 
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public boolean controllaUser(Acquirente inputAcquirente) throws SQLException;

	public Acquirente getUtenteByUser(Acquirente inputAcquirente)throws SQLException;

}
