package it.unipv.po.oocinema.persistenza.classiDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.persistenza.MySQLConnectionFactory;
import it.unipv.po.oocinema.persistenza.interfaccieDAO.ISalaDAO;
import it.unipv.po.oocinema.model.cinema.Sala; 

/**
 * Classe che implementa l'interfaccia ISalaDAO e quindi contiene i metodi per
 * gestire la persistenza di dati delle sale. 
 * 
 * @author GoF
 */
public class SalaDAO implements ISalaDAO {
	
	/**
	 * Connessione al database.
	 */
	private Connection conn;

	/**
	 * Costruttore.
	 */
	public SalaDAO() {
		super();
	}
	
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
	@Override
	public Sala getSalaById(Sala inputSala) throws SQLException {
		conn = MySQLConnectionFactory.connect(conn);
		PreparedStatement st1;
		ResultSet result;
		String query = "SELECT * FROM sala WHERE id = ?;";
		st1 = conn.prepareStatement(query);
		st1.setString(1, ""+inputSala.getId());
		result = st1.executeQuery();
		Sala sala;
		if(result.next())
			sala = new Sala (result.getInt("id"), result.getInt("n_righe"), result.getInt("n_col"));
		else sala = null;
		MySQLConnectionFactory.closeConnection(conn);
		return sala;
	}
	
	/**
	 * Metodo che restituisce tutte le sale registrate e i loro attributi. 
	 * 
	 * @return lista delle sale registrate con i loro attributi.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	@Override
	public ArrayList<Sala> getTutteSale() throws SQLException {
		conn = MySQLConnectionFactory.connect(conn);
		PreparedStatement st1;
		ResultSet result;
		String query = "SELECT * FROM sala;";
		st1 = conn.prepareStatement(query);
		result = st1.executeQuery();
		ArrayList<Sala> sala = new ArrayList<Sala>();
		while (result.next()) {
			sala.add(new Sala(result.getInt("id"), result.getInt("n_righe"), result.getInt("n_col")));
		}
		MySQLConnectionFactory.closeConnection(conn);
		return sala;
	}
}
