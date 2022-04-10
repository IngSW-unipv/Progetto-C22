package it.unipv.po.oocinema.persistenza.classiDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.persistenza.MySQLConnectionFactory;
import it.unipv.po.oocinema.persistenza.interfaccieDAO.IFilmDAO;


public class FilmDAO implements IFilmDAO {

	private Connection conn;


	public FilmDAO() {
		super();
	}
	
	@Override
	public void aggiungiFilm(Film inputFilm) throws SQLException {
		conn = MySQLConnectionFactory.connect(conn);
		
		//String query = ;
		PreparedStatement st1 = conn.prepareStatement("INSERT INTO film VALUES(?,?,?,?,?,?,?,?,?)");
		
		st1.setInt(1, inputFilm.getId());
		st1.setString(2, inputFilm.getTitolo());
		st1.setString(3, inputFilm.getDescrizione());
		st1.setString(4, inputFilm.getGenere());
		st1.setInt(5, inputFilm.getDurata());
		st1.setString(6, inputFilm.getRegista());
		st1.setString(7, inputFilm.getCast());
		st1.setString(8, inputFilm.getCoverPath());
		st1.setString(9, inputFilm.getTrailerPath());
		
		
		st1.executeUpdate();
		MySQLConnectionFactory.closeConnection(conn);
	}
	
	@Override
	public void rimuoviFilm(Film inputFilm) throws SQLException {
		conn = MySQLConnectionFactory.connect(conn);
		
		String query = "DELETE FROM film where id=?";
		PreparedStatement st1 = conn.prepareStatement(query);
		
		st1.setInt(1, inputFilm.getId());
		st1.executeUpdate();
		MySQLConnectionFactory.closeConnection(conn);
	}
	
	@Override
	public ArrayList<Film> getTuttiFilm() throws SQLException {
		
		conn = MySQLConnectionFactory.connect(conn);
				
		String query = "SELECT * FROM film;";
		PreparedStatement st1 = conn.prepareStatement(query);
		ResultSet result = st1.executeQuery();
		
		ArrayList<Film> film = new ArrayList<Film>();
		while (result.next()) {
			film.add(new Film(result.getInt("id"),result.getString("titolo"), result.getString("descrizione"),
					result.getString("genere"), result.getInt("durata"), result.getString("regista"), 
					result.getString("cast"), result.getString("coverPath"),	result.getString("trailerPath")));
		}
		
		MySQLConnectionFactory.closeConnection(conn);
	
		return film;
	}

	@Override
	public Film getFilmbyId(Film inputFilm) throws SQLException{
		
		conn = MySQLConnectionFactory.connect(conn);
	
		String query = "SELECT * FROM film where id=?;";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setString(1, ""+inputFilm.getId());
		ResultSet result=st1.executeQuery(query);
		
		Film f=new Film(result.getInt("id"),result.getString("titolo"), result.getString("descrizione"),
					result.getString("genere"), result.getInt("durata"), result.getString("regista"), 
					result.getString("cast"), result.getString("duration"),	result.getString("trailerPath"));
	
		MySQLConnectionFactory.closeConnection(conn);
		return f;
	}
	
	@Override
	public Film getFilmbyTitolo(Film inputFilm) throws SQLException{
		conn = MySQLConnectionFactory.connect(conn);
		
		String query = "SELECT * FROM film where titolo=?;";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setString(1, ""+inputFilm.getTitolo());
		ResultSet result=st1.executeQuery(query);

		
		Film f=new Film(result.getInt("id"), result.getString("titolo"), result.getString("descrizione"),
					result.getString("genere"), result.getInt("durata"), result.getString("regista"), 
					result.getString("cast"), result.getString("durata"),	result.getString("trailerPath"));
	
		MySQLConnectionFactory.closeConnection(conn);
		return f;
	}

	@Override
	public int getNumProiezioniByFilm(Film inputFilm) throws SQLException {
		conn = MySQLConnectionFactory.connect(conn);
		int r;
		String query = "SELECT count(*) as NUM FROM proiezione GROUP BY film_id having film_id = ?";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setInt(1, inputFilm.getId());
		ResultSet result = st1.executeQuery();
		if(result.next())
			r = result.getInt("NUM");
		else r = 0;
		MySQLConnectionFactory.closeConnection(conn);

		return r;
	}
	
	
		
}


