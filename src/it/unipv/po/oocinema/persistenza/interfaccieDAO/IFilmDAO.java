package it.unipv.po.oocinema.persistenza.interfaccieDAO;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.cinema.Film;

public interface IFilmDAO {
	public void aggiungiFilm(Film inputFilm) throws SQLException;
	public void rimuoviFilm(Film inputFilm) throws SQLException;
	public ArrayList<Film> getTuttiFilm() throws SQLException;
	public Film getFilmbyId(Film filmInput) throws SQLException;
	public Film getFilmbyTitolo(Film inputFilm) throws SQLException;
	public int getNumProiezioniByFilm(Film inputFilm) throws SQLException;
}
