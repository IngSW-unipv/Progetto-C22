package it.unipv.po.oocinema.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.persistenza.DBFacade;

public class TestFilm {
	
	private Film filmA, filmB;
	
	private DBFacade facade; 
	
	@Before
	public void intTest() throws Exception {
		filmA=new Film(53,"titolo","descrizione","genere", 90,"regista","cast","coverPath","trailerPath");
		filmB=new Film();
		facade = DBFacade.getInstance();
	}
	
	@Test
	public void testAggiungiFilm(){
		try {
			facade.aggiungiFilm(filmA);
			filmB=facade.getFilmbyId(filmA);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertTrue(filmA.equals(filmB)); 
	}

}
