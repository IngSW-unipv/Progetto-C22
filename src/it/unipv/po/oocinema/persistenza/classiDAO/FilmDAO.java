package it.unipv.po.oocinema.persistenza.classiDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.persistenza.MySQLConnectionFactory;
import it.unipv.po.oocinema.persistenza.interfaccieDAO.IFilmDAO;


public class FilmDAO implements IFilmDAO {

	private Connection conn;


	public FilmDAO() {
		super();
		conn = MySQLConnectionFactory.connect(conn);
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
	public ArrayList<Film> selectAll() throws SQLException {
		
		conn = MySQLConnectionFactory.connect(conn);
		PreparedStatement st1;
		
		String query = "SELECT * FROM film where flag = true;";
		st1 = conn.prepareStatement(query);
		ResultSet result = st1.executeQuery();
		
		ArrayList<Film> film = new ArrayList<Film>();
		
		while (result.next()) {
			film.add(new Film(result.getString("titolo"), result.getString("descrizione"),
					result.getString("genere"), result.getInt("durata"), result.getString("regista"), 
					result.getString("cast"), result.getString("duration"),	result.getString("trailerPath")));
		}
		
		MySQLConnectionFactory.closeConnection(conn);
	
		return film;
	}
	
	@Override
	public void aggiungiFilm(Film inputFilm) throws SQLException {
		conn = MySQLConnectionFactory.connect(conn);
		
		String query = "INSERT INTO film VALUES(?,?,?,?,?,?,?,?,?)";
		PreparedStatement st1 = conn.prepareStatement(query);
		
		st1.setInt(1, inputFilm.getId());
		st1.setString(2, inputFilm.getTitolo());
		st1.setString(3, inputFilm.getDescrizione());
		st1.setString(4, inputFilm.getGenere());
		st1.setInt(5, inputFilm.getDurata());
		st1.setString(6, inputFilm.getRegista());
		st1.setString(7, inputFilm.getCast());
		st1.setString(8, inputFilm.getCoverPath());
		st1.setString(9, inputFilm.getTrailerPath());
		
		MySQLConnectionFactory.closeConnection(conn);
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
					result.getString("cast"), result.getString("duration"),	result.getString("trailerPath"));
	
		MySQLConnectionFactory.closeConnection(conn);
		return f;
	}
		
}


