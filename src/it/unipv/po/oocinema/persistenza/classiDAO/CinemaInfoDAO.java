package it.unipv.po.oocinema.persistenza.classiDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.unipv.po.oocinema.persistenza.MySQLConnectionFactory;
import it.unipv.po.oocinema.persistenza.interfaccieDAO.ICinemaInfoDAO;

public class CinemaInfoDAO implements ICinemaInfoDAO {

	
	private Connection conn;


	public CinemaInfoDAO() {
		super();
	}
	
	@Override
	public String getIndirizzo() throws SQLException {
		conn = MySQLConnectionFactory.connect(conn);
		PreparedStatement st1;
		ResultSet result;
		
		String query = "SELECT indirizzo FROM cinema_info;";
		st1 = conn.prepareStatement(query);
		result=st1.executeQuery(query);

		String ris = result.getString("indirizzo");
		MySQLConnectionFactory.closeConnection(conn);
		return ris;
		
	}

	@Override
	public String getTelefono() throws SQLException {
		conn = MySQLConnectionFactory.connect(conn);
		PreparedStatement st1;
		ResultSet result;
		
		String query = "SELECT telefono FROM cinema_info;";
		st1 = conn.prepareStatement(query);
		result=st1.executeQuery(query);
		
		String ris = result.getString("telefono");
		MySQLConnectionFactory.closeConnection(conn);
		return ris;
	}

	@Override
	public String getEmail() throws SQLException {
		// METTERE EMAIL IN WORKBENCH
		return null;
	}

}
