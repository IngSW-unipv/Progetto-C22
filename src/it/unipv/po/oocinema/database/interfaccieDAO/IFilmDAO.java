package it.unipv.po.oocinema.database.interfaccieDAO;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.Film;

public interface IFilmDAO {
	
	public ArrayList<Film> selectAll() throws SQLException;
	
	public Film getFilmbyId(Film filmInput) throws SQLException;
}
