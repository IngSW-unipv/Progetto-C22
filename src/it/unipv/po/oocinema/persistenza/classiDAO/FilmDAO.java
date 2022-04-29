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
	}
	
	/**
	 * Metodo usato dall'amministratore per registrare una nuovo film.
	 * 
	 * @param inputFilm oggetto che contiene tutti gli attributi che andranno 
	 * 					 registrati.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
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
		st1.executeUpdate();
		MySQLConnectionFactory.closeConnection(conn);
	}
	
	/**
	 * Metodo usato dall'amministratore per rimuovere un film registrato.
	 * 
	 * @param inputFilm oggetto che contiene l'identificativo del film da 
	 * 					cancellare.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	@Override
	public void rimuoviFilm(Film inputFilm) throws SQLException {
		conn = MySQLConnectionFactory.connect(conn);
		String query = "DELETE FROM film where id=?";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setInt(1, inputFilm.getId());
		st1.executeUpdate();
		MySQLConnectionFactory.closeConnection(conn);
	}
	
	/**
	 * Metodo che restituisce tutti i film registrati e i loro attributi. 
	 * 
	 * @return lista dei film registrati con i loro identificativi e 
	 * 		   password associate.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
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
	
	/**
	 * Metodo che restituisce tutti gli attributi di un film dato il suo identificativo. 
	 * 
	 * @param inputFilm oggetto che contiene l'identificativo del film da 
	 * 					recuperare.
	 * @return Oggetto film con tutti i suoi attributi il cui identificativo è uguale
	 * 		   a quello fornito in input.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	@Override
	public Film getFilmbyId(Film inputFilm) throws SQLException{
		conn = MySQLConnectionFactory.connect(conn);
		String query = "SELECT * FROM film where id=?;";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setString(1, ""+inputFilm.getId());
		ResultSet result=st1.executeQuery();
		Film f;
		if(result.next()) {
			f=new Film(result.getInt("id"),result.getString("titolo"), result.getString("descrizione"),
					result.getString("genere"), result.getInt("durata"), result.getString("regista"), 
					result.getString("cast"), result.getString("coverPath"),	result.getString("trailerPath"));
		}else f = null;
		MySQLConnectionFactory.closeConnection(conn);
		return f;
	}
	
	/**
	 * Metodo che restituisce tutti gli attributi di un film dato il suo titolo. 
	 * 
	 * @param inputFilm oggetto che contiene il titolo del film da 
	 * 					recuperare.
	 * @return Oggetto film con tutti i suoi attributi il cui titolo è uguale
	 * 		   a quello fornito in input.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	@Override
	public Film getFilmbyTitolo(Film inputFilm) throws SQLException{
		conn = MySQLConnectionFactory.connect(conn);
		String query = "SELECT * FROM film where titolo=?;";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setString(1, inputFilm.getTitolo());
		ResultSet result=st1.executeQuery();
		Film f;
		if(result.next()) {
			f=new Film(result.getInt("id"), result.getString("titolo"), result.getString("descrizione"),
					result.getString("genere"), result.getInt("durata"), result.getString("regista"), 
					result.getString("cast"), result.getString("coverPath"),	result.getString("trailerPath"));
		} else f = null;
		MySQLConnectionFactory.closeConnection(conn);
		return f;
	}
}


