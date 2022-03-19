package it.unipv.po.oocinema.persistenza.classiDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unipv.po.oocinema.model.acquirenti.Acquirente;
import it.unipv.po.oocinema.persistenza.MySQLConnectionFactory;
import it.unipv.po.oocinema.persistenza.interfaccieDAO.IAcquirenteDAO;

public class AcquirenteDAO implements IAcquirenteDAO {

	private Connection conn;


	public AcquirenteDAO() {
		super();
		conn = MySQLConnectionFactory.connect(conn);
	}

	@Override
	public boolean login(Acquirente inputAcq) throws SQLException {
		conn = MySQLConnectionFactory.connect(conn);
		PreparedStatement st1;
		ResultSet result;
		
		String query = "SELECT psw from acquirente where user = ?;";
		st1 = conn.prepareStatement(query);
		st1.setString(1, inputAcq.getUser());

		result=st1.executeQuery(query);

		boolean log = false;
		if(result.getString("psw").equals(inputAcq.getPassword())) {
			log = true;
		}
			
		MySQLConnectionFactory.closeConnection(conn);
		return log;
	}	

}
