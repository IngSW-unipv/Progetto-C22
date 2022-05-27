package it.unipv.po.oocinema.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.persistenza.DBFacade;

/**
 * Classe di test per la classe Film
 * @author GoF
 *
 */
public class TestFilm {
	
	/**
	 * Film di test
	 */
	private Film filmA, filmB;
	
	/**
	 * Istanza della classe che comunica con il DB
	 */
	private DBFacade facade; 
	
	/**
	 * Inizializzazione delle variabili, prima del test
	 * @throws Exception
	 */
	@Before
	public void intTest() throws Exception {
		filmA=new Film(2,"titolo","descrizione","genere", 90,"regista","cast","coverPath","trailerPath"); //incrementa sempre
		filmB=new Film();
		facade = DBFacade.getInstance();
	}
	
	/**
	 * Test sul film contenuto nel DB. Si verifica che i dati nel DB sono quelli aspettati
	 */
	@Test
	public void testGetFilmById(){
		try {
			filmB=facade.getFilmbyId(filmA);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertTrue(filmA.equals(filmB)); 
	}
	
	/**
	 * Test sull'aggiunte di un film nel DB
	 */
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

	/**
	 * TEst sulla rimozione di un film dal DB
	 */
	@Test
	public void testRimuoviFilm(){
		try {
			facade.aggiungiFilm(filmA);
			facade.rimuoviFilm(filmA);
			filmB=facade.getFilmbyId(filmA);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertNull(filmB); 
	}
}
