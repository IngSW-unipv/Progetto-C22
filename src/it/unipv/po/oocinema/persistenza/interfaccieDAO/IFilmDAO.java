package it.unipv.po.oocinema.persistenza.interfaccieDAO;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.cinema.Film;

public interface IFilmDAO {
	
	public ArrayList<Film> selectAll() throws SQLException;
	
	public Film getFilmbyId(Film filmInput) throws SQLException;
}
