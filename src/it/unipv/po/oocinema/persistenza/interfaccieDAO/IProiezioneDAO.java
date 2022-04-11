package it.unipv.po.oocinema.persistenza.interfaccieDAO;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.cinema.Proiezione;

public interface IProiezioneDAO {
	public void aggiungiProiezione(Proiezione inputProiezione) throws SQLException;
	public void rimuoviProiezione(Proiezione inputProiezione) throws SQLException;
	public Proiezione getProiezioneById(Proiezione inputProiezione) throws SQLException;
	public ArrayList<Proiezione> getTutteProiezioniFuture() throws SQLException;
	
	//public ArrayList<Proiezione> getAllProiezioniByFilmId(Film inputFilm) throws SQLException;
	
}
