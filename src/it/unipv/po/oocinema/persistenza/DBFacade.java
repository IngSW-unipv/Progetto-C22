package it.unipv.po.oocinema.persistenza;

import java.sql.SQLException;


import it.unipv.po.oocinema.model.acquirenti.Acquirente;
import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.model.cinema.Proiezione;
import it.unipv.po.oocinema.model.cinema.Sala;
import it.unipv.po.oocinema.persistenza.classiDAO.AcquirenteDAO;
import it.unipv.po.oocinema.persistenza.classiDAO.FilmDAO;
import it.unipv.po.oocinema.persistenza.classiDAO.ProiezioneDAO;
import it.unipv.po.oocinema.persistenza.interfaccieDAO.IAcquirenteDAO;
import it.unipv.po.oocinema.persistenza.interfaccieDAO.IFilmDAO;

public class DBFacade {
	IAcquirenteDAO iAcquirenteDAO;
	IFilmDAO iFilmDAO;

	public DBFacade() {
		iAcquirenteDAO = new AcquirenteDAO();
		iFilmDAO = new FilmDAO();
	}
	

	public boolean login(Acquirente inputAcq) {
		try {
			return iAcquirenteDAO.login(inputAcq);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}


	public void aggiungiFilm(Film inputFilm) throws SQLException {
		FilmDAO f= new FilmDAO();
		f.aggiungiFilm(inputFilm);
		
	}


	public Film getFilmbyTitolo(Film inputFilm) throws SQLException{
		FilmDAO f=new FilmDAO();
		return f.getFilmbyTitolo(inputFilm);
	}


	public void aggiungiProiezione(Proiezione inputProiezione) throws SQLException {
		ProiezioneDAO p= new ProiezioneDAO();
		p.aggiungiProiezione(inputProiezione);
	}


	public Sala getSala(Sala sala) throws SQLException{
		// TODO Auto-generated method stub
		return null;
	}


	
}
