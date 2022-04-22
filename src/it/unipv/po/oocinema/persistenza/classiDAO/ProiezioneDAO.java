package it.unipv.po.oocinema.persistenza.classiDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.persistenza.MySQLConnectionFactory;
import it.unipv.po.oocinema.persistenza.interfaccieDAO.IProiezioneDAO;
import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.model.cinema.Proiezione;
import it.unipv.po.oocinema.model.cinema.Sala;

public class ProiezioneDAO implements IProiezioneDAO{
	private Connection conn;
	
	public ProiezioneDAO() {
		super();
	}
	
	@Override
	public Proiezione getProiezioneById(Proiezione inputProiezione) throws SQLException {
		conn = MySQLConnectionFactory.connect(conn);
		PreparedStatement st1;
		ResultSet result;
		String query = "SELECT * FROM proiezione WHERE id = ?;";
		st1 = conn.prepareStatement(query);
		st1.setString(1, ""+inputProiezione.getId());
		result = st1.executeQuery();
		Film f=new Film();
		f.setId(result.getInt("film_id"));
		FilmDAO filmDAO= new FilmDAO();
		f=filmDAO.getFilmbyId(f);
		Sala s=new Sala();
		s.setId(result.getInt("sala_id"));
		SalaDAO salaDAO= new SalaDAO();
		s=salaDAO.getSalaById(s);
		Proiezione proiezione;
		if(result.next()) {
			proiezione = new Proiezione( inputProiezione.getId() ,f, result.getString("giorno"), s
										,result.getDouble("prezzo"), result.getString("orario"));
		} else proiezione=null;
		MySQLConnectionFactory.closeConnection(conn);
		return proiezione;
	}
	
	@Override
	public void aggiungiProiezione(Proiezione inputProiezione) throws SQLException{
		conn = MySQLConnectionFactory.connect(conn);
		String query = "INSERT INTO proiezione VALUES(?,?,?,?,?,?)";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setInt(1, inputProiezione.getId());
		st1.setDouble(2, inputProiezione.getPrezzo());
		st1.setInt(3, inputProiezione.getFilm().getId());
		st1.setInt(4, inputProiezione.getSala().getId());
		st1.setString(5, inputProiezione.getGiorno());
		st1.setString(6, inputProiezione.getOrario());
		st1.executeUpdate();
		MySQLConnectionFactory.closeConnection(conn);
	}
	
	@Override
	public void rimuoviProiezione(Proiezione inputProiezione) throws SQLException {
		conn = MySQLConnectionFactory.connect(conn);
		String query = "DELETE FROM proiezione where id= ?";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setInt(1, inputProiezione.getId());
		st1.executeUpdate();
		MySQLConnectionFactory.closeConnection(conn);
	}

	@Override
	public ArrayList<Proiezione> getTutteProiezioniFuture() throws SQLException {
		conn = MySQLConnectionFactory.connect(conn);
		String query = "SELECT * FROM proiezione where giorno>curdate();";
		PreparedStatement st1 = conn.prepareStatement(query);
		ResultSet result=st1.executeQuery();
		ArrayList<Proiezione> p = new ArrayList<Proiezione>();
		while (result.next()) {
			Film f=new Film();
			f.setId(result.getInt("film_id")); 
			FilmDAO filmDAO= new FilmDAO();
			f=filmDAO.getFilmbyId(f);
			Sala s=new Sala();
			s.setId(result.getInt("sala_id"));
			SalaDAO salaDAO= new SalaDAO();
			s=salaDAO.getSalaById(s);
			p.add(new Proiezione(result.getInt("id"),f, result.getString("giorno"), s, 
					result.getDouble("prezzo"), result.getString("orario")));
		}
		MySQLConnectionFactory.closeConnection(conn);
		return p;
	}
	
	@Override
	public Proiezione getProiezioneByFilmGiornoOra(Proiezione inputProiezione) throws SQLException{
		conn = MySQLConnectionFactory.connect(conn);
		PreparedStatement st1;
		ResultSet result;
		String query = "SELECT * FROM proiezione WHERE film_id = ? and giorno = ? and orario = ?;";
		st1 = conn.prepareStatement(query);
		st1.setInt(1, inputProiezione.getFilm().getId());
		st1.setString(2, ""+inputProiezione.getGiorno());
		st1.setString(3, ""+inputProiezione.getOrario());
		result = st1.executeQuery();
		Sala s=new Sala();
		s.setId(result.getInt("sala_id"));
		SalaDAO salaDAO= new SalaDAO();
		s=salaDAO.getSalaById(s);
		Proiezione proiezione;
		if(result.next()) {
			proiezione = new Proiezione( result.getInt("id") ,inputProiezione.getFilm() , inputProiezione.getGiorno(), s
								,result.getDouble("prezzo"), inputProiezione.getOrario());
		}else proiezione=null;
		MySQLConnectionFactory.closeConnection(conn);
		return proiezione;
	}
	
	@Override
	public ArrayList<String> getGiorniByFilm(Film inputFilm) throws SQLException{
		FilmDAO filmDAO= new FilmDAO(); //ho solo il titolo del film in inputFilm
		inputFilm=filmDAO.getFilmbyTitolo(inputFilm);
		conn = MySQLConnectionFactory.connect(conn);
		String query = "SELECT giorno FROM proiezione where film_id=?;";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setInt(1, inputFilm.getId());
		ResultSet result=st1.executeQuery();
		ArrayList<String> giorni = new ArrayList<String>();
		while (result.next()) {
			giorni.add(result.getString("giorno"));
		}
		MySQLConnectionFactory.closeConnection(conn);
		return giorni;
	}
	
	@Override
	public ArrayList<String> getOreByProiezione(Proiezione inputProiezione) throws SQLException{
		conn = MySQLConnectionFactory.connect(conn);
		String query = "SELECT orario FROM proiezione where film_id=? and giorno=?;";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setInt(1, inputProiezione.getId());
		st1.setString(2, ""+inputProiezione.getGiorno());
		ResultSet result=st1.executeQuery();
		ArrayList<String> ore = new ArrayList<String>();
		while (result.next()) {
			ore.add(result.getString("orario"));
		}
		MySQLConnectionFactory.closeConnection(conn);
		return ore;
	}
}
