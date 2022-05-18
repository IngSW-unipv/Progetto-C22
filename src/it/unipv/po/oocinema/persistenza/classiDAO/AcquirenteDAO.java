package it.unipv.po.oocinema.persistenza.classiDAO; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.acquirenti.Acquirente;
import it.unipv.po.oocinema.model.acquirenti.Cassa;
import it.unipv.po.oocinema.persistenza.MySQLConnectionFactory;
import it.unipv.po.oocinema.persistenza.interfaccieDAO.IAcquirenteDAO;

/**
 * Classe che implementa l'interfaccia IAcquirenteDAO e quindi contiene i metodi per
 * gestire la persistenza di dati degli acquirenti, online e fisici. 
 * 
 * @author GoF
 */
public class AcquirenteDAO implements IAcquirenteDAO {

	/**
	 * Connessione al database.
	 */
	private Connection conn;

	/**
	 * Costruttore.
	 */
	public AcquirenteDAO() {
		super();
	}
	
	/**
	 * Metodo usato per verificare le credenziali di un generico user:cliente online
	 * cassa o amministratore. 
	 * 
	 * @param inputAcquirente oggetto che contiene username e password che andranno
	 * 						  verificate
	 * @return true o false a seconda se l'utente è registrato 
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	@Override
	public boolean login(Acquirente inputAcquirente) throws SQLException {
		conn = MySQLConnectionFactory.connect(conn);
		PreparedStatement st1;
		ResultSet result;
		String query = "SELECT psw from acquirente where user = ?;";
		st1 = conn.prepareStatement(query);
		st1.setString(1, inputAcquirente.getUser());
		result=st1.executeQuery();
		boolean log = false;
		if( result.next() && result.getString("psw").equals(inputAcquirente.getPassword())) {
			log = true;
		}	
		MySQLConnectionFactory.closeConnection(conn);
		return log;
	}	
	
	/**
	 * Metodo usato per registrare le credenziali e le informazioni personali di un 
	 * cliente online. 
	 * 
	 * @param inputCliente oggetto che contiene tutti gli attirbuti che andrnno
	 * 					   registrati. 
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	@Override
	public void registrazione(Acquirente inputAcquirente) throws SQLException{
		conn = MySQLConnectionFactory.connect(conn);
		String query = "INSERT INTO acquirente VALUES(?,?,?,?,?,?)";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setString(1, inputAcquirente.getUser());
		st1.setString(2, inputAcquirente.getPassword());
		st1.setInt(3, 1);
		st1.setString(4, inputAcquirente.getNome());
		st1.setString(5, inputAcquirente.getCognome());
		st1.setString(6, inputAcquirente.getCompleanno());
		st1.executeUpdate();
		MySQLConnectionFactory.closeConnection(conn);
	}
	
	/**
	 * Metodo usato dall'amministratore per registrare una nuova cassa.
	 * 
	 * @param inputCassa oggetto che contiene tutti gli attributi che andranno 
	 * 					 registrati.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	@Override
	public void aggiungiCassa(Cassa inputCassa) throws SQLException{
		conn = MySQLConnectionFactory.connect(conn);
		String query = "INSERT INTO acquirente VALUES(?,?,?,?,null,null)";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setString(1, inputCassa.getUser());
		st1.setString(2, inputCassa.getPassword());
		st1.setInt(3, 2);
		st1.setString(4, inputCassa.getCompleanno());
		st1.executeUpdate();
		MySQLConnectionFactory.closeConnection(conn);
	}
	
	/**
	 * Metodo usato dall'amministratore per rimuovere una cassa registrata.
	 * 
	 * @param inputCassa oggetto che contiene l'identificativo della cassa da 
	 * 					 cancellare.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	@Override
	public void rimuoviCassa(Cassa inputCassa) throws SQLException {
		conn = MySQLConnectionFactory.connect(conn);
		String query = "DELETE FROM acquirente where user=?";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setString(1, inputCassa.getUser());
		st1.executeUpdate();
		MySQLConnectionFactory.closeConnection(conn);
	}

	/**
	 * Metodo che restituisce tutte le casse registrate e i loro attributi,
	 * nel DB c'è un attributo tipo che è 1 per i clienti e 2 per le casse. 
	 * 
	 * @return lista delle casse registrate con i loro identificativi e 
	 * 		   password associate.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	@Override
	public ArrayList<Cassa> getTutteCasse() throws SQLException {
		conn = MySQLConnectionFactory.connect(conn);
		String query = "SELECT * FROM acquirente where tipo= ?;";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setInt(1, 2);
		ResultSet result = st1.executeQuery();
		ArrayList<Cassa> casse = new ArrayList<Cassa>();
		while (result.next()) {
			casse.add(new Cassa(result.getString("user"),result.getString("psw")));
		}
		MySQLConnectionFactory.closeConnection(conn);
		return casse;
	}
	
	/**
	 * Metodo usato in fase di registrazione di un nuovo utente che controlla che l'username 
	 * scelta non sia già registrata.
	 * 
	 * @param inputCassa oggetto che contiene i dati da registrate 
	 * @return vero se l'username non è già registrato, false se è presente come persistenza. 
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	@Override
	public boolean controllaUser(Acquirente inputAcquirente) throws SQLException{
		conn = MySQLConnectionFactory.connect(conn);
		String query = "SELECT user FROM acquirente where user= ?;";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setString(1, inputAcquirente.getUser());
		ResultSet result = st1.executeQuery();
		if(result.next()) {
			MySQLConnectionFactory.closeConnection(conn);
			return false;
		}
		else {
			MySQLConnectionFactory.closeConnection(conn);
			return true;
		}
	}
	
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
	@Override
	public Acquirente getUtenteByUser(Acquirente inputAcquirente) throws SQLException{
		conn = MySQLConnectionFactory.connect(conn);
		String query = "SELECT * FROM acquirente where user= ?;";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setString(1, inputAcquirente.getUser());
		ResultSet result = st1.executeQuery();
		if(result.next()) {
			Acquirente a = new Acquirente(result.getString("user"),result.getString("psw"),
					result.getString("nome"),result.getString("cognome"),result.getString("compleanno"));
			MySQLConnectionFactory.closeConnection(conn);
			return a;
		} else {
			MySQLConnectionFactory.closeConnection(conn);
			return null;
		}	
	}

	public int getTipoByAcquirente(Acquirente acquirente) throws SQLException{
		conn = MySQLConnectionFactory.connect(conn);
		String query = "SELECT tipo FROM acquirente where user= ?;";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setString(1, acquirente.getUser());
		ResultSet result = st1.executeQuery();
		int tipo = -1;
		if(result.next()) {
			tipo = result.getInt("tipo");
		} 
		
		MySQLConnectionFactory.closeConnection(conn);
		
		return tipo;
			
	}
}
