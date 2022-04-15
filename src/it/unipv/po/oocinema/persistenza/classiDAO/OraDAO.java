package it.unipv.po.oocinema.persistenza.classiDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.persistenza.MySQLConnectionFactory;
import it.unipv.po.oocinema.persistenza.interfaccieDAO.IOraDAO;

public class OraDAO implements IOraDAO{
	private Connection conn;


	public OraDAO() {
		super();
	}


	@Override
	public ArrayList<String> getTutteOre() throws SQLException {
		conn = MySQLConnectionFactory.connect(conn);
		
		ArrayList<String> ore = new ArrayList<String>(); 
		
		String query = "SELECT * from ora";
		PreparedStatement st1 = conn.prepareStatement(query);
		ResultSet result = st1.executeQuery();
		
		while (result.next()) {
			ore.add(result.getString("ora"));
		}
		
		MySQLConnectionFactory.closeConnection(conn);

		return ore;
		
	}
	
	
		
}
