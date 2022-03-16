package it.unipv.po.oocinema.database.classiDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.database.MySQLConnectionFactory;
import it.unipv.po.oocinema.database.interfaccieDAO.IFilmDAO;
import it.unipv.po.oocinema.model.Film;


public class FilmDAO implements IFilmDAO {

	private Connection conn;


	public FilmDAO() {
		super();
		conn = MySQLConnectionFactory.connect(conn);
	}
	

	@Override
	public Film getFilmbyId(Film filmInput) throws SQLException{
		
		conn = MySQLConnectionFactory.connect(conn);
		PreparedStatement st1;
		ResultSet result;
		
		String query = "SELECT * FROM film where id_film=?;";
		st1 = conn.prepareStatement(query);
		st1.setString(1, ""+filmInput.getID_film());

		result=st1.executeQuery(query);

		
		Film f=new Film(result.getInt("id_film"), result.getString("titolo"), result.getString("descrizione"),
					result.getString("genere"), result.getInt("durata"), result.getString("regista"), result.getString("cast"), result.getString("duration"),
					result.getString("trailerPath"));
			

		MySQLConnectionFactory.closeConnection(conn);
		return f;
		
	}


	@Override
	public ArrayList<Film> selectAll() throws SQLException {
		
		conn = MySQLConnectionFactory.connect(conn);
		
		String query = "SELECT * FROM film where flag = true;";
		PreparedStatement st = conn.prepareStatement(query);
		ResultSet result = st.executeQuery();
		
		ArrayList<Film> film = new ArrayList<Film>();
		
		while (result.next()) {
			film.add(new Film(result.getInt("id_film"), result.getString("titolo"), result.getString("descrizione"),
					result.getString("genere"), result.getInt("durata"), result.getString("regista"), result.getString("cast"), result.getString("duration"),
					result.getString("trailerPath")));
		}
		
		MySQLConnectionFactory.closeConnection(conn);
	
		return film;
		
	}

	
		
		
		
		
		
		
	}


