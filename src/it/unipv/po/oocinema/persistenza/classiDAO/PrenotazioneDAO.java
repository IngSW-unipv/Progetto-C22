package it.unipv.po.oocinema.persistenza.classiDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.acquirenti.Acquirente;
import it.unipv.po.oocinema.model.acquirenti.Cliente;
import it.unipv.po.oocinema.model.cinema.Film;
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
	public ArrayList<Prenotazione> getPrenotazioniFutureByCliente(Cliente inputCliente) throws SQLException{
		conn = MySQLConnectionFactory.connect(conn);
		
		String query = "SELECT * FROM prenotazione where acquirente_user=? and giorno=curdate();";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setString(1, ""+inputCliente.getUser());
		ResultSet result=st1.executeQuery(query);
		
		ArrayList<Prenotazione> p = new ArrayList<Prenotazione>();
		
		while (result.next()) {
			Proiezione proiezione= new Proiezione();
			proiezione.setId(result.getInt("id"));
			ProiezioneDAO proiezioneDAO= new ProiezioneDAO();
			proiezione=proiezioneDAO.getProiezione(proiezione);
			p.add(new Prenotazione(result.getInt("id"), result.getDate("data_acquisto"), inputCliente, proiezione
					));
		}
		MySQLConnectionFactory.closeConnection(conn);
		return p;
	}
	
}
