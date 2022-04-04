package it.unipv.po.oocinema.persistenza.interfaccieDAO;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.cinema.Proiezione;
import it.unipv.po.oocinema.model.cinema.Film;


public interface IProiezioneDAO {
	
	public Proiezione getProiezione(Proiezione inputProiezione) throws SQLException;
	
	//public ArrayList<Proiezione> getAllProiezioniByFilmId(Film inputFilm) throws SQLException;
	
	//public ArrayList<Proiezione> getAllProiezioni() throws SQLException;
	
	//public void rimuoviProiezione(Proiezione inputProiezione) throws SQLException;
	
	public void aggiungiProiezione(Proiezione inputProiezione) throws SQLException;
}
