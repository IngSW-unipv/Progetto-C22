package it.unipv.po.oocinema.persistenza.classiDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unipv.po.oocinema.model.acquirenti.Acquirente;
import it.unipv.po.oocinema.persistenza.MySQLConnectionFactory;
import it.unipv.po.oocinema.persistenza.interfaccieDAO.ICinemaInfoDAO;

/**
 * Classe che implementa l'interfaccia ICinemaInfoDAO e quindi contiene i metodi per
 * gestire la persistenza dei dati logistici del cinema: indirizzo, telefono e email.  
 * 
 * @author GoF
 */
public class CinemaInfoDAO implements ICinemaInfoDAO {

	/**
	 * Connessione al database.
	 */
	private Connection conn;

	/**
	 * Costruttore.
	 */
	public CinemaInfoDAO() {
		super();
	}
	
	/**
	 * Metodo usato per recuperare l'indirizzo del cinema memorizzato nel DB.
	 * 
	 * @return stringa che contiene l'indirizzo del cinema.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	@Override
	public String getIndirizzo() throws SQLException {
		conn = MySQLConnectionFactory.connect(conn);
		PreparedStatement st1;
		ResultSet result;
		String query = "SELECT indirizzo FROM cinema_info;";
		st1 = conn.prepareStatement(query);
		result=st1.executeQuery(); //fare if?
		String indirizzo = result.getString("indirizzo");
		MySQLConnectionFactory.closeConnection(conn);
		return indirizzo;
	}
	
	/**
	 * Metodo usato per recuperare il numero di telefono del cinema memorizzato nel DB.
	 * 
	 * @return stringa che contiene il numero di telefono del cinema.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	@Override
	public String getTelefono() throws SQLException {
		conn = MySQLConnectionFactory.connect(conn);
		PreparedStatement st1;
		ResultSet result;
		String query = "SELECT telefono FROM cinema_info;";
		st1 = conn.prepareStatement(query);
		result=st1.executeQuery(query);
		String telefono = result.getString("telefono");
		MySQLConnectionFactory.closeConnection(conn);
		return telefono;
	}
	
	/**
	 * Metodo usato per recuperare l'email del cinema memorizzato nel DB.
	 * 
	 * @return stringa che contiene l'email del cinema.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	@Override
	public String getEmail() throws SQLException {
		conn = MySQLConnectionFactory.connect(conn);
		PreparedStatement st1;
		ResultSet result;
		String query = "SELECT email FROM cinema_info;";
		st1 = conn.prepareStatement(query);
		result=st1.executeQuery(query);
		String email = result.getString("telefono");
		MySQLConnectionFactory.closeConnection(conn);
		return email;
	}

	@Override
	public boolean loginAdmin(Acquirente inputAcquirente) throws SQLException{
		conn = MySQLConnectionFactory.connect(conn);
		PreparedStatement st1;
		ResultSet result;
		String query = "SELECT password from cinema_info where user = ?;";
		st1 = conn.prepareStatement(query);
		st1.setString(1, inputAcquirente.getUser());
		result=st1.executeQuery();
		boolean log = false;
		if( result.next() && result.getString("password").equals(inputAcquirente.getPassword())) {
			log = true;
		}	
		MySQLConnectionFactory.closeConnection(conn);
		return log;
	}

}
