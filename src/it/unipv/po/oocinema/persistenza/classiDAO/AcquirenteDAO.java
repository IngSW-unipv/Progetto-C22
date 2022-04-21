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

		result=st1.executeQuery();

		boolean log = false;
		if( result.next() && result.getString("psw").equals(inputAcq.getPassword())) {
			log = true;
		}
			
		MySQLConnectionFactory.closeConnection(conn);
		return log;
	}	
	
	@Override
	public void registrazione(Cliente inputCliente) throws SQLException{
		conn = MySQLConnectionFactory.connect(conn);
		
		String query = "INSERT INTO acquirente VALUES(?,?,?,?,?,?)";
		PreparedStatement st1 = conn.prepareStatement(query);
		
		st1.setString(1, inputCliente.getUser());
		st1.setString(2, inputCliente.getPassword());
		st1.setInt(3, 1);
		st1.setString(4, inputCliente.getNome());
		st1.setString(5, inputCliente.getCognome());
		st1.setString(6, inputCliente.getCompleanno());
		
		
		st1.executeUpdate();
		MySQLConnectionFactory.closeConnection(conn);
	}
	
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
		st1.setInt(1, 2);
		ResultSet result = st1.executeQuery();
		
		ArrayList<Cassa> casse = new ArrayList<Cassa>();
		while (result.next()) {
			casse.add(new Cassa(result.getString("user"),result.getString("psw")));
		}
		
		MySQLConnectionFactory.closeConnection(conn);
	
		return casse;
	}
	
	@Override
	public int getTipoByUser(Acquirente inputAcquirente) throws SQLException{
		conn = MySQLConnectionFactory.connect(conn);
		
		String query = "SELECT tipo FROM acquirente where user= ?;";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setString(1, inputAcquirente.getUser());
		ResultSet result = st1.executeQuery();
		int c;
		if(result.next()) {
			c=result.getInt("tipo"); 
		}else c = -1;
		MySQLConnectionFactory.closeConnection(conn);
		return c;
	}
	
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

}
