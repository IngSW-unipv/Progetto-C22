package it.unipv.po.oocinema.persistenza.classiDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.acquirenti.Acquirente;
import it.unipv.po.oocinema.model.acquirenti.Cassa;
import it.unipv.po.oocinema.model.acquirenti.Cliente;
import it.unipv.po.oocinema.persistenza.MySQLConnectionFactory;
import it.unipv.po.oocinema.persistenza.interfaccieDAO.IAcquirenteDAO;

public class AcquirenteDAO implements IAcquirenteDAO {

	private Connection conn;

	public AcquirenteDAO() {
		super();
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
	
	@Override
	public void registrazione(Cliente inputCliente) throws SQLException{
		conn = MySQLConnectionFactory.connect(conn);
		
		String query = "INSERT INTO acquirente VALUES(?,?,?,?)";
		PreparedStatement st1 = conn.prepareStatement(query);
		
		st1.setString(1, inputCliente.getUser());
		st1.setString(2, inputCliente.getPassword());
		st1.setDate(3, inputCliente.getCompleanno());
		st1.setString(4, "CLIENTE");
		
		st1.executeUpdate();
		MySQLConnectionFactory.closeConnection(conn);
	}
	
	@Override
	public void aggiungiCassa(Cassa inputCassa) throws SQLException{
		conn = MySQLConnectionFactory.connect(conn);
		
		String query = "INSERT INTO acquirente VALUES(?,?,?,?)";
		PreparedStatement st1 = conn.prepareStatement(query);
		
		st1.setString(1, inputCassa.getUser());
		st1.setString(2, inputCassa.getPassword());
		st1.setDate(3, inputCassa.getCompleanno());
		st1.setString(4, "CASSA");
		
		st1.executeUpdate();
		MySQLConnectionFactory.closeConnection(conn);
	}
	
	@Override
	public void rimuoviCassa(Cassa inputCassa) throws SQLException {
		conn = MySQLConnectionFactory.connect(conn);
		
		String query = "DELETE FROM acquirente where user=?";
		PreparedStatement st1 = conn.prepareStatement(query);
		
		st1.setString(1, inputCassa.getUser());
		
		st1.executeUpdate();
		MySQLConnectionFactory.closeConnection(conn);
		
	}

	@Override
	public ArrayList<Cassa> getTutteCasse() throws SQLException {
		conn = MySQLConnectionFactory.connect(conn);
		
		String query = "SELECT * FROM acquirente where tipo= ?;";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setString(1, "CASSA");
		ResultSet result = st1.executeQuery();
		
		ArrayList<Cassa> casse = new ArrayList<Cassa>();
		while (result.next()) {
			casse.add(new Cassa(result.getString("user"),result.getString("psw")));
		}
		
		MySQLConnectionFactory.closeConnection(conn);
	
		return casse;
	}

}
