package it.unipv.po.oocinema.persistenza;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.acquirenti.Acquirente;
import it.unipv.po.oocinema.model.acquirenti.Cassa;
import it.unipv.po.oocinema.model.acquirenti.Cliente;
import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.model.cinema.Posto;
import it.unipv.po.oocinema.model.cinema.Proiezione;
import it.unipv.po.oocinema.model.cinema.Sala;
import it.unipv.po.oocinema.model.prenotazione.Prenotazione;
import it.unipv.po.oocinema.persistenza.classiDAO.AcquirenteDAO;
import it.unipv.po.oocinema.persistenza.classiDAO.FilmDAO;
import it.unipv.po.oocinema.persistenza.classiDAO.OraDAO;
import it.unipv.po.oocinema.persistenza.classiDAO.PostoDAO;
import it.unipv.po.oocinema.persistenza.classiDAO.PrenotazioneDAO;
import it.unipv.po.oocinema.persistenza.classiDAO.ProiezioneDAO;
import it.unipv.po.oocinema.persistenza.classiDAO.SalaDAO;

public class DBFacade {
	AcquirenteDAO acquirenteDAO;
	FilmDAO filmDAO;
	ProiezioneDAO proiezioneDAO;
	PrenotazioneDAO prenotazioneDAO;
	SalaDAO salaDAO;
	OraDAO oraDAO;
	PostoDAO postoDAO;
	
	
	public DBFacade() {
		acquirenteDAO = new AcquirenteDAO();
		filmDAO = new FilmDAO();
		proiezioneDAO = new ProiezioneDAO();
		prenotazioneDAO= new PrenotazioneDAO();
		salaDAO=new SalaDAO();
		oraDAO = new OraDAO();
		postoDAO=new PostoDAO();
	}

	public boolean login(Acquirente inputAcquirente) throws SQLException {
		return acquirenteDAO.login(inputAcquirente);
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
	
	public int getTipoByUser(Acquirente inputAcquirente) throws SQLException{ 
		return acquirenteDAO.getTipoByUser(inputAcquirente);
	}
	
	public boolean controllaUser(Acquirente inputAcquirente) throws SQLException{ // true se non ci sono utenti 
		return acquirenteDAO.controllaUser(inputAcquirente);
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
	
	public Proiezione getProiezioneByFilmGiornoOra(Proiezione inputProiezione) throws SQLException{ //possibilita di cambiare e togliere questo restituendo un array di proiezioni al metodo sotto
		return proiezioneDAO.getProiezioneByFilmGiornoOra(inputProiezione);
	}
	
	public ArrayList<String> getOreByProiezione(Proiezione inputProiezione) throws SQLException{ 
		return proiezioneDAO.getOreByProiezione(inputProiezione);
	}
	
	public ArrayList<String> getGiorniByFilm(Film inputFilm) throws SQLException{ 
		return proiezioneDAO.getGiorniByFilm(inputFilm);
	}
	
	public ArrayList<Prenotazione> getPrenotazioniFutureByCliente(Acquirente inputAcquirente) throws SQLException{
		return prenotazioneDAO.getPrenotazioniFutureByCliente(inputAcquirente);
	}
	
	public void aggiungiPrenotazione(Prenotazione inputProiezione) throws SQLException{
		prenotazioneDAO.aggiungiPrenotazione(inputProiezione);
	}

	public ArrayList<Sala> getTutteSale() throws SQLException {
		return salaDAO.getTutteSale();
	}
	
	public Sala getSalaById(Sala inputSala) throws SQLException{
		return salaDAO.getSalaById(inputSala);
	}

	public ArrayList<String> getTutteOre() throws SQLException { 
		return oraDAO.getTutteOre();
	}

	public ArrayList<Posto> getRigheLibere(Proiezione inputProiezione) throws SQLException{  //riga e non fila cambiare nel database
		return postoDAO.getRigheLibere(inputProiezione);
	}
	
	public ArrayList<Posto> getPostiLiberiByRiga(Proiezione inputProiezione, Posto inputPosto) throws SQLException{
		return postoDAO.getPostiLiberiByRiga(inputProiezione, inputPosto);
	}
		
}
