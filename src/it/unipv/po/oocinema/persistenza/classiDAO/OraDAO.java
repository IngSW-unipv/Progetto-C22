package it.unipv.po.oocinema.persistenza.classiDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.persistenza.MySQLConnectionFactory;
import it.unipv.po.oocinema.persistenza.interfaccieDAO.IOraDAO;

/**
 * Classe che implementa l'interfaccia IOraDAO e quindi un metodo usato per
 * recuperare le fascie orarie predefinite. 
 * 
 * @author GoF
 */
public class OraDAO implements IOraDAO{
	
	/**
	 * Connessione al database.
	 */
	private Connection conn;

	/**
	 * Costruttore.
	 */
	public OraDAO() {
		super();
	}
	
	/**
	 * Metodo che restituisce tutte le fascie orarie possibili in cui si può
	 * programmare una proiezione. 
	 * 
	 * @return lista di tipo String in cui vi sono elencate tutte le fascie
	 * orarie possibili in cui si può programmare una proiezione. 
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	@Override
	public ArrayList<String> getTutteOre() throws SQLException {
		conn = MySQLConnectionFactory.connect(conn);
		String query = "SELECT * from ora";
		PreparedStatement st1 = conn.prepareStatement(query);
		ResultSet result = st1.executeQuery();
		ArrayList<String> ore = new ArrayList<String>(); 
		while (result.next()) {
			ore.add(result.getString("ora"));
		}
		MySQLConnectionFactory.closeConnection(conn);
		return ore;
	}
}
