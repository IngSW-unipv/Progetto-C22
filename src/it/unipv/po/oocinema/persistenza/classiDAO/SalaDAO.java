package it.unipv.po.oocinema.persistenza.classiDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.persistenza.MySQLConnectionFactory;
import it.unipv.po.oocinema.persistenza.interfaccieDAO.ISalaDAO;
import it.unipv.po.oocinema.model.cinema.Sala; 

public class SalaDAO implements ISalaDAO {
	
	private Connection conn;


	public SalaDAO() {
		super();
		conn = MySQLConnectionFactory.connect(conn); //prova
	}
	
	@Override
	public Sala getSalaById(Sala salaInput) throws SQLException {
		conn = MySQLConnectionFactory.connect(conn);
		PreparedStatement st1;
		ResultSet result;
		
		String query = "SELECT * FROM Sala WHERE id = ?;";
		st1 = conn.prepareStatement(query);
		st1.setString(1, ""+salaInput.getID_sala());
		result = st1.executeQuery();
		Sala sala = new Sala(salaInput.getID_sala(), result.getInt("riga"), result.getInt("colonna"));
		MySQLConnectionFactory.closeConnection(conn);
		return sala;
	}
	
	@Override
	public ArrayList<Sala> getAllSale() throws SQLException {
		conn = MySQLConnectionFactory.connect(conn);
		PreparedStatement st1;
		ResultSet result;
		
		String query = "SELECT * FROM Sala;";
		st1 = conn.prepareStatement(query);
		result = st1.executeQuery();
		ArrayList<Sala> sala = new ArrayList<Sala>();
		while (result.next()) {
			sala.add(new Sala(result.getString("id"), result.getInt("riga"), result.getInt("colonna")));
		}
		MySQLConnectionFactory.closeConnection(conn);
		return sala;
	}
}