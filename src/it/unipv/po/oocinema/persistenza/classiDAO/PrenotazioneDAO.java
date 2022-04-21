package it.unipv.po.oocinema.persistenza.classiDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.acquirenti.Acquirente;
import it.unipv.po.oocinema.model.cinema.Posto;
import it.unipv.po.oocinema.model.cinema.Proiezione;
import it.unipv.po.oocinema.model.prenotazione.Prenotazione;
import it.unipv.po.oocinema.persistenza.interfaccieDAO.IPrenotazioneDAO;

import it.unipv.po.oocinema.persistenza.MySQLConnectionFactory;


public class PrenotazioneDAO implements IPrenotazioneDAO {
	private Connection conn;

	public PrenotazioneDAO() {
		super();
	}
	
	@Override
	public ArrayList<Prenotazione> getPrenotazioniFutureByCliente(Acquirente inputAcquirente) throws SQLException{
		conn = MySQLConnectionFactory.connect(conn);
		String query = "SELECT * FROM prenotazione where acquirente_user=? and giorno>curdate();";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setString(1, ""+inputAcquirente.getUser());
		ResultSet result=st1.executeQuery();
		ArrayList<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
		while (result.next()) {
			Proiezione proiezione= new Proiezione();
			proiezione.setId(result.getInt("id"));
			ProiezioneDAO proiezioneDAO= new ProiezioneDAO();
			proiezione=proiezioneDAO.getProiezioneById(proiezione);
			prenotazioni.add(new Prenotazione(result.getInt("id"), result.getString("data_acquisto"), inputAcquirente, proiezione
					));
		}
		MySQLConnectionFactory.closeConnection(conn);
		return prenotazioni;
	}
	
	@Override
	public void aggiungiPrenotazione(Prenotazione inputPrenotazione) throws SQLException {
		conn = MySQLConnectionFactory.connect(conn);
		String query = "INSERT INTO prenotazione VALUES(?,?,?,?,?)";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setInt(1, inputPrenotazione.getId());
		st1.setString(2, inputPrenotazione.getDataAcquisto());
		st1.setInt(3, inputPrenotazione.getNumPosti());
		st1.setInt(4, inputPrenotazione.getProiezione().getId()); 
		st1.setString(5, inputPrenotazione.getAcquirente().getUser());
		st1.executeUpdate();
		for (Posto p : inputPrenotazione.getPosti()) {
		query="INSERT INTO prenotazione VALUES(?,?,?)";
		st1 = conn.prepareStatement(query);
		st1.setInt(1, p.getRiga());
		st1.setInt(2, p.getColonna());
		st1.setInt(3, inputPrenotazione.getId());
		st1.executeUpdate();
		}
		MySQLConnectionFactory.closeConnection(conn);
	}
}
