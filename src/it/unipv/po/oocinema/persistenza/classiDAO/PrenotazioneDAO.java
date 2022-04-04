package it.unipv.po.oocinema.persistenza.classiDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unipv.po.oocinema.model.prenotazione.Prenotazione;
import it.unipv.po.oocinema.persistenza.MySQLConnectionFactory;
import it.unipv.po.oocinema.persistenza.interfaccieDAO.IPrenotazioneDAO;
public class PrenotazioneDAO {
	private Connection conn;

	public PrenotazioneDAO() {
		super();
		conn = MySQLConnectionFactory.connect(conn);
	}
	
}
