package it.unipv.po.oocinema.persistenza.interfaccieDAO;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.acquirenti.Acquirente;
import it.unipv.po.oocinema.model.acquirenti.Cassa;

/**
 * Interfaccia che contiene i metodi per gestire la persistenza di dati degli 
 * acquirenti, online e fisici. 
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
	public void registrazione(Acquirente inputAcquirente) throws SQLException;
	
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
	 * Metodo che restituisce tutte le casse registrate e i loro attributi,
	 * nel DB c'è un attributo tipo che è 1 per i clienti e 2 per le casse. 
	 * 
	 * @return lista delle casse registrate con i loro identificativi e 
	 * 		   password associate.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public ArrayList<Cassa> getTutteCasse() throws SQLException;
	
	/**
	 * Metodo usato in fase di registrazione di un nuovo utente che controlla che l'username 
	 * scelta non sia già registrata.
	 * @param inputCassa oggetto che contiene i dati da registrate 
	 * @return vero se l'username non è già registrato, false se è presente come persistenza. 
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	public boolean controllaUser(Acquirente inputAcquirente) throws SQLException;
	
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
	public Acquirente getUtenteByUser(Acquirente inputAcquirente)throws SQLException;

	/**
	 * 
	 * @param acquirente
	 * @return tipo dell'acquirente dato l'user
	 * @throws SQLException
	 */
	public int getTipoByAcquirente(Acquirente acquirente) throws SQLException;

}
