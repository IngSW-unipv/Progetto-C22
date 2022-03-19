package it.unipv.po.oocinema.test;

import static org.junit.Assert.assertFalse; 
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import it.unipv.po.oocinema.model.FasciaOraria;
import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.model.cinema.Proiezione;
import it.unipv.po.oocinema.model.cinema.Sala;
import it.unipv.po.oocinema.persistenza.MySQLConnectionFactory;
 
public class CinemaTest {
	
	@Test
	public void connessioneDatabase() {
		assertTrue(MySQLConnectionFactory.connect());
	}
	
    @Test
    public void proiezioneConFilmInesistente() {
        Proiezione p = new Proiezione("sbagliato", "28/03/22", "11/02/21", "IDS142", "21-22");
        assertFalse(MySQLConnectionFactory.insertProiezione(p));
    }
    
    @Test
    public void proiezioneConFilmEsistente() {
    	String idFilm = "TestID";
    	String idSala = "TestSala"; 
    	String idFascia = "TestFascia";
    	
    	Film film = new Film(idFilm, "TestTitolo", "", "", 0, "", "", 0, "", "");
    	MySQLConnectionFactory.insertFilm(film);
    	
    	Sala sala = new Sala(idSala, "", 0);
    	MySQLConnectionFactory.insertSala(sala);
    	
    	FasciaOraria fasciaOraria = new FasciaOraria(idFascia);
    	MySQLConnectionFactory.insertFasciaOraria(fasciaOraria);
    	
    	
        Proiezione p = new Proiezione(idFilm, "28/03/22", "11/02/21", idSala, idFascia);
        assertTrue(MySQLConnectionFactory.insertProiezione(p));
        
        assertTrue(MySQLConnectionFactory.removeProiezione(p));
        assertTrue(MySQLConnectionFactory.removeFilm(film));
        assertTrue(MySQLConnectionFactory.removeSala(sala));
        assertTrue(MySQLConnectionFactory.removeFasciaOraria(fasciaOraria));
    }
}