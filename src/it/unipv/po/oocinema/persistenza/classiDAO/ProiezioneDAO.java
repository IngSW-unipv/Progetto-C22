package it.unipv.po.oocinema.persistenza.classiDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import it.unipv.po.oocinema.persistenza.MySQLConnectionFactory;
import it.unipv.po.oocinema.persistenza.interfaccieDAO.IProiezioneDAO;
import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.model.cinema.Proiezione;
import it.unipv.po.oocinema.model.cinema.Sala;

public class ProiezioneDAO implements IProiezioneDAO{
	private Connection conn;
	
	@Override
	public Proiezione getProiezione(Proiezione inputProiezione) throws SQLException {
		conn = MySQLConnectionFactory.connect(conn);
		PreparedStatement st1;
		ResultSet result;
		
		String query = "SELECT * FROM proiezione WHERE id = ?;";
		st1 = conn.prepareStatement(query);
		st1.setString(1, ""+inputProiezione.getId());
		result = st1.executeQuery();
		Film f=new Film();
		f.setId(result.getInt("film_id"));
		Sala s=new Sala();
		s.setId(result.getInt("sala_id"));
		Proiezione proiezione = new Proiezione( f, (LocalDate) result.getDate("giorno"), s
								,result.getDouble("prezzo"), result.getString("orario"));
		MySQLConnectionFactory.closeConnection(conn);
		return proiezione;
	}
}
