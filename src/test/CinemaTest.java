package test;

import static org.junit.Assert.assertFalse; 
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import database.MySQLConnection;
import model.FasciaOraria;
import model.Film;
import model.Proiezione;
import model.Sala;
 
public class CinemaTest {
	
	@Test
	public void connessioneDatabase() {
		assertTrue(MySQLConnection.connect());
	}
	
    @Test
    public void proiezioneConFilmInesistente() {
        Proiezione p = new Proiezione("sbagliato", "28/03/22", "11/02/21", "IDS142", "21-22");
        assertFalse(MySQLConnection.insertProiezione(p));
    }
    
    @Test
    public void proiezioneConFilmEsistente() {
    	String idFilm = "TestID";
    	String idSala = "TestSala"; 
    	String idFascia = "TestFascia";
    	
    	Film film = new Film(idFilm, "TestTitolo", "", "", 0, "", "", 0, "", "");
    	MySQLConnection.insertFilm(film);
    	
    	Sala sala = new Sala(idSala, "", 0);
    	MySQLConnection.insertSala(sala);
    	
    	FasciaOraria fasciaOraria = new FasciaOraria(idFascia);
    	MySQLConnection.insertFasciaOraria(fasciaOraria);
    	
    	
        Proiezione p = new Proiezione(idFilm, "28/03/22", "11/02/21", idSala, idFascia);
        assertTrue(MySQLConnection.insertProiezione(p));
        
        assertTrue(MySQLConnection.removeProiezione(p));
        assertTrue(MySQLConnection.removeFilm(film));
        assertTrue(MySQLConnection.removeSala(sala));
        assertTrue(MySQLConnection.removeFasciaOraria(fasciaOraria));
    }
}