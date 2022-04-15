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
	
	
	public ArrayList<Posto> getTuttiPostiByPrenotazione(Prenotazione inputPrenotazione) throws SQLException{
		conn = MySQLConnectionFactory.connect(conn);
		
		String query = "SELECT * FROM posto where prenotazione_id=? ;";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setString(1, ""+inputPrenotazione.getId());
		ResultSet result=st1.executeQuery(query);
		
		ArrayList<Posto> p = new ArrayList<Posto>();
		
		while (result.next()) {
			Proiezione proiezione= new Proiezione();
			proiezione.setId(result.getInt("id"));
			ProiezioneDAO proiezioneDAO= new ProiezioneDAO();
			proiezione=proiezioneDAO.getProiezioneById(proiezione);
			p.add(new Prenotazione(result.getInt("id"), result.getDate("data_acquisto"), inputPrenotazione.getAcquirente(), proiezione
					));}
		return null;
	}
}
