package it.unipv.po.oocinema.persistenza.classiDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.cinema.Posto;
import it.unipv.po.oocinema.model.cinema.Proiezione;
import it.unipv.po.oocinema.model.prenotazione.Prenotazione;
import it.unipv.po.oocinema.persistenza.MySQLConnectionFactory;
import it.unipv.po.oocinema.persistenza.interfaccieDAO.IPostoDAO;

public class PostoDAO implements IPostoDAO {
	
	private Connection conn;

	public PostoDAO() {
		super();
	}
	
	@Override
	public ArrayList<Posto> getTuttiPostiByPrenotazione(Prenotazione inputPrenotazione) throws SQLException{
		conn = MySQLConnectionFactory.connect(conn);
		String query = "SELECT * FROM posto where prenotazione_id=? ;";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setString(1, ""+inputPrenotazione.getId());
		ResultSet result=st1.executeQuery();
		ArrayList<Posto> posti = new ArrayList<Posto>();
		while (result.next()) {
			posti.add(new Posto(result.getInt("riga"),result.getInt("colonna"), inputPrenotazione));
		}
		MySQLConnectionFactory.closeConnection(conn);
		return posti;
	}
	
	@Override
	public ArrayList<Posto> getRigheLibere(Proiezione inputProiezione) throws SQLException{
		conn = MySQLConnectionFactory.connect(conn);
		String query = "select count(*) as NUM, riga\r\n"
					 + "from posto A join prenotazione B on A.prenotazione_id=B.id\r\n"
					 + "group by riga\r\n"
					 + "having B.proiezione_id=? ;";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setString(1, ""+inputProiezione.getId());
		ResultSet result=st1.executeQuery();
		ArrayList<Posto> posti = new ArrayList<Posto>();
		while (result.next()) {
			if(result.getInt("NUM")<inputProiezione.getSala().getColonne())
				posti.add(new Posto(result.getInt("riga")));
		}
		MySQLConnectionFactory.closeConnection(conn);
		return posti;
	}
	
	@Override
	public ArrayList<Posto> getPostiLiberiByRiga(Proiezione inputProiezione, Posto inputPosto) throws SQLException{
		conn = MySQLConnectionFactory.connect(conn);
		String query ="select colonna posto A join prenotazione B on A.prenotazione_id=B.id\r\n"
				     + "where B.proiezione_id=? and riga=?;";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setString(1, ""+inputProiezione.getId());
		st1.setString(2, ""+inputPosto.getRiga());
		ResultSet result=st1.executeQuery();
		ArrayList<Posto> posti = new ArrayList<Posto>();
		while (result.next()) {
			posti.add(new Posto(result.getInt("riga"), result.getInt("colonna")));
		}
		MySQLConnectionFactory.closeConnection(conn);
		return posti;
	}
}
