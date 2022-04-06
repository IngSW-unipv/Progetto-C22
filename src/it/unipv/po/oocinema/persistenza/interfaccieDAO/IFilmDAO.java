package it.unipv.po.oocinema.persistenza.interfaccieDAO;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.cinema.Film;

public interface IFilmDAO {
	public ArrayList<Film> getTuttiFilm() throws SQLException;
	public ArrayList<Integer> getNumProiezioni() throws SQLException;
	public Film getFilmbyId(Film filmInput) throws SQLException;
	public void aggiungiFilm(Film filmInput) throws SQLException;
	public void rimuoviFilm(Film inputFilm) throws SQLException;
	public Film getFilmbyTitolo(Film film) throws SQLException;
}
