package it.unipv.po.oocinema.persistenza.interfaccieDAO;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.model.cinema.Proiezione;
import it.unipv.po.oocinema.model.cinema.Sala;

public interface IProiezioneDAO {
	public void aggiungiProiezione(Proiezione inputProiezione) throws SQLException;
	public void rimuoviProiezione(Proiezione inputProiezione) throws SQLException;
	public Proiezione getProiezioneById(Proiezione inputProiezione) throws SQLException;
	public ArrayList<Proiezione> getTutteProiezioniFuture() throws SQLException;
	public Proiezione getProiezioneByFilmGiornoOra(Proiezione inputProiezione) throws SQLException; //non è possibile avere lo stesso film contemporaneamente in due sale diverse
	public ArrayList<String> getGiorniByFilm(Film inputFilm) throws SQLException;
	public ArrayList<String> getOreByProiezione(Proiezione inputProiezione) throws SQLException;
	//public ArrayList<Proiezione> getAllProiezioniByFilmId(Film inputFilm) throws SQLException;

	
}
