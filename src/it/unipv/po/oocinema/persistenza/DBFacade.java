package it.unipv.po.oocinema.persistenza;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.acquirenti.Acquirente;
import it.unipv.po.oocinema.model.acquirenti.Cassa;
import it.unipv.po.oocinema.model.acquirenti.Cliente;
import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.model.cinema.Proiezione;
import it.unipv.po.oocinema.model.cinema.Sala;
import it.unipv.po.oocinema.model.prenotazione.Prenotazione;
import it.unipv.po.oocinema.persistenza.classiDAO.AcquirenteDAO;
import it.unipv.po.oocinema.persistenza.classiDAO.FilmDAO;
import it.unipv.po.oocinema.persistenza.classiDAO.PrenotazioneDAO;
import it.unipv.po.oocinema.persistenza.classiDAO.ProiezioneDAO;
import it.unipv.po.oocinema.persistenza.classiDAO.SalaDAO;

public class DBFacade {
	AcquirenteDAO acquirenteDAO;
	FilmDAO filmDAO;
	ProiezioneDAO proiezioneDAO;
	PrenotazioneDAO prenotazioneDAO;
	SalaDAO salaDAO;

	public DBFacade() {
		acquirenteDAO = new AcquirenteDAO();
		filmDAO = new FilmDAO();
		proiezioneDAO = new ProiezioneDAO();
		prenotazioneDAO= new PrenotazioneDAO();
		salaDAO=new SalaDAO();
	}

	public boolean login(Acquirente inputAcq) throws SQLException {
		return acquirenteDAO.login(inputAcq);
	}
	
	public void registrazione(Cliente inputCliente) throws SQLException{
		acquirenteDAO.registrazione(inputCliente);
	}
	
	public void aggiungiCassa(Cassa inputCassa) throws SQLException {
		acquirenteDAO.aggiungiCassa(inputCassa);
	}
	
	public void rimuoviCassa(Cassa inputCassa) throws SQLException {
		acquirenteDAO.rimuoviCassa(inputCassa);
	}
	
	public ArrayList<Cassa> getTutteCasse() throws SQLException {
		return acquirenteDAO.getTutteCasse();
	}

	public void aggiungiFilm(Film inputFilm) throws SQLException {
		filmDAO.aggiungiFilm(inputFilm);
	}
	
	public void rimuoviFilm(Film inputFilm) throws SQLException {
		filmDAO.rimuoviFilm(inputFilm);
	}
	
	public ArrayList<Film> getTuttiFilm() throws SQLException {
		return filmDAO.getTuttiFilm();
	}
	
	public int getNumProiezioniByFilm(Film inputFilm) throws SQLException {
		return filmDAO.getNumProiezioniByFilm(inputFilm);
	}


	public Film getFilmbyTitolo(Film inputFilm) throws SQLException{
		return filmDAO.getFilmbyTitolo(inputFilm);
	}

	public void aggiungiProiezione(Proiezione inputProiezione) throws SQLException {
		 proiezioneDAO.aggiungiProiezione(inputProiezione);
	}
	
	public void rimuoviProiezione(Proiezione inputProiezione) throws SQLException {
		proiezioneDAO.rimuoviProiezione(inputProiezione);
	}
	
	public Proiezione getProiezioneById(Proiezione inputProiezione) throws SQLException{
		return proiezioneDAO.getProiezioneById(inputProiezione);
	}
	
	public ArrayList<Proiezione> getTutteProiezioniFuture() throws SQLException {
		return proiezioneDAO.getTutteProiezioniFuture();
	}
	
	public ArrayList<Prenotazione> getPrenotazioniFutureByCliente(Cliente inputCliente) throws SQLException{
		return prenotazioneDAO.getPrenotazioniFutureByCliente(inputCliente);
	}

	public Sala getSalaById(Sala inputSala) throws SQLException{
		return salaDAO.getSalaById(inputSala);
	}
	
}
