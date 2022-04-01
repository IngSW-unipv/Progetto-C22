package it.unipv.po.oocinema.persistenza.classiDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.persistenza.MySQLConnectionFactory;
import it.unipv.po.oocinema.persistenza.interfaccieDAO.IPrenotazioneDAO;
import it.unipv.po.oocinema.model.cinema.Prenotazione; 

public class ProiezioneDAO implements IPrenotazioneDAO{
	private Connection conn;

}
